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
public class GeoCodingCoordinatesEntity {
    @JsonProperty("lat")
    private String latitude;
    @JsonProperty("lon")
    private String longitude;

}
