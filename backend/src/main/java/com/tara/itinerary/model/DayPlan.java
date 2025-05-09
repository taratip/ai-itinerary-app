package com.tara.itinerary.model;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayPlan {
    @Id
    private UUID id;
    private LocalDate date;
    private List<Activity> activities;
}
