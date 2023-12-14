package com.example.applciationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.domain.command.ComputeWeather;
import com.example.domain.entity.Weather;
import com.example.infrastructure.schema.WeatherSchema;
import com.example.repository.WeatherRepository;

/**
 * WeatherForDrivingService
 */
@Service
public class WeatherForDrivingService {
  private final WeatherRepository weatherRepository;

  @Autowired
  public WeatherForDrivingService(@Qualifier("weatherRepository") WeatherRepository weatherRepository){
    this.weatherRepository = weatherRepository;
  }
  
  public boolean isFoggy(ComputeWeather computeWeatherCmd){
    Weather weather = new Weather(computeWeatherCmd.getTemperature(), computeWeatherCmd.getHumidity());
    WeatherSchema weatherSchema = new WeatherSchema(computeWeatherCmd.getCity(),computeWeatherCmd.getPrecipitationForTheNextOneHour(),computeWeatherCmd.getSnowForTheNextOneHour(),computeWeatherCmd.getVisibility(),weather.isFoggy(),computeWeatherCmd.getWeatherDescriptions());
     weatherRepository.save(weatherSchema);
    System.out.println(computeWeatherCmd.getWeatherDescriptions());

    return weather.isFoggy();
  }
  public WeatherSchema getWeather(String city){
    WeatherSchema weatherSchema = weatherRepository.findById(city).get();
    List<String> weatherDescriptions = weatherSchema.getWeatherDescriptions();
    for (String description : weatherDescriptions) {
        System.out.println(description);
    }
    return weatherSchema;
  }
  
}
