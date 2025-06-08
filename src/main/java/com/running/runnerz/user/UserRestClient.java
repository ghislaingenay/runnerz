package com.running.runnerz.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserRestClient {
  private final RestClient restClient;

  public UserRestClient(
      RestClient.Builder restClient,
      @Value("${user.api.base-url}") String baseUrl) {
        JdkClientHttpRequestFactory jdkClientHttpRequestFactory = new JdkClientHttpRequestFactory();
        jdkClientHttpRequestFactory.setReadTimeout(5000); // Set read timeout to 5 seconds
        // requestFactory = This method configures the underlying HTTP client implementation that the RestClient will use to perform HTTP requests.
        // new JdkClientHttpRequestFactory() => This is a specific implementation of a ClientHttpRequestFactory, using the JDK’s built-in HttpURLConnection mechanism.

        // can add default headers, interceptors
    this.restClient = restClient.baseUrl(baseUrl).requestFactory(jdkClientHttpRequestFactory).build();
  }

  public List<User> getAllUsers() {
    return restClient.get()
        .uri("/users")
        .retrieve()
        .body(new ParameterizedTypeReference<>() {
          
        });
  }

  public User findById(Integer id) {
    return restClient.get()
        .uri("/users/{id}", id)
        .retrieve()
        // .body(new ParameterizedTypeReference<User>() {});
        .body(User.class);
  }

}
