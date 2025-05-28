package com.tara.itinerary.dto;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItineraryResponse {
    private UUID id;
    private String title;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> travelers;
    private Instant createdAt;
    private Instant updatedAt;
    private List<DayPlanResponse> dayPlans;
    private String notes;
    private String budget;
    private List<String> interests;
}
