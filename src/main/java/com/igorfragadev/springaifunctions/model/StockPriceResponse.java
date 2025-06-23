package com.igorfragadev.springaifunctions.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;

public record StockPriceResponse(
        @JsonPropertyDescription("Stock or index ticker symbol (e.g., AAPL or VT).") String ticker,
        @JsonPropertyDescription("Stock or index name (e.g., Apple Inc).") String name,
        @JsonPropertyDescription("Current price (e.g., 201.8).") BigDecimal price,
        @JsonPropertyDescription("Exchange Stock or index, where this is negotiated.") String exchange,
        @JsonPropertyDescription("Updated time of this stock price in Epoch Unix timestamp.") Integer updated,
        @JsonPropertyDescription("Currency of this stock price") String currency) {
}
