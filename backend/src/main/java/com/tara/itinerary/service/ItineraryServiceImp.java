package com.tara.itinerary.service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openai.client.OpenAIClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.tara.itinerary.dto.ItineraryRequest;
import com.tara.itinerary.dto.ItineraryResponse;
import com.tara.itinerary.exception.GlobalExceptionHandler;
import com.tara.itinerary.exception.ItineraryNotFoundException;
import com.tara.itinerary.mapper.ItineraryMapper;
import com.tara.itinerary.model.Activity;
import com.tara.itinerary.model.DayPlan;
import com.tara.itinerary.model.Itinerary;
import com.tara.itinerary.repository.ItineraryRepository;

@Service
public class ItineraryServiceImp implements ItineraryService {
	private final ItineraryRepository itineraryRepository;
	private final ItineraryMapper itineraryMapper;
	private final OpenAIClient openAiService;

	public ItineraryServiceImp(ItineraryRepository itineraryRepository,
			ItineraryMapper itineraryMapper,
			OpenAIClient openAiService, GlobalExceptionHandler globalExceptionHandler) {
		this.itineraryRepository = itineraryRepository;
		this.itineraryMapper = itineraryMapper;
		this.openAiService = openAiService;
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

	@Override
	public ItineraryResponse generateItinerary(ItineraryRequest itineraryRequest) {
		String prompt = buildPrompt(itineraryRequest);

		try {
			ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
					.model(ChatModel.GPT_3_5_TURBO)
					.temperature(0.7)
					.maxCompletionTokens(1000)
					.addSystemMessage("You are a travel planner expert.")
					.addUserMessage(prompt)
					.build();

			String response = openAiService.chat()
					.completions()
					.create(params)
					.choices()
					.get(0)
					.message()
					.content()
					.orElseThrow(() -> new ResponseStatusException(
							HttpStatus.INTERNAL_SERVER_ERROR,
							"No response content from OpenAI"));

			ItineraryResponse itineraryResponse = parseGeneratedResponse(response, itineraryRequest);
			return itineraryResponse;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to generate itinerary", e);
		}
	}

	private String buildPrompt(ItineraryRequest itineraryRequest) {
		StringBuilder promptBuilder = new StringBuilder(String.format("""
				Create a detailed day-by-day travel itinerary for a trip to %s
				Trip duration: %s to %s
				""",
				itineraryRequest.getDestination(),
				itineraryRequest.getStartDate(),
				itineraryRequest.getEndDate()));

		if (itineraryRequest.getTravelers() != null && !itineraryRequest.getTravelers().isEmpty()) {
			promptBuilder.append("Travelers: ")
					.append(itineraryRequest.getTravelers().size())
					.append("\n");

		} else {
			promptBuilder.append("Travelers: 1\n");
		}

		if (itineraryRequest.getBudget() != null) {
			promptBuilder.append("Budget: ")
					.append(itineraryRequest.getBudget())
					.append("\n");
		}

		if (itineraryRequest.getInterests() != null && !itineraryRequest.getInterests().isEmpty()) {
			promptBuilder.append("Interests: ")
					.append(String.join(", ", itineraryRequest.getInterests()))
					.append("\n");
		} else {
			promptBuilder.append("Interests: General\n");
		}

		promptBuilder.append("""
				For each day, include:
				- 2-3 activities
				- Specific times
				- Locations
				- Brief descriptions
				- Categories (e.g., sightseeing, dining, adventure)

				Format the response as JSON
				""");

		return promptBuilder.toString();
	}

	private ItineraryResponse parseGeneratedResponse(String response, ItineraryRequest itineraryRequest) {
		try {
			String cleanedResponse = response
					.replaceAll("^```json\\s*", "") // Remove opening ```json
					.replaceAll("^```\\s*", "") // Remove opening ```
					.replaceAll("```$", "") // Remove closing ```
					.trim();

			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(cleanedResponse);
			JsonNode itineraryNode = rootNode.get("itinerary");

			Itinerary itinerary = new Itinerary();
			itinerary.setTitle(itineraryRequest.getTitle());
			itinerary.setDestination(itineraryRequest.getDestination());
			itinerary.setStartDate(itineraryRequest.getStartDate());
			itinerary.setEndDate(itineraryRequest.getEndDate());
			if (itineraryRequest.getTravelers() != null && !itineraryRequest.getTravelers().isEmpty()) {
				List<String> travelers = itineraryRequest.getTravelers()
						.stream().map(String::trim).collect(Collectors.toList());
				itinerary.setTravelers(travelers);
			}

			if (itineraryRequest.getBudget() != null) {
				itinerary.setBudget(itineraryRequest.getBudget());
			}

			if (itineraryRequest.getInterests() != null && !itineraryRequest.getInterests().isEmpty()) {
				List<String> interests = itineraryRequest.getInterests()
						.stream().map(String::trim).collect(Collectors.toList());
				itinerary.setInterests(interests);
			}

			List<DayPlan> dayPlans = new ArrayList<>();
			itineraryNode.fields().forEachRemaining(entry -> {
				String dateStr = entry.getKey();
				JsonNode dayNode = entry.getValue();

				DayPlan dayPlan = new DayPlan();
				LocalDate date = LocalDate.parse(dateStr);
				dayPlan.setDate(date);
				dayPlan.setActivities(parseActivities(dayNode.get("activities")));
				dayPlans.add(dayPlan);
			});

			itinerary.setDays(dayPlans);

			return itineraryMapper.toDto(itinerary);
		} catch (Exception e) {
			System.err.println("Failed to parse response: " + e.getMessage());
			e.printStackTrace();
			throw new ResponseStatusException(
					HttpStatus.INTERNAL_SERVER_ERROR,
					"Failed to parse generated itinerary: " + e.getMessage());
		}
	}

	private List<Activity> parseActivities(JsonNode activitiesNode) {
		List<Activity> activities = new ArrayList<>();
		if (activitiesNode != null && activitiesNode.isArray()) {
			for (JsonNode activityNode : activitiesNode) {
				Activity activity = new Activity();
				String location = activityNode.get("location").asText();
				activity.setName(location);
				activity.setTime(activityNode.get("time").asText());
				activity.setLocation(location);
				activity.setDescription(activityNode.get("description").asText());

				if (activityNode.has("category")) {
					activity.setCategory(activityNode.get("category").asText());
				} else {
					activity.setCategory("General");
				}

				activities.add(activity);
			}
		}
		return activities;
	}
}
