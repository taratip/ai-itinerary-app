package com.tara.itinerary.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ActivityRequest {
    @NotBlank(message = "Activity name is required")
    private String name;

    private String time;

    @NotBlank(message = "Location is required")
    private String location;

    private String description;
    private String category;
    private String notes;
}
