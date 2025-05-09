package com.tara.itinerary.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class DayPlanRequest {
    private LocalDate date;
    private List<ActivityRequest> activities;
}
