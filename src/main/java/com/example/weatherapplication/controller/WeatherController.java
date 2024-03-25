package com.example.weatherapplication.controller;


import com.example.weatherapplication.entity.WeatherResponse;
import com.example.weatherapplication.service.WeatherService;
import com.example.weatherapplication.weatherDomain.WeatherRequestDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class WeatherController {

    private WeatherService weatherService;
    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather/{city_name}")
    public @ResponseBody WeatherResponse weather (@PathVariable("city_name") String city_name) throws Exception {

        final WeatherRequestDetails weatherRequestetails = WeatherRequestDetails.builder()
                .city_name(city_name)
                .build();

        return weatherService.getWeather(weatherRequestetails);
    }

}
