package com.example.rendezvous.services;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
public class GoogleCalendarService {

    private static final String APPLICATION_NAME = "Rendezvous Service";
    private static final String CALENDAR_ID = "primary"; // Pour le calendrier principal

    public Calendar getCalendarService() throws IOException {
        // Configure OAuth2.0 credentials
        GoogleCredential credential = GoogleCredential.fromStream(getClass().getResourceAsStream("/credentials.json"))
                .createScoped(Collections.singleton(CalendarScopes.CALENDAR));

        return new Calendar.Builder(
                credential.getTransport(),
                credential.getJsonFactory(),
                credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public Event createEvent(String summary, String location, String description, String startTime, String endTime) throws IOException {
        Calendar service = getCalendarService();

        Event event = new Event()
                .setSummary(summary)
                .setLocation(location)
                .setDescription(description);

        EventDateTime start = new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(startTime))
                .setTimeZone("America/Los_Angeles");
        event.setStart(start);

        EventDateTime end = new EventDateTime()
                .setDateTime(new com.google.api.client.util.DateTime(endTime))
                .setTimeZone("America/Los_Angeles");
        event.setEnd(end);

        return service.events().insert(CALENDAR_ID, event).execute();
    }
}
