package com.tara.itinerary.service;

import com.tara.itinerary.dto.ItineraryRequest;
import com.tara.itinerary.dto.ItineraryResponse;

public interface ItineraryService {
	ItineraryResponse createItinerary(ItineraryRequest request);
}
