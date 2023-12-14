package com.example.infrastructure.dto.soapRequest;

/**
 * ComputeWeatherRequest
 */
import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlRootElement(name = "ComputeWeatherRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class ComputeWeatherRequest {
  @XmlElement(name = "city")
      private String city;
}
