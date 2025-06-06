package com.running.runnerz.run;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;

@Repository
public class RunRepository {
    private List<Run> runs = new ArrayList<>();

    public List<Run> findAll() {
        return runs;
    }

    @PostConstruct  // performed after the bean is initialized
    @SuppressWarnings("unused")
    private void init() {
        runs.add(new Run(1, "Morning Run", LocalDateTime.now().minusHours(1), LocalDateTime.now(), 5, Location.OUTDOOR));
        runs.add(new Run(2, "Evening Run", LocalDateTime.now().minusHours(2), LocalDateTime.now().minusHours(1), 10, Location.INDOOR));
        runs.add(new Run(3, "Night Run", LocalDateTime.now().minusHours(3), LocalDateTime.now().minusHours(2), 15, Location.OUTDOOR));
    }

    public Run findById(Integer id) {
        return runs.stream()
                .filter(run -> run.id().equals(id))
                .findFirst()
                .orElse(null);
    }
   
}
