package com.tara.itinerary.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tara.itinerary.dto.ItineraryRequest;
import com.tara.itinerary.dto.ItineraryResponse;
import com.tara.itinerary.service.ItineraryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/itineraries")
@CrossOrigin(origins = "*")
public class ItineraryController {
	@Autowired
	private ItineraryService itineraryService;

	@PostMapping
	public ResponseEntity<ItineraryResponse> createItinerary(@Valid @RequestBody ItineraryRequest itineraryRequest) {
		return ResponseEntity.ok(itineraryService.createItinerary(itineraryRequest));
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItineraryResponse> getItinerary(@PathVariable UUID id) {
		return ResponseEntity.ok(itineraryService.getItinerary(id));
	}

	@GetMapping
	public ResponseEntity<List<ItineraryResponse>> getAllItineraries() {
		return ResponseEntity.ok(itineraryService.getAllItineraries());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ItineraryResponse> updateItinerary(
			@PathVariable UUID id,
			@Valid @RequestBody ItineraryRequest itineraryRequest) {
		return ResponseEntity.ok(itineraryService.updateItinerary(id, itineraryRequest));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteItinerary(@PathVariable UUID id) {
		itineraryService.deleteItinerary(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/generate")
	public ResponseEntity<ItineraryResponse> generateItinerary(@RequestBody ItineraryRequest itineraryRequest) {
		return ResponseEntity.ok(itineraryService.generateItinerary(itineraryRequest));
	}
}
