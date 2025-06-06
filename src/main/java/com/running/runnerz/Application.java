package com.running.runnerz;


import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.running.runnerz.run.Location;
import com.running.runnerz.run.Run;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		log.info("Application started successfully!");
	}

	// run after the application has started
	@Bean 
	CommandLineRunner runner() {
		return args -> {
			Run run = new Run(
				1,
				"Morning Run",
				LocalDateTime.now().minusHours(1),
				LocalDateTime.now(),
				5,
				Location.OUTDOOR
			);
			log.info(null != run ? "Run created: " + run : "Run creation failed");
		};
	}

}
