package com.tara.itinerary.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ItineraryRequest {
    private String title;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> travelers;
    private List<DayPlanRequest> dayPlans;
    private String notes;
}
