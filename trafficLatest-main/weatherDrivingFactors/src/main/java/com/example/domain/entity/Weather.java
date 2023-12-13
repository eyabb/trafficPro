package com.example.domain.entity;

/**
 * Weather
 */

public class Weather {

  double temperature;
  int humidity;

  public Weather(double temperature, int humidity) {
    this.temperature = temperature;
    this.humidity = humidity;
  }

  private double computeTheDewPoint() {
    double exponentArg = 17.67 * temperature / (temperature + 243.5);
    double saturationVaporPressure = 6.112 * exponentArg;
    double actualVaporPressure = (humidity * saturationVaporPressure) / 100;
    double relativeHumidity = (actualVaporPressure / saturationVaporPressure) * 100;
    double dewPoint = temperature - ((100 - relativeHumidity) / 5);
    return dewPoint;
  }

  public boolean isFoggy() {
    double dewPoint = computeTheDewPoint();
    return dewPoint - 2 <= temperature && temperature <= dewPoint;
  }

}
