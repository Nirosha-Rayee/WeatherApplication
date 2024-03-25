package com.example.weatherapplication.tansformer;

import com.example.weatherapplication.entity.OpenWeatherResponseEntity;
import com.example.weatherapplication.entity.WeatherEntity;
import com.example.weatherapplication.entity.WeatherResponse;
import com.example.weatherapplication.weatherDomain.CityWeather;
import io.micrometer.observation.Observation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OpenWeatherTransformerTest {
    private OpenWeatherTransformer transformer;

    @BeforeEach
    public void setUp() {
        transformer = new OpenWeatherTransformer();
    }

    @Test
    public void test_should_transform_open_weather_response_entity_to_domain() {

        final WeatherEntity weather = WeatherEntity.builder()
                .main("cloudy")
                .description("cloudy")
                .build();

        final WeatherEntity[] weatherEntities = {weather};

        final OpenWeatherResponseEntity entity  = OpenWeatherResponseEntity.builder()

                .weather(weatherEntities)
                .build();

        final CityWeather cityWeather = transformer.transformToDomain(entity);

        assertAll( "should return domain weather object",
                () -> assertEquals(entity.getWeather()[0].getMain(), cityWeather.getWeather()),
                () -> assertEquals(entity.getWeather()[0].getDescription(), cityWeather.getWeather()));
    }


    @Test
    public void test_should_transform_city_weather_to_entity() {
        final CityWeather cityWeather = CityWeather.builder()
                .weather("Rainy")
                .details("A little bit of rain")
                .build();

        final WeatherResponse entity = transformer.transformToEntity(cityWeather);

        assertAll( "should return entity weather response object",
                () -> assertEquals(cityWeather.getWeather(), entity.getWeather()),
                () -> assertEquals(cityWeather.getDetails(), entity.getDetails()));

    }


}