package com.running.runnerz.run;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

@Repository
public class JdbcClientRunRepository {
    private static final Logger log = LoggerFactory.getLogger(JdbcClientRunRepository.class);
    private final JdbcClient jdbcClient;

    public JdbcClientRunRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
        log.info("JdbcClientRunRepository initialized with JdbcClient: {}", jdbcClient);
    }

    public List<Run> findAll() {
        return jdbcClient.sql("SELECT * FROM runs").query(Run.class).list();
    }

    public Optional<Run> findById(Integer id) {
        return jdbcClient.sql("SELECT * FROM runs WHERE id = :id")
                .param("id", id)
                .query(Run.class).optional();
    }
    public void create(Run run) {
        var updated = jdbcClient.sql("INSERT INTO runs (title, start_time, end_time, kms, location) VALUES (:title, :startTime, :endTime, :kms, :location)")
                .param("title", run.title())
                .param("startTime", run.startTime())
                .param("endTime", run.endTime())
                .param("kms", run.kms())
                .param("location", run.location())
                .update();
        Assert.state(updated == 1, "Expected to insert exactly one run, but updated count was: " + run.title());
        log.info("Run created: {}", run);                
    }
    public void update(Integer id, Run run) {
        jdbcClient.sql("UPDATE runs SET title = :title, start_time = :startTime, end_time = :endTime, kms = :kms, location = :location WHERE id = :id")
                .param("id", id)
                .param("title", run.title())
                .param("startTime", run.startTime())
                .param("endTime", run.endTime())
                .param("kms", run.kms())
                .param("location", run.location())
                .update();
    }
    public void delete(Integer id) {
        jdbcClient.sql("DELETE FROM runs WHERE id = :id")
                .param("id", id)
                .update();
        log.info("Run with id {} deleted", id);
    }

    public int count() {
        return jdbcClient.sql("SELECT COUNT(*) FROM runs")
                .query().listOfRows().size();
    }

    public void saveAll(List<Run> runs) {
        Assert.notEmpty(runs, "Runs list must not be empty");
        for (Run run : runs) {
            create(run);
        }
        log.info("Saved {} runs", runs.size());
    }

    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("SELECT * FROM runs WHERE location = :location")
                .param("location", location)
                .query(Run.class).list();
    }
}
