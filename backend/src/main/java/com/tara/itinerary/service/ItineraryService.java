package com.tara.itinerary.service;

import java.util.List;
import java.util.UUID;

import com.tara.itinerary.dto.ItineraryRequest;
import com.tara.itinerary.dto.ItineraryResponse;

public interface ItineraryService {
	ItineraryResponse createItinerary(ItineraryRequest request);

	ItineraryResponse getItinerary(UUID id);

	List<ItineraryResponse> getAllItineraries();

	ItineraryResponse updateItinerary(UUID id, ItineraryRequest itineraryRequest);

	void deleteItinerary(UUID id);
}
