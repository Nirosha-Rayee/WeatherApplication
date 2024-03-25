package com.example.weatherapplication.tansformer;

import com.example.weatherapplication.entity.GeoCodingCoordinatesEntity;
import com.example.weatherapplication.weatherDomain.CityCoordinates;
import org.springframework.stereotype.Service;

@Service
public class GeoCodingCoordinatesTransformer {

    public CityCoordinates transformToDomain(final GeoCodingCoordinatesEntity entity){
        return CityCoordinates.builder()
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .build();

    }

}
