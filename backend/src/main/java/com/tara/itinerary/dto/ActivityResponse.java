package com.tara.itinerary.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class ActivityResponse {
    private UUID id;
    private String name;
    private String time;
    private String location;
    private String description;
    private String category;
    private String notes;
}
