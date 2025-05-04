package com.tara.itinerary.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class ActivityResponse {
	private String id;
    private String name;
    private String location;
    private Instant startTimeUtc;
    private Instant endTimeUtc;
    private String notes;
}
