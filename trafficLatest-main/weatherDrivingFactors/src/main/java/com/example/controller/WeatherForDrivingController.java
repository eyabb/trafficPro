package com.example.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.applciationService.WeatherForDrivingService;
import com.example.domain.command.ComputeWeather;
import com.example.infrastructure.dto.Rain;
import com.example.infrastructure.dto.Snow;
import com.example.infrastructure.dto.WeatherDto;
import com.example.infrastructure.schema.WeatherSchema;
import java.util.HashMap;
import java.util.Map;

/**
 * WeaterController
 */
@RestController
public class WeatherForDrivingController {
  @Autowired
  private WeatherForDrivingService weatherForDrivingService;
  private final RestTemplate restTemplate = new RestTemplate();
  private Map<String, Map<String, Double>> cityCoordinates = new HashMap<>();

  @PostMapping("/weather/{city}")
  public ResponseEntity<String> computeWeather(@PathVariable String city) {
    System.out.println("hey");
    Map<String, Double> sfaxCoords = new HashMap<>();
    sfaxCoords.put("latitude", 34.7406);
    sfaxCoords.put("longitude", 10.7601);
    cityCoordinates.put("Sfax", sfaxCoords);
    Map<String, Double> tunisCoords = new HashMap<>();
    tunisCoords.put("latitude", 34.0479);
    tunisCoords.put("longitude", 10.6197);
    cityCoordinates.put("Tunis", tunisCoords);
    Double selectedCityLatitude = cityCoordinates.get("Tunis").get("latitude");
    Double selectedCityLongitude = cityCoordinates.get("Tunis").get("longitude");
    Map<String, Double> selectedCityCoordinates = cityCoordinates.get(city);
    if (selectedCityCoordinates != null) {
       selectedCityLatitude = selectedCityCoordinates.get("latitude");
       selectedCityLongitude = selectedCityCoordinates.get("longitude");
    }
    String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + selectedCityLatitude
        + "&lon=" + selectedCityLongitude +  "&appid=0917ed7b90429faccd44fe6c5915fd63&lang=ar";
    try {
      ResponseEntity<WeatherDto> response = this.restTemplate.getForEntity(url, WeatherDto.class);
      if (response.getStatusCode().is2xxSuccessful()) {
        WeatherDto weatherDto = response.getBody();
        ArrayList<String> descriptions = new ArrayList<>();
        for (int i = 0; i < weatherDto.getWeather().length; i++) {
          descriptions.add(weatherDto.getWeather()[i].getDescription());

        }
        Snow snow = weatherDto.getSnow();
        float volumeOfSnowForTheLastHourInMm = 0.0f;
        if (snow != null) {
          volumeOfSnowForTheLastHourInMm = snow.getVolumeOfSnowForTheLastHourInMm();
        }
        Rain rain = weatherDto.getRain();
        float volumeOfRainForTheLastHourInMm = 0.0f;
        if (rain != null) {
          volumeOfSnowForTheLastHourInMm = rain.getVolumeOfRainForTheLastHourInMm();
        }
        ComputeWeather computeWeatherCommand = new ComputeWeather(city, volumeOfRainForTheLastHourInMm,
            volumeOfSnowForTheLastHourInMm, weatherDto.getVisibility(), descriptions, weatherDto.getTemp(),
            weatherDto.getHumidity());
        boolean isFoggy = weatherForDrivingService.isFoggy(computeWeatherCommand);
        return ResponseEntity.ok("Weather data computed successfully.");

      } else {
        throw new Exception("problem with the service");
      }

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred");
    }
  }

  @GetMapping("/weather/{city}")
  public WeatherSchema getWeather(@PathVariable String city) {
    return weatherForDrivingService.getWeather(city);
  }
}
