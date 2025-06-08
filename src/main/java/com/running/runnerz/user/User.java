package com.running.runnerz.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import jakarta.validation.constraints.NotEmpty;

public record User(
  @Id
  Integer id,
  @NotEmpty
  String name,
  @NotEmpty
  String username,
  @NotEmpty
  String email,
  @NotEmpty
  String password,
  Address address,
  String phone,
  String website,
  Company company,
  @Version
  Integer version
) {

}
