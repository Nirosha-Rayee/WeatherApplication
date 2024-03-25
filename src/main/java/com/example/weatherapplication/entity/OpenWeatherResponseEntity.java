package com.example.weatherapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class OpenWeatherResponseEntity {
    @JsonProperty("weather")
    private WeatherEntity[] weather;

//    @JsonProperty("hourly")
//    private HourlyEntity[] hourly;


}
