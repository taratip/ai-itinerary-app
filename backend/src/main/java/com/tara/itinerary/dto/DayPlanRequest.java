package com.tara.itinerary.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DayPlanRequest {
    @NotNull(message = "Date is required")
    private LocalDate date;

    private List<ActivityRequest> activities;
}
