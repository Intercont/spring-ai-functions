package com.igorfragadev.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;

public record WeatherResponse (
        @JsonPropertyDescription("Windspeed in KMH") BigDecimal wind_speed,
        @JsonPropertyDescription("Direction of the wind") Integer wind_degrees,
        @JsonPropertyDescription("Current temperature in Celsius") Integer temp,
        @JsonPropertyDescription("Current humidity") Integer humidity,
        @JsonPropertyDescription("Sunset time in Unix timestamp") Integer sunset,
        @JsonPropertyDescription("Minimum temperature in Celsius") Integer min_temp,
        @JsonPropertyDescription("Maximum temperature in Celsius.") Integer max_temp,
        @JsonPropertyDescription("Cloud coverage percentage") Integer cloud_pct,
        @JsonPropertyDescription("What the temperature feels like in Celsius.") Integer feels_like,
        @JsonPropertyDescription("Sunrise time in Unix timestamp") Integer sunrise
        ){
}
