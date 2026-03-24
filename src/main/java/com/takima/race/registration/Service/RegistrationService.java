package com.takima.race.registration.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.race.entities.Race;
import com.takima.race.race.services.RaceService;
import com.takima.race.registration.Entity.Registration;
import com.takima.race.registration.Repository.RegistrationRepository;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.services.RunnerService;


@Service
public class RegistrationService {
    private final RegistrationRepository registrationRepository;
    private final RunnerService runnerService;
    private final RaceService raceService;



    public RegistrationService(RegistrationRepository registrationRepository, RunnerService runnerService, RaceService raceService) {
        this.registrationRepository = registrationRepository;
        this.runnerService = runnerService;
        this.raceService = raceService;
    }


    public List<Runner> getRunnerByRaceId(Long RaceId) {
        Race r = raceService.getById(RaceId);
        List<Registration> regs = registrationRepository.findRunnerIdByRaceId(RaceId);
        List<Runner> participants = new ArrayList<>();
        //List<Long> ids = new ArrayList<>(); //Test avec renvoie des ids
        for (Registration reg : regs){
            //ids.add(reg.getRunnerId());
            participants.add(runnerService.getById(reg.getRunnerId()));
        }
        return participants;
    }

    public Registration addParticipant(Long idRace, Long idRunner){
        Race r = raceService.getById(idRace);
        Runner ru = runnerService.getById(idRunner);
        if (raceService.getById(idRace).getMaxParticipants() == raceService.countParticipant(idRace)){
            //Code Erreur 409, course complête
             throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                String.format("La course d'id %d est complète", idRace)
            );
        }
        if (getRunnerByRaceId(idRace).contains(runnerService.getById(idRunner))){
            //Code Erreur 409, cours déjà inscrit
             throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                String.format("Le coureur %d est déjà inscrit à la course %d", idRunner, idRace)
            );
        }
        Date date = Date.valueOf(LocalDate.now());
        return registrationRepository.save(new Registration(idRace, idRunner, date));
    }

}
