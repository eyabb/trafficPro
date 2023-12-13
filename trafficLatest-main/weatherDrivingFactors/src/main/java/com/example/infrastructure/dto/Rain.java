package com.example.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Rain
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class Rain {
  @JsonProperty("1h")
  private float volumeOfRainForTheLastHourInMm;

  @JsonProperty("3h")
  private float volumeOfRainForTheLastThreeHoursInMm;
  
}
