package com.running.runnerz.run;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

public record Run(
  Integer id,
  @NotEmpty
  String title,
  LocalDateTime startTime,
  LocalDateTime endTime,
  @Positive
  Integer kms,
  Location location
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
 