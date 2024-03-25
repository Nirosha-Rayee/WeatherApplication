package com.example.weatherapplication.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class HourlyEntity {

    @JsonProperty("temp")
    private double temp;

    @JsonProperty("feels_like")
    private double feelsLike;
}
