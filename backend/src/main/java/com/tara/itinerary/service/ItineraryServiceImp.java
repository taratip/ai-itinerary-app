package com.tara.itinerary.service;

import java.time.Instant;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.tara.itinerary.dto.ItineraryRequest;
import com.tara.itinerary.dto.ItineraryResponse;
import com.tara.itinerary.model.Itinerary;
import com.tara.itinerary.repository.ItineraryRepository;

@Service
public class ItineraryServiceImp implements ItineraryService {
	private final ModelMapper modelMapper;
	private final ItineraryRepository itineraryRepository;

	public ItineraryServiceImp(ItineraryRepository itineraryRepository, ModelMapper modelMapper) {
		this.itineraryRepository = itineraryRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ItineraryResponse createItinerary(ItineraryRequest request) {
		Itinerary itinerary = modelMapper.map(request, Itinerary.class);
		itinerary.setCreatedAt(Instant.now());
		itinerary.setUpdatedAt(Instant.now());

		Itinerary savedItinerary = itineraryRepository.save(itinerary);
		return modelMapper.map(savedItinerary, ItineraryResponse.class);
	}
}
