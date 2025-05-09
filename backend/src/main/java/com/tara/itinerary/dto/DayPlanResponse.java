package com.tara.itinerary.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class DayPlanResponse {
    private UUID id;
    private LocalDate date;
    private List<ActivityResponse> activities;
}
