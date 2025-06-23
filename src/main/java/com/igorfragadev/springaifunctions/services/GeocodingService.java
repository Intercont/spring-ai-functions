package com.igorfragadev.springaifunctions.services;

import com.igorfragadev.springaifunctions.model.GeocodingLocation;
import com.igorfragadev.springaifunctions.model.WeatherRequest;
import com.igorfragadev.springaifunctions.model.WeatherResponse;

import java.util.List;
import java.util.Optional;

public interface GeocodingService {

    Optional<GeocodingLocation> getCoordinatesFromCityName(String cityName, String countryCode);
    List<GeocodingLocation> getAllMatchingLocations(String cityName, String countryCode, int count);
    WeatherResponse getWeatherByCity(WeatherRequest weatherRequest);
}
