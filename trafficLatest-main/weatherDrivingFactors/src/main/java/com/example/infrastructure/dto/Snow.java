package com.example.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
 * Snow
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Snow {
  @JsonProperty("1h")
  private float volumeOfSnowForTheLastHourInMm;

  @JsonProperty("3h")
  private float volumeOfSnowForTheLastThreeHoursInMm;
}
