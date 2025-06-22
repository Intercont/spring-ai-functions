package com.igorfragadev.springaifunctions.services;

import com.igorfragadev.springaifunctions.model.GeocodingLocation;
import com.igorfragadev.springaifunctions.model.GeocodingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GeocodingServiceRestClientImpl implements GeocodingService {

    private static final String GEOCODING_API_URL = "https://geocoding-api.open-meteo.com/v1";
    
    public Optional<GeocodingLocation> getCoordinatesFromCityName(String cityName) {
        return getCoordinatesFromCityName(cityName, null, 1);
    }

    public Optional<GeocodingLocation> getCoordinatesFromCityName(String cityName, String countryCode) {
        return getCoordinatesFromCityName(cityName, countryCode, 1);
    }

    public Optional<GeocodingLocation> getCoordinatesFromCityName(String cityName, String countryCode, int count) {
        try {
            GeocodingResponse response = buildRequest(cityName, countryCode, count);

            if (response.results() != null && !response.results().isEmpty()) {
                return Optional.of(response.results().get(0));
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

            return response.results() != null ?
                    response.results() : List.of();

        } catch (Exception e) {
//            log.error("Error calling geocoding API: {}", e.getMessage());
            return List.of();
        }
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
