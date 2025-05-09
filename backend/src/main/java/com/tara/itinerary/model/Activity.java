package com.tara.itinerary.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity {
    @Id
    private UUID id;
    private String name;
    private String time;
    private String location;
    private String description;
    private String category;
    private String notes;
}
