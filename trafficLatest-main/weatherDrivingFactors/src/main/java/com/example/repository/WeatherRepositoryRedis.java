// package com.example.repository;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.stereotype.Repository;
//
// import com.example.infrastructure.schema.WeatherSchema;
//
// import java.util.concurrent.TimeUnit;

// @Repository("redisWeatherRepository")
// public class WeatherRepositoryRedis {
//
//     private final RedisTemplate<String, WeatherSchema> redisTemplate;
//
//     @Autowired
//     public WeatherRepositoryRedis(RedisTemplate<String, WeatherSchema> redisTemplate) {
//         this.redisTemplate = redisTemplate;
//     }
//
//     public void saveWeatherData(WeatherSchema weatherData) {
//         redisTemplate.opsForValue().set(weatherData.getCity(), weatherData);
//         redisTemplate.expire(weatherData.getCity(), 1, TimeUnit.HOURS);
//     }
//
// }

