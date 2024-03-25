package com.example.weatherapplication.weatherDomain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class WeatherRequestDetails {
    private String city_name;
    //private LocalDate date;

}
