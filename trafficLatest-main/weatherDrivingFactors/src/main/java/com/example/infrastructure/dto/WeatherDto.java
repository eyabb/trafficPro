package com.example.infrastructure.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * WeatherDto
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {
  private double temp;
  private int humidity;
  private int visibility;
  private WeatherInfo[] weather;
  private Snow snow;
  private Rain rain;
}
