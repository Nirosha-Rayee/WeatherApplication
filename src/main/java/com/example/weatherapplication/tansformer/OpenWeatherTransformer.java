package com.example.weatherapplication.tansformer;

import com.example.weatherapplication.entity.OpenWeatherResponseEntity;
import com.example.weatherapplication.entity.WeatherResponse;
import com.example.weatherapplication.weatherDomain.CityWeather;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherTransformer {
    public CityWeather transformToDomain(final OpenWeatherResponseEntity entity) {
        return CityWeather.builder()
                .weather(entity.getWeather()[0].getMain())
                .details(entity.getWeather()[0].getDescription())
//                .temp(entity.getMain().getTemp())
//                .feelsLike(entity.getMain().getFeels_like())

                .build();

    }
    public WeatherResponse transformToEntity(final CityWeather cityWeather) {
        return WeatherResponse.builder()
                .weather(cityWeather.getWeather())
                .details(cityWeather.getDetails())
//                .temp(cityWeather.getTemp())
//                .feelsLike(cityWeather.getFeelsLike())
                .build();
    }
}
