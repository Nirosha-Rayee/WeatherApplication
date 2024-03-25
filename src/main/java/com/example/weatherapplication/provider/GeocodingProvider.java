package com.example.weatherapplication.provider;

import com.example.weatherapplication.entity.GeoCodingCoordinatesEntity;
import com.example.weatherapplication.weatherDomain.WeatherRequestDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class GeocodingProvider {
    @Value("${api.key}")
    private String apiKey;

    @Value("${geocoding.url}")
    private String geocodingUrl;

    public GeoCodingCoordinatesEntity getCoordinates(final WeatherRequestDetails weatherRequestetails) throws Exception {
        //geo coding api call
        RestTemplate restTemplate = new RestTemplate();
        final ResponseEntity<GeoCodingCoordinatesEntity[]> responseEntity;
        HttpEntity<String> requestEntity = new HttpEntity<>(null, null);

        //build url
        UriComponents uriBuilder = UriComponentsBuilder.fromHttpUrl(geocodingUrl)
                .queryParam("q", weatherRequestetails.getCity_name())
                .queryParam("limit", 1)
                .queryParam("appid", apiKey)
                .build();

        try {
            responseEntity = restTemplate.
                    exchange(uriBuilder.toUriString(),
                            HttpMethod.GET, requestEntity, GeoCodingCoordinatesEntity[].class);
        }catch (HttpStatusCodeException e){
            throw new Exception(e.getMessage());
        }
        return responseEntity.getBody()[0];
    }
}

