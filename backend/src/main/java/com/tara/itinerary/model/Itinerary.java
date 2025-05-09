package com.tara.itinerary.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "itineraries")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary {
    @Id
    private UUID id;
    private String title;
    private String destination;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> travelers;
    private Instant createdAt;
    private Instant updatedAt;
    private List<DayPlan> days;
    private String notes;
}
