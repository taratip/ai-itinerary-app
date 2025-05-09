package com.tara.itinerary.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DayPlanRequest {
    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull(message = "Activities are required")
    @Size(min = 1, message = "At least one activity is required")
    @Valid
    private List<ActivityRequest> activities;
}
