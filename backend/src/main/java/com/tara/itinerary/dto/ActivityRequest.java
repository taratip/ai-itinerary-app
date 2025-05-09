package com.tara.itinerary.dto;

import lombok.Data;

@Data
public class ActivityRequest {
    private String name;
    private String time;
    private String location;
    private String description;
    private String category;
    private String notes;
}
