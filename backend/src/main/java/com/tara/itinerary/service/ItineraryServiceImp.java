package com.tara.itinerary.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.tara.itinerary.dto.ItineraryRequest;
import com.tara.itinerary.dto.ItineraryResponse;
import com.tara.itinerary.exception.ItineraryNotFoundException;
import com.tara.itinerary.mapper.ItineraryMapper;
import com.tara.itinerary.model.Itinerary;
import com.tara.itinerary.repository.ItineraryRepository;

@Service
public class ItineraryServiceImp implements ItineraryService {
	private final ItineraryRepository itineraryRepository;
	private final ItineraryMapper itineraryMapper;

	public ItineraryServiceImp(ItineraryRepository itineraryRepository, ItineraryMapper itineraryMapper) {
		this.itineraryRepository = itineraryRepository;
		this.itineraryMapper = itineraryMapper;
	}

	@Override
	public ItineraryResponse createItinerary(ItineraryRequest request) {
		Itinerary itinerary = itineraryMapper.toEntity(request);
		Itinerary savedItinerary = itineraryRepository.save(itinerary);

		return itineraryMapper.toDto(savedItinerary);
	}

	@Override
	public ItineraryResponse getItinerary(UUID id) {
		Itinerary itinerary = itineraryRepository.findById(id)
				.orElseThrow(() -> new ItineraryNotFoundException(
						"Itinerary not found with id: " + id));
		return itineraryMapper.toDto(itinerary);
	}

	@Override
	public List<ItineraryResponse> getAllItineraries() {
		List<Itinerary> itineraries = itineraryRepository.findAll();
		return itineraries.stream()
				.map(itinerary -> itineraryMapper.toDto(itinerary))
				.collect(Collectors.toList());
	}

	@Override
	public ItineraryResponse updateItinerary(UUID id, ItineraryRequest itineraryRequest) {
		Itinerary existingItinerary = itineraryRepository.findById(id)
				.orElseThrow(() -> new ItineraryNotFoundException(
						"Itinerary not found with id: " + id));
		itineraryMapper.updateEntityFromDto(itineraryRequest, existingItinerary);
		existingItinerary.setUpdatedAt(Instant.now());

		Itinerary updatedItinerary = itineraryRepository.save(existingItinerary);
		return itineraryMapper.toDto(updatedItinerary);
	}

	@Override
	public void deleteItinerary(UUID id) {
		Itinerary existingItinerary = itineraryRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND,
						"Itinerary not found with id: " + id));

		itineraryRepository.delete(existingItinerary);
	}
}
