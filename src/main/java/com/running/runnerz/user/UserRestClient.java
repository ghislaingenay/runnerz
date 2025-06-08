package com.running.runnerz.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class UserRestClient {
  private final RestClient restClient;

  public UserRestClient(
      RestClient.Builder restClient,
      @Value("${user.api.base-url}") String baseUrl) {
    this.restClient = restClient.baseUrl(baseUrl).build();
  }

}
