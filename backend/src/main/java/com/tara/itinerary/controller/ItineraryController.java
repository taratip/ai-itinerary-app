package com.tara.itinerary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tara.itinerary.dto.ItineraryRequest;
import com.tara.itinerary.dto.ItineraryResponse;
import com.tara.itinerary.service.ItineraryService;

@RestController
@RequestMapping("/api/itineraries")
@CrossOrigin(origins = "*")
public class ItineraryController {
	@Autowired
	private ItineraryService itineraryService;

	@PostMapping
	public ResponseEntity<ItineraryResponse> createItinerary(@RequestBody ItineraryRequest itineraryRequest) {
		return ResponseEntity.ok(itineraryService.createItinerary(itineraryRequest));
	}
}
