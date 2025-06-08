package com.running.runnerz.user;

import java.util.List;

import org.springframework.web.service.annotation.GetExchange;

public interface UserHttpClient {
  @GetExchange("/users/{id}")
  User findById(Integer id);


  @GetExchange("/users")
  List<User> getAllUsers();

}
