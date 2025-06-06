package com.running.runnerz.run;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class RunController {
  @GetMapping('/hello')
  public String index() {
    return "Welcome to the Running App!";
  }

}
