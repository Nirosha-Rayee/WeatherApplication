package com.example.weatherapplication.service;

import com.example.weatherapplication.entity.GeoCodingCoordinatesEntity;
import com.example.weatherapplication.entity.OpenWeatherResponseEntity;
import com.example.weatherapplication.entity.WeatherEntity;
import com.example.weatherapplication.entity.WeatherResponse;
import com.example.weatherapplication.provider.GeocodingProvider;
import com.example.weatherapplication.provider.WeatherProvider;
import com.example.weatherapplication.tansformer.GeoCodingCoordinatesTransformer;
import com.example.weatherapplication.tansformer.OpenWeatherTransformer;
import com.example.weatherapplication.weatherDomain.CityCoordinates;
import com.example.weatherapplication.weatherDomain.WeatherRequestDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(WeatherService.class)
class WeatherServiceTest {

    public static final String LATITUDE = "51.5074";
    public static final String LONGITUDE = "0.1278";
    public static final String WEATHER = "cloudy";
    public static final String DETAILS = "cloudy";
    public static final String CITY_NAME = "London";

    @MockBean
    private GeocodingProvider geocodingProvider;
    @MockBean
    private WeatherProvider weatherProvider;
    @MockBean
    private GeoCodingCoordinatesTransformer geoCodingCoordinatesTransformer;
    @MockBean
    private OpenWeatherTransformer openWeatherTransformer;

    @InjectMocks
    private WeatherService weatherService;

    @Test
    public void test_should_return_weather_response() throws Exception {

        final WeatherRequestDetails weatherRequestDetails = WeatherRequestDetails.builder()
                .city_name(CITY_NAME)
                .build();

        mockGeocodingProvider(weatherRequestDetails);
        mockGeocodingCoordinatesTransformer();
        mockWeatherProvider();
        mockOpenWeatherTransformer();

        final WeatherResponse weatherResponse = weatherService.getWeather(weatherRequestDetails);

        assertAll("should return city weather response",
                () -> assertEquals(WEATHER, weatherResponse.getWeather()),
                () -> assertEquals(DETAILS, weatherResponse.getDetails()));

        when(geocodingProvider.getCoordinates(weatherRequestDetails)).thenThrow(new Exception("error"));
    }

    private void mockOpenWeatherTransformer() {

        final WeatherResponse weatherResponse = WeatherResponse.builder()
                .weather(WEATHER)
                .details(DETAILS)
                .build();

        when(openWeatherTransformer.transformToEntity(any())).thenReturn(weatherResponse);
    }

    private void mockWeatherProvider() throws Exception {
        final WeatherEntity weather = WeatherEntity.builder()
                .main(WEATHER)
                .description(DETAILS)
                .build();

        final WeatherEntity[] weatherEntities = {weather};

        final OpenWeatherResponseEntity entity  = OpenWeatherResponseEntity.builder()

                .weather(weatherEntities)
                .build();

        when(weatherProvider.getWeather(any())).thenReturn(entity);
    }

    private void mockGeocodingCoordinatesTransformer() {
        final CityCoordinates cityCoordinates = CityCoordinates.builder()
                .latitude(LATITUDE)
                .longitude(LONGITUDE)
                .build();

        when(geoCodingCoordinatesTransformer.transformToDomain(any())).thenReturn(cityCoordinates);
    }

    private void mockGeocodingProvider(WeatherRequestDetails weatherRequestDetails) throws Exception {
        final GeoCodingCoordinatesEntity entity = GeoCodingCoordinatesEntity.builder()
                .latitude(LATITUDE)

                .longitude(LONGITUDE)
                .build();

        when(geocodingProvider.getCoordinates(weatherRequestDetails)).thenReturn(entity);
    }

}