package com.takima.race.registration.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.takima.race.registration.DTO.RegistrationDto;
import com.takima.race.registration.Entity.Registration;
import com.takima.race.registration.Service.RegistrationService;
import com.takima.race.runner.entities.Runner;

@RestController
@RequestMapping("/races")
public class RegitrationController {
    private final RegistrationService registrationService;

    public RegitrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    
    @GetMapping("/{id}/registrations")
    public List<Runner> getRacePartcipant(@PathVariable Long id) {
        return registrationService.getRunnerByRaceId(id);
    }

    @PostMapping("/{id}/registrations")
    public ResponseEntity<Registration> addRegistration (@PathVariable Long id,  @RequestBody RegistrationDto idRunner) {
        return ResponseEntity.status(HttpStatus.CREATED).body(registrationService.addParticipant(id, idRunner.getRunnerId()));
    }
}
