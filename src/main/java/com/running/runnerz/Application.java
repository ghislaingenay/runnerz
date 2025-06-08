package com.running.runnerz;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.running.runnerz.user.UserHttpClient;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {
		// Load .env variables before Spring starts
		Dotenv.configure().ignoreIfMissing().load();
		SpringApplication.run(Application.class, args);
		log.info("Application started successfully!");
	}

	@Bean
	public UserHttpClient userHttpClient(@Value("${user.api.base-url}") String baseUrl) {
		RestClient restClient = RestClient.create(baseUrl);
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(UserHttpClient.class);
	}

	// run after the application has started
	// @Bean 
	// public CommandLineRunner runner(JdbcClientRunRepository JdbcClientRunRepository) {
	// 	return args -> {
	// 		Run run = new Run(
	// 			1,
	// 			"Morning Run",
	// 			LocalDateTime.now().minusHours(1),
	// 			LocalDateTime.now(),
	// 			5,
	// 			Location.OUTDOOR
	// 		);
	// 		JdbcClientRunRepository.create(run);
	// 	};
	// }

}
