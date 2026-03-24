package com.takima.race.runner.services;

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
import com.takima.race.runner.repositories.RunnerRepository;

@Service
public class RunnerService {

    private final RunnerRepository runnerRepository;
    private final RegistrationRepository registrationDao;
    private final RaceService raceService;


    public RunnerService(RunnerRepository runnerRepository, RegistrationRepository registrationDao, RaceService raceService) {
        this.registrationDao = registrationDao;
        this.runnerRepository = runnerRepository;
        this.raceService = raceService;
    }

    public List<Runner> getAll() {
        return runnerRepository.findAll();
    }

    public Runner getById(Long id) {
        return runnerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Runner %s not found", id)
                )
        );
    }

    public Runner create(Runner runner) {
        if (!runner.getEmail().contains("@")){
            //pas le bon format de mail
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                String.format("L'adresse mail %s n'a pas le bon format (pas de @)", runner.getEmail())
            );
        }
        return runnerRepository.save(runner);
    }

    public void deleteById(Long id) {
        runnerRepository.deleteById(id);
    }

    public Runner update(Long id, Runner runnerMod) {
        Runner runner = getById(id);
        runner.setAge(runnerMod.getAge());
        runner.setEmail(runnerMod.getEmail());
        runner.setFirstName(runnerMod.getFirstName());
        runner.setLastName(runnerMod.getLastName());
        return runnerRepository.save(runner);
    }

    public List<Race> getRaces(Long id){
        Runner r = getById(id);
        List<Registration> regs = registrationDao.findRacesIdByRunnerId(id);
        List<Race> courses = new ArrayList<>();
        for (Registration reg : regs){
            courses.add(raceService.getById(reg.getRaceId()));
        }
        return courses;    
    }
}
