package com.example.weatherapplication.tansformer;

import com.example.weatherapplication.entity.GeoCodingCoordinatesEntity;
import com.example.weatherapplication.weatherDomain.CityCoordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeoCodingCoordinatesTransformerTest {
    private GeoCodingCoordinatesTransformer transformer;

    @BeforeEach
    public void setUp() {
        transformer = new GeoCodingCoordinatesTransformer();
    }

    @Test
    public void test_should_transform_geo_coding_coordinates_to_main() {
       final GeoCodingCoordinatesEntity entity = GeoCodingCoordinatesEntity.builder()
               .longitude("12.0")
               .latitude("12.90")
               .build();
       final CityCoordinates cityCoordinates = transformer.transformToDomain(entity);

       assertAll( "should return domain city coordinates",
               () -> assertEquals(entity.getLatitude(), cityCoordinates.getLatitude()),
               () -> assertEquals(entity.getLongitude(), cityCoordinates.getLongitude()));
    }



}