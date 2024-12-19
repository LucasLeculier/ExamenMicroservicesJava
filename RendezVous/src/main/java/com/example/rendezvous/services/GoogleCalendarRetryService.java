package com.example.rendezvous.services;

import com.google.api.services.calendar.model.Event;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class GoogleCalendarRetryService {

    private static final int MAX_RETRIES = 3;
    private static final long RETRY_DELAY_MS = 2000; // 2 secondes

    private final GoogleCalendarService googleCalendarService;

    public GoogleCalendarRetryService(GoogleCalendarService googleCalendarService) {
        this.googleCalendarService = googleCalendarService;
    }

    public Event createEventWithRetry(String summary, String location, String description, String startTime, String endTime) {
        int attempts = 0;
        while (attempts < MAX_RETRIES) {
            try {
                return googleCalendarService.createEvent(summary, location, description, startTime, endTime);
            } catch (IOException e) {
                attempts++;
                if (attempts < MAX_RETRIES) {
                    try {
                        Thread.sleep(RETRY_DELAY_MS);
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                } else {
                    throw new RuntimeException("Failed to create event after " + MAX_RETRIES + " attempts", e);
                }
            }
        }
        return null;
    }
}
