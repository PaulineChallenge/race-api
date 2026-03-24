package com.takima.race.runner.controllers;

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

import com.takima.race.race.entities.Race;
import com.takima.race.runner.entities.Runner;
import com.takima.race.runner.services.RunnerService;

@RestController
@RequestMapping("/runners")
public class RunnerController {
    private final RunnerService runnerService;

    public RunnerController(RunnerService runnerService) {
        this.runnerService = runnerService;
    }

    @GetMapping
    public List<Runner> getAll() {
        return runnerService.getAll();
    }

    @GetMapping("/{id}")
    public Runner getById(@PathVariable Long id) {
        return runnerService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Runner> create(@RequestBody Runner runner) {
        return ResponseEntity.status(HttpStatus.CREATED).body(runnerService.create(runner));

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        runnerService.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Runner> update(@PathVariable Long id, @RequestBody Runner runner) {
        return ResponseEntity.status(HttpStatus.CREATED).body(runnerService.update(id, runner));
    }

    @GetMapping("/{id}/races")
    public List<Race> getRaces(@PathVariable Long id){
        return runnerService.getRaces(id);
    }
}