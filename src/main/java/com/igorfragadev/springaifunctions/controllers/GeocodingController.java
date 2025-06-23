package com.igorfragadev.springaifunctions.controllers;

import com.igorfragadev.springaifunctions.model.GeocodingLocation;
import com.igorfragadev.springaifunctions.model.WeatherRequest;
import com.igorfragadev.springaifunctions.model.WeatherResponse;
import com.igorfragadev.springaifunctions.services.GeocodingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/geocoding")
public class GeocodingController {

    private GeocodingServiceImpl geocodingService;

    @GetMapping("/coordinates")
    public ResponseEntity<GeocodingLocation> getCoordinates(
            @RequestParam String cityName,
            @RequestParam(required = false) String countryCode) {

        Optional<GeocodingLocation> location = geocodingService.getCoordinatesFromCityName(cityName, countryCode);

        return location.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<GeocodingLocation>> searchLocations(
            @RequestParam String cityName,
            @RequestParam(required = false) String countryCode,
            @RequestParam(defaultValue = "5") int count) {

        List<GeocodingLocation> locations = geocodingService.getAllMatchingLocations(cityName, countryCode, count);
        return ResponseEntity.ok(locations);
    }

    @GetMapping("/weather")
    public ResponseEntity<WeatherResponse> getWeatherTest(
            @RequestBody WeatherRequest weatherRequest) {

        WeatherResponse weather = geocodingService.getWeatherByCity(weatherRequest);

        return ResponseEntity.ok(weather);
    }
}
