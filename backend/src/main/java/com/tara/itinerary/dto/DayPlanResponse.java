package com.tara.itinerary.dto;

import java.time.Instant;
import java.util.List;

import lombok.Data;

@Data
public class DayPlanResponse {
	private String id;
    private Instant dateUtc;
    private List<ActivityResponse> activities;
}
