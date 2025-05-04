package com.tara.itinerary.model;

import lombok.Data;

@Data
public class Activity {
	private String time; // Could use LocalTime
    private String title;
    private String location;
    private String description;
    private String category;
    private String notes;
}
