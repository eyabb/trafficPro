package com.example.infrastructure.dto.soapResponse;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.*;
import lombok.Data;
/**
 * ComputeWeather
 */

@XmlRootElement(name = "WeatherResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ComputeWeatherResponse {
  @XmlElement(name = "city")
  private String city;
  @XmlElement(name = "precipitationForTheNextOneHour")
  private float precipitationForTheNextOneHour;
  @XmlElement(name = "snowForTheNextOneHour")
  private float snowForTheNextOneHour;
  @XmlElement(name = "visibility")
  private int visibility;
  @XmlElement(name = "isFoggy")
  private boolean isFoggy;
  @XmlElement(name = "weatherDescriptions")
  private ArrayList<String> weatherDescriptions;
}
