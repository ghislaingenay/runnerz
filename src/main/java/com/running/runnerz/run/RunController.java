package com.running.runnerz.run;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("/1")
  public Run findById() {
    return runRepository.findById(1);
  }

}
