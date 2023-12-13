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

/**
 * WeaterController
 */
@RestController
public class WeatherForDrivingController {
  @Autowired
  private WeatherForDrivingService weatherForDrivingService;
  private final RestTemplate restTemplate = new RestTemplate();
  
  @PostMapping("/weather/{city}")
  public ResponseEntity<String> computeWeather(@PathVariable String city) {
    System.out.println("hey");
    String url = "https://api.openweathermap.org/data/2.5/weather?lat=34.0479&lon=10.6197&appid=0917ed7b90429faccd44fe6c5915fd63&lang=ar";
    try {
      ResponseEntity<WeatherDto> response = this.restTemplate.getForEntity(url, WeatherDto.class);
      if (response.getStatusCode().is2xxSuccessful()) {
        WeatherDto weatherDto = response.getBody();
    ArrayList<String> descriptions = new ArrayList<>(); 
    for(int i=0; i < weatherDto.getWeather().length; i++){
      descriptions.add(weatherDto.getWeather()[i].getDescription());
              
    }
    Snow snow = weatherDto.getSnow();
    float volumeOfSnowForTheLastHourInMm = 0.0f;
    if(snow != null){
        volumeOfSnowForTheLastHourInMm = snow.getVolumeOfSnowForTheLastHourInMm();
    }
    Rain rain = weatherDto.getRain();
    float volumeOfRainForTheLastHourInMm = 0.0f;
    if(rain != null){
        volumeOfSnowForTheLastHourInMm = rain.getVolumeOfRainForTheLastHourInMm();
    }
        ComputeWeather computeWeatherCommand = new ComputeWeather(city,volumeOfRainForTheLastHourInMm,volumeOfSnowForTheLastHourInMm ,weatherDto.getVisibility(),descriptions,weatherDto.getTemp(),weatherDto.getHumidity());
        boolean isFoggy = weatherForDrivingService.isFoggy(computeWeatherCommand);
        return ResponseEntity.ok("Weather data computed successfully.");

      } else {
        throw new Exception("problem with the service");
      }

    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception occurred");
    }
  }
  @GetMapping("/hello")
    public String sayHello() {
        weatherForDrivingService.getWeather();
        return "Hello!";
    }

}
