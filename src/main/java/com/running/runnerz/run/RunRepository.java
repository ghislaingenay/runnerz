package com.running.runnerz.run;

import java.time.LocalDateTime;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;

public interface RunRepository extends ListCrudRepository<Run, Integer> {

    @Query("UPDATE runs SET title = :title, start_time = :startTime, end_time = :endTime, kms = :kms, location = :location WHERE id = :id")
    void update(Integer id, String title, LocalDateTime startTime, LocalDateTime endTime, Integer kms, Location location);        // This method is used to update a run by its ID.

    
}
