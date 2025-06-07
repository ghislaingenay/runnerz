package com.running.runnerz.run;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Run> findById(Integer id) {
        return runs.stream()
                .filter(run -> run.id().equals(id))
                .findFirst();
    }

    public void create(Run run) {
        runs.add(run);
    }

    public void update(Integer id, Run run) {
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            runs.remove(existingRun.get());
            runs.add(run);
        } else {
            throw new RunNotFoundException();
        }
    }
    public void delete(Integer id) {
        Optional<Run> existingRun = findById(id);
        if (existingRun.isPresent()) {
            runs.remove(existingRun.get());
        } else {
            throw new RunNotFoundException();
        }
    }
   
}
