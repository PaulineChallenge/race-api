
package com.takima.race.race.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takima.race.race.DTO.RaceDto;
import com.takima.race.race.entities.Race;
import com.takima.race.race.services.RaceService;

@RestController
@RequestMapping("/races")
public class RaceController {
    private final RaceService raceService;

    public RaceController(RaceService raceService) {
        this.raceService = raceService;
    }

    @GetMapping
    public List<Race> getAll() {
        return raceService.getAll();
    }

    @GetMapping("/{id}")
    public Race getById(@PathVariable Long id) {
        return raceService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Race> create(@RequestBody Race race) {
        return ResponseEntity.status(HttpStatus.CREATED).body(raceService.create(race));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        raceService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Race update(@PathVariable Long id, @RequestBody Race race) {
        return raceService.update(id, race);
    }

    @GetMapping("/{raceId}/participants/count")
    public RaceDto countParticipant(@PathVariable Long raceId){
        return raceService.countParticipants(raceId);
    }
}