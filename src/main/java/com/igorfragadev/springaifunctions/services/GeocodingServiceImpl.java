package com.igorfragadev.springaifunctions.services;

import com.igorfragadev.springaifunctions.model.GeocodingLocation;
import com.igorfragadev.springaifunctions.model.GeocodingResponse;
import com.igorfragadev.springaifunctions.model.WeatherRequest;
import com.igorfragadev.springaifunctions.model.WeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeocodingServiceImpl implements GeocodingService {

    private static final String GEOCODING_API_URL = "https://geocoding-api.open-meteo.com/v1";
    private static final String WEATHER_URL = "https://api.api-ninjas.com/v1/weather";

    @Value("${sfg.aiapp.apiNinjasKey}")
    private String apiNinjasKey;
    
    public Optional<GeocodingLocation> getCoordinatesFromCityName(String cityName, String countryCode) {
        try {
            GeocodingResponse response = buildRequest(cityName, countryCode, 1);

            if (Objects.nonNull(response.results()) && !response.results().isEmpty()) {
                return response.results().stream().findFirst();
            }

            return Optional.empty();

        } catch (Exception e) {
//            log.error("Error calling geocoding API: {}", e.getMessage());
            return Optional.empty();
        }
    }

    public List<GeocodingLocation> getAllMatchingLocations(String cityName, String countryCode, int count) {
        try {
            GeocodingResponse response = buildRequest(cityName, countryCode, count);

            return Objects.nonNull(response.results()) ?
                    response.results() : List.of();

        } catch (Exception e) {
//            log.error("Error calling geocoding API: {}", e.getMessage());
            return List.of();
        }
    }

    @Override
    public WeatherResponse getWeatherByCity(WeatherRequest weatherRequest) {
        //ApiNinjas search by city name is only on premium subscription now, so I'm retrieving the latitude and longitude from Open Meteo API
        Optional<GeocodingLocation> location = this.getCoordinatesFromCityName(weatherRequest.city(), weatherRequest.countryCode());

        if (location.isPresent()) {
            RestClient restClient = RestClient.builder()
                    .baseUrl(WEATHER_URL)
                    .defaultHeaders(httpHeaders -> {
                        httpHeaders.set("X-Api-Key", apiNinjasKey);
                        httpHeaders.set("Accept", "application/json");
                        httpHeaders.set("Content-Type", "application/json");
                    }).build();

            return restClient.get().uri(uriBuilder -> {
                System.out.println("Building URI for weather request: " + weatherRequest);

                uriBuilder.queryParam("lat", String.valueOf(location.get().latitude()));
                uriBuilder.queryParam("lon", String.valueOf(location.get().longitude()));
                return uriBuilder.build();
            }).retrieve().body(WeatherResponse.class);
        }

        System.out.println("No location found for city: " + weatherRequest.city());
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No location found for city: " + weatherRequest.city());
    }

    private GeocodingResponse buildRequest(String cityName, String countryCode, int count) {

        RestClient restClient = RestClient.builder()
                .baseUrl(GEOCODING_API_URL)
                .defaultHeader("Accept", "application/json")
                .defaultHeader("Content-Type", "application/json")
                .build();

        return restClient.get()
                .uri(uriBuilder -> {
                    uriBuilder.path("/search")
                            .queryParam("name", cityName)
                            .queryParam("count", count);

                    if (countryCode != null && !countryCode.isEmpty()) {
                        uriBuilder.queryParam("countryCode", countryCode);
                    }

                    return uriBuilder.build();
                }).retrieve().body(GeocodingResponse.class);
    }
}
