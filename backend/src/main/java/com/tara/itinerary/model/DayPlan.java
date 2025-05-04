package com.tara.itinerary.model;

import java.time.Instant;
import java.util.List;

import lombok.Data;

@Data
public class DayPlan {
	private Instant date;
    private List<Activity> activities;
}
