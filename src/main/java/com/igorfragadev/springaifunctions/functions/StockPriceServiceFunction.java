package com.igorfragadev.springaifunctions.functions;

import com.igorfragadev.springaifunctions.model.StockPriceRequest;
import com.igorfragadev.springaifunctions.model.StockPriceResponse;
import org.springframework.web.client.RestClient;

import java.util.function.Function;

public class StockPriceServiceFunction implements Function<StockPriceRequest, StockPriceResponse> {

    private static final String STOCKPRICE_URL = "https://api.api-ninjas.com/v1/stockprice";
    private String apiNinjasKey;

    public StockPriceServiceFunction(String apiNinjasKey) {
        this.apiNinjasKey = apiNinjasKey;
    }

    @Override
    public StockPriceResponse apply(StockPriceRequest stockPriceRequest) {

        RestClient restClient = RestClient.builder()
                .baseUrl(STOCKPRICE_URL)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", apiNinjasKey);
                    httpHeaders.set("Accept", "application/json");
                    httpHeaders.set("Content-Type", "application/json");
                }).build();

        return restClient.get().uri(uriBuilder -> {
            System.out.println("Building URI for stock price request: " + stockPriceRequest);

            uriBuilder.queryParam("ticker", String.valueOf(stockPriceRequest.ticker()));
            return uriBuilder.build();
        }).retrieve().body(StockPriceResponse.class);
    }
}
