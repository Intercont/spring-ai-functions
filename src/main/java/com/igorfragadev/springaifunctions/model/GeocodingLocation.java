package com.igorfragadev.springaifunctions.model;

public record GeocodingLocation(
        String name,
        double latitude,
        double longitude,
        String country,
        String admin1,
        String timezone) {
}
