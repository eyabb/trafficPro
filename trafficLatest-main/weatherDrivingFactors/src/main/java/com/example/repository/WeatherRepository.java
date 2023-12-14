package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.infrastructure.schema.WeatherSchema;

/**
 * WeatherRepository
 */
@Repository("weatherRepository")
public interface WeatherRepository extends CrudRepository<WeatherSchema, String> {}
