package com.igorfragadev.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record StockPriceRequest(
        @JsonProperty(required = true, value = "ticker")
        @JsonPropertyDescription("Stock or index ticker symbol (e.g., AAPL or VT).") String ticker) {
}
