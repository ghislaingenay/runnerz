package com.running.runnerz.run;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record Run(
  @Id
  Integer id,
  @NotEmpty
  String title,
  LocalDateTime startTime,
  LocalDateTime endTime,
  @Positive
  Integer kms,
  Location location,
  @Version
  Integer version
) {

  public Run {
    if (null == title || null == startTime || null == endTime || null == kms || null == location) {
      throw new IllegalArgumentException("Run fields cannot be null");
    }
    if (startTime.isAfter(endTime)) {
      throw new IllegalArgumentException("Start time cannot be after end time");
    }
    if (kms <= 0) {
      throw new IllegalArgumentException("Kms must be greater than zero");
    }
  }
}
 