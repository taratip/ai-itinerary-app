package com.tara.itinerary.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.tara.itinerary.dto.ActivityRequest;
import com.tara.itinerary.dto.ActivityResponse;
import com.tara.itinerary.dto.DayPlanRequest;
import com.tara.itinerary.dto.DayPlanResponse;
import com.tara.itinerary.dto.ItineraryRequest;
import com.tara.itinerary.dto.ItineraryResponse;
import com.tara.itinerary.model.Activity;
import com.tara.itinerary.model.DayPlan;
import com.tara.itinerary.model.Itinerary;

@Mapper(componentModel = "spring")
public interface ItineraryMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "dayPlans", target = "days")
    Itinerary toEntity(ItineraryRequest request);

    @Mapping(source = "days", target = "dayPlans")
    ItineraryResponse toDto(Itinerary entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(source = "dayPlans", target = "days")
    void updateEntityFromDto(ItineraryRequest request, @MappingTarget Itinerary entity);

    @Mapping(target = "id", ignore = true)
    DayPlan toDayPlanEntity(DayPlanRequest request);

    DayPlanResponse toDayPlanDto(DayPlan entity);

    @Mapping(target = "id", ignore = true)
    Activity toActivityEntity(ActivityRequest request);

    ActivityResponse toActivityDto(Activity entity);
}