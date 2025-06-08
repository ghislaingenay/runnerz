package com.running.runnerz.run;

import java.io.InputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.running.runnerz.utils.JsonParser;

@Component
public class RunJsonDataLoader implements  CommandLineRunner {

    private final RunRepository runRepository;
    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final JsonParser jsonParser = new JsonParser();

    public RunJsonDataLoader(RunRepository runRepository) {
        this.runRepository = runRepository;
    }

    @Override
    public void run(String... args) throws Exception {
      if (runRepository.count() > 0) {
            log.info("Run repository already contains data, skipping JSON data loading.");
            return;
        } else {
        log.info("Loading runs from JSON file...");
        // Load JSON data and save to repository
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/runs.json")) {
            if (inputStream == null) {
                log.error("Could not find runs.json file in resources.");
                return;
            }
            // Parse the JSON file into a list of Run objects
            List<Run> runs = jsonParser.parseJson(inputStream.toString(), new TypeReference<List<Run>>() {});
            runRepository.saveAll(runs);
            log.info("Successfully loaded {} runs from JSON file.", runs.size());
        } catch (Exception e) {
            log.error("Error loading runs from JSON file: {}", e.getMessage());
        }
        // This is where you would implement the logic to read from a JSON file
        // and save the runs to the repository.
        // For example:
        // List<Run> runs = loadRunsFromJson();
        // runRepository.saveAll(runs);
        }
    }

}
