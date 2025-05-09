package com.tara.itinerary.repository;

import com.tara.itinerary.model.Itinerary;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItineraryRepository extends MongoRepository<Itinerary, UUID> {

}
