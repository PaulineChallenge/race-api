package com.takima.race.race.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.takima.race.race.DTO.RaceDto;
import com.takima.race.race.entities.Race;
import com.takima.race.race.repositories.RaceRepository;
import com.takima.race.registration.Repository.RegistrationRepository;

@Service
public class RaceService {
    private final RaceRepository raceRepository;
    private final RegistrationRepository registrationRepository;

    public RaceService(RaceRepository raceRepository, RegistrationRepository registrationRepository) {
        this.raceRepository = raceRepository;
        this.registrationRepository = registrationRepository;
    }

    public List<Race> getAll() {
        return raceRepository.findAll();
    }

    public Race getById(Long id) {
        return raceRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        String.format("Race %s not found", id)
                )
        );
    }

    public Race create(Race race) {
        System.out.println("Valeur dans service : " + race.getMaxParticipants());
        return raceRepository.save(race);
    }

    public void deleteById(Long id) {
        raceRepository.deleteById(id);
    }

    public Race update(Long id, Race raceMod) {
        Race race = getById(id);
        race.setDate(raceMod.getDate());
        race.setName(raceMod.getName());
        race.setMaxParticipants(raceMod.getMaxParticipants());
        race.setLocation(raceMod.getLocation());
        return raceRepository.save(race);
    }

    public long countParticipant(Long RaceId) {
        return registrationRepository.countRunnerIdByRaceId(RaceId);
    }

    public RaceDto countParticipants(Long RaceId){
        return new RaceDto(countParticipant(RaceId));
    }
}