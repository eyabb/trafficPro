package com.example.infrastructure.schema;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

/**
 * WeatherSchema
 */
@RedisHash("WeatherSchema")
@Data
public class WeatherSchema implements Serializable {
  @Id
  private String city;
  private float precipitationForTheNextOneHour;
  private float snowForTheNextOneHour;
  private int visibility;
  private boolean isFoggy;
  private ArrayList<String> weatherDescriptions;
  
  public WeatherSchema(String city, float precipitationForTheNextOneHour, float snowForTheNextOneHour,
      int visibility, boolean isFoggy, ArrayList<String> weatherDescriptions) {
    this.city = city;
    this.precipitationForTheNextOneHour = precipitationForTheNextOneHour;
    this.snowForTheNextOneHour = snowForTheNextOneHour;
    this.visibility = visibility;
    this.isFoggy = isFoggy;
    this.weatherDescriptions = weatherDescriptions;
  }
}
