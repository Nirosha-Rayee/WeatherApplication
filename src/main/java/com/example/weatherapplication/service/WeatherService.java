package com.example.weatherapplication.service;


import com.example.weatherapplication.entity.WeatherResponse;
import com.example.weatherapplication.provider.GeocodingProvider;
import com.example.weatherapplication.provider.WeatherProvider;
import com.example.weatherapplication.tansformer.GeoCodingCoordinatesTransformer;
import com.example.weatherapplication.tansformer.OpenWeatherTransformer;
import com.example.weatherapplication.weatherDomain.CityCoordinates;
import com.example.weatherapplication.weatherDomain.CityWeather;
import com.example.weatherapplication.weatherDomain.WeatherRequestDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private GeocodingProvider geocodingProvider;
    private GeoCodingCoordinatesTransformer geoCodingCoordinatesTransformer;
    private WeatherProvider weatherProvider;
    private OpenWeatherTransformer openWeatherTransformer;

    @Autowired
    public WeatherService(final GeocodingProvider geocodingProvider,
                          final GeoCodingCoordinatesTransformer geoCodingCoordinatesTransformer,
                          final WeatherProvider weatherProvider,
                          final OpenWeatherTransformer openWeatherTransformer) {
        this.geocodingProvider = geocodingProvider;
        this.geoCodingCoordinatesTransformer = geoCodingCoordinatesTransformer;
        this.weatherProvider = weatherProvider;
        this.openWeatherTransformer = openWeatherTransformer;
    }

    public WeatherResponse getWeather(final WeatherRequestDetails weatherRequestDetails) throws Exception{
        System.out.println("I got the request");

        //get latitute and longitude from city name

        final CityCoordinates cityCoordinates = geoCodingCoordinatesTransformer
                .transformToDomain(geocodingProvider.getCoordinates(weatherRequestDetails));

        //get weather for geo coordinates
        final CityWeather cityWeather = openWeatherTransformer
                .transformToDomain(weatherProvider.getWeather(cityCoordinates));

        return openWeatherTransformer.transformToEntity(cityWeather);
    }



}
