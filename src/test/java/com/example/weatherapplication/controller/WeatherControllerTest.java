package com.example.weatherapplication.controller;

import com.example.weatherapplication.entity.WeatherResponse;
import com.example.weatherapplication.service.WeatherService;
import com.example.weatherapplication.weatherDomain.WeatherRequestDetails;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(WeatherController.class)
class WeatherControllerTest {
    public static final String CITY_NAME = "London";
    public static final String WEATHER = "sunny";
    public static final String DETAILS = "very sunny";
    @MockBean
    private WeatherService weatherService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_should_return_weather_response_success() throws Exception {
        final WeatherRequestDetails weatherRequestDetails = WeatherRequestDetails.builder()
                .city_name(CITY_NAME)
                .build();

        final WeatherResponse weatherResponse = WeatherResponse.builder()
                .weather(WEATHER)
                .details(DETAILS)
                .build();

        when(weatherService.getWeather(weatherRequestDetails)).thenReturn(weatherResponse);

        mockMvc.perform(get("/api/v1/weather/{city_name}", CITY_NAME)).andDo(print())
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.weather").value(WEATHER))
//                .andExpect(jsonPath("$.details").value(DETAILS));
    }


}