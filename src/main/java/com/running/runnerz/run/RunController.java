package com.running.runnerz.run;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/runs")
public class RunController {

  private final RunRepository runRepository;

  public RunController(RunRepository runRepository) {
    this.runRepository = runRepository;
  }
  
  @GetMapping("")
  public List<Run> findAll() {
    return runRepository.findAll();
  }

  @GetMapping("/{id}")
  public Run findById(@PathVariable("id") Integer id) {
    Optional<Run> run = runRepository.findById(id);
    if (run.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run not found with id: " + id);
    } 
      return run.get();
    
  }

  @PostMapping("")
  public ResponseEntity<Run> create(@Valid @RequestBody Run run) {
    if (null == run || null == run.id() || null == run.title() || null == run.startTime() || null == run.endTime() || null == run.kms() || null == run.location()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Run data");
    }
    runRepository.save(run);
    if (runRepository.findById(run.id()).isEmpty()) {
      throw new RunNotFoundException();
    }
    // Optionally, you can return the created run or a success message
    return ResponseEntity.status(HttpStatus.CREATED).body(run);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Run> update(@PathVariable("id") Integer id, @RequestBody Run run) {
    if (null == run || !run.id().equals(id) || null == run.title() || null == run.startTime() || null == run.endTime() || null == run.kms() || null == run.location()) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Run data");
    }
    Optional<Run> existingRun = runRepository.findById(id);
    if(existingRun.isEmpty()) throw new RunNotFoundException();
    runRepository.update(run.id(), run.title(), run.startTime(), run.endTime(), run.kms(), run.location());
    return ResponseEntity.status(HttpStatus.OK).body(run);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> delete(@PathVariable("id") Integer id) {
    Optional<Run> existingRun = runRepository.findById(id);
    if (existingRun.isEmpty()) {
      throw new RunNotFoundException();
    }
    runRepository.delete(existingRun.get());
    return ResponseEntity.noContent().build();
  }

}
