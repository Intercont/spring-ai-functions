package com.igorfragadev.springaifunctions.functions;

import com.igorfragadev.springaifunctions.model.WeatherRequest;
import com.igorfragadev.springaifunctions.model.WeatherResponse;
import com.igorfragadev.springaifunctions.services.GeocodingService;

import java.util.function.Function;

public class WeatherServiceFunction implements Function<WeatherRequest, WeatherResponse> {

    private final GeocodingService geocodingService;

    public WeatherServiceFunction(GeocodingService geocodingService) {
        this.geocodingService = geocodingService;
    }

    @Override
    public WeatherResponse apply(WeatherRequest weatherRequest) {
        return geocodingService.getWeatherByCity(weatherRequest);
    }
}
