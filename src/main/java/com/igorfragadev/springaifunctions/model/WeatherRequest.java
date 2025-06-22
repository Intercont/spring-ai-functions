package com.igorfragadev.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record WeatherRequest(
        @JsonProperty(required = true, value = "location")
        @JsonPropertyDescription("The city and state e.g. Orlando, FL ") String location,
        @JsonProperty(required = false, value = "state")
        @JsonPropertyDescription("Optional state for US cities only") String state,
        @JsonProperty(required = false, value = "country")
        @JsonPropertyDescription("Optional country name") String country) {
}
