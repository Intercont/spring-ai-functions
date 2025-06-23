package com.igorfragadev.springaifunctions.model;

import java.util.List;

public record GeocodingResponse(List<GeocodingLocation> results) {
}
