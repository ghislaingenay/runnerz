package com.running.runnerz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Test // can I get the server running 
	void contextLoads() {
		// This test will pass if the application context loads successfully
		// It does not need to contain any assertions or logic
		// The purpose is to ensure that the application can start without errors
		// If there are issues with the configuration or dependencies, this test will fail
		// indicating that the application context could not be loaded.
	}

}
