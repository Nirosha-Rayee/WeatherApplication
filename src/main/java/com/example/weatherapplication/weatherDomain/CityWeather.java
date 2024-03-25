package com.example.weatherapplication.weatherDomain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter


public class CityWeather {
    private String weather;
    private String details;
//    private double temp;
//    private double feelsLike;
}

