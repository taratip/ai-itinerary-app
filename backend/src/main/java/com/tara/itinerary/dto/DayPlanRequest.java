package com.tara.itinerary.dto;

import java.time.Instant;
import java.util.List;

import lombok.Data;

@Data
public class DayPlanRequest {
	private Instant dateUtc;
    private List<ActivityRequest> activities;
}
