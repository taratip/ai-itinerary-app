package com.tara.itinerary.listener;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;
import com.tara.itinerary.model.Itinerary;

@Component
public class ItineraryListener extends AbstractMongoEventListener<Itinerary> {
    public void onBeforeConvert(BeforeConvertEvent<Itinerary> event) {
        Itinerary itinerary = event.getSource();
        if (itinerary.getId() == null) {
            itinerary.setId(UUID.randomUUID());
        }
        Instant now = Instant.now();
        if (itinerary.getCreatedAt() == null) {
            itinerary.setCreatedAt(now);
        }
        itinerary.setUpdatedAt(now);

        if (itinerary.getDays() != null) {
            itinerary.getDays().forEach(dayPlan -> {
                if (dayPlan.getId() == null) {
                    dayPlan.setId(UUID.randomUUID());
                }

                if (dayPlan.getActivities() != null) {
                    dayPlan.getActivities().forEach(activity -> {
                        if (activity.getId() == null) {
                            activity.setId(UUID.randomUUID());
                        }
                    });
                }
            });
        }
    }
}
