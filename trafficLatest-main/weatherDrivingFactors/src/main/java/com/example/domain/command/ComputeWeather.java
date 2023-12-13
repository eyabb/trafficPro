package com.example.domain.command;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ComputeWeather
 */
@Data
@AllArgsConstructor
public class ComputeWeather {
  private String city;
  private float precipitationForTheNextOneHour;
  private float snowForTheNextOneHour;
  private int visibility;
  private ArrayList<String>weatherDescriptions; 
  private double temperature;
  private int humidity;
}
