package com.example.weatherapplication.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class WeatherResponse {
    private String weather;
    private String details;
//    private double temp;
//    private double feelsLike;

}