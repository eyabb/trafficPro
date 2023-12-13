package com.example.controller;

import java.util.ArrayList;

/**
 * WeatherForDrivingSoa
 */
// WeatherSoapEndpoint.java

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.example.applciationService.WeatherForDrivingService;
import com.example.domain.command.ComputeWeather;
import com.example.infrastructure.dto.Rain;
import com.example.infrastructure.dto.Snow;
import com.example.infrastructure.dto.WeatherDto;
import com.example.infrastructure.dto.soapRequest.ComputeWeatherRequest;
import com.example.infrastructure.dto.soapResponse.ComputeWeatherResponse;

@Controller
@Endpoint
public class WeatherForDrivingSoa {

    private static final String NAMESPACE_URI = "http://example.com";

    private final WeatherForDrivingService weatherSoapService;
   private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public WeatherForDrivingSoa(WeatherForDrivingService weatherSoapService) {
        this.weatherSoapService = weatherSoapService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CityRequest")
    @ResponsePayload
    public ComputeWeatherResponse computeWeather(@RequestPayload ComputeWeatherRequest request) {
        String city = request.getCity();
        String url = "https://api.openweathermap.org/data/2.5/weather?lat=34.0479&lon=10.6197&appid=0917ed7b90429faccd44fe6c5915fd63&lang=ar";
      ComputeWeatherResponse computeWeatherResponse = new ComputeWeatherResponse();

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
        boolean isFoggy = weatherSoapService.isFoggy(computeWeatherCommand);
        computeWeatherResponse.setCity(city);
        computeWeatherResponse.setVisibility(weatherDto.getVisibility());
        computeWeatherResponse.setFoggy(isFoggy);
        computeWeatherResponse.setWeatherDescriptions(descriptions);
        computeWeatherResponse.setSnowForTheNextOneHour(volumeOfSnowForTheLastHourInMm);
        computeWeatherResponse.setPrecipitationForTheNextOneHour(volumeOfRainForTheLastHourInMm);

      } else {
        throw new Exception("problem with the service");
      }
    }
    catch(Exception e){
      System.out.println(e);
    }
        return computeWeatherResponse;

    }
}

