package com.tara.itinerary.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tara.itinerary.dto.ItineraryRequest;
import com.tara.itinerary.dto.ItineraryResponse;
import com.tara.itinerary.service.ItineraryService;

@WebMvcTest(ItineraryController.class)
public class ItineraryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItineraryService itineraryService;

    @Autowired
    private ObjectMapper objectMapper;

    private ItineraryRequest request;
    private ItineraryResponse response;
    private UUID id;

    @BeforeEach
    void setUp() {
        id = UUID.randomUUID();

        request = new ItineraryRequest();
        request.setTitle("Test Itinerary");
        request.setDestination("Test Destination");
        request.setStartDate(LocalDate.now());
        request.setEndDate(LocalDate.now().plusDays(5));

        response = new ItineraryResponse();
        response.setId(id);
        response.setTitle("Test Itinerary");
        response.setDestination("Test Destination");
        response.setStartDate(LocalDate.now());
        response.setEndDate(LocalDate.now().plusDays(5));
    }

    @Test
    void whenCreateItinerary_thenReturnCreatedItinerary() throws Exception {
        when(itineraryService.createItinerary(any(ItineraryRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/itineraries")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.title").value("Test Itinerary"))
                .andExpect(jsonPath("$.destination").value("Test Destination"))
                .andExpect(jsonPath("$.startDate").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.endDate").value(LocalDate.now().plusDays(5).toString()));
    }

    @Test
    void whenGetItinerary_thenReturnItinerary() throws Exception {
        when(itineraryService.getItinerary(id)).thenReturn(response);

        mockMvc.perform(get("/api/itineraries/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.title").value("Test Itinerary"))
                .andExpect(jsonPath("$.destination").value("Test Destination"))
                .andExpect(jsonPath("$.startDate").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$.endDate").value(LocalDate.now().plusDays(5).toString()));
    }
}
