package com.igorfragadev.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record WeatherRequest(
        @JsonProperty(required = true, value = "city")
        @JsonPropertyDescription("The city name in English e.g. Lisbon (and not Lisboa)") String city,
        @JsonProperty(value = "countryCode")
        @JsonPropertyDescription("Optional countryCode code e.g. PT") String countryCode) {
}
