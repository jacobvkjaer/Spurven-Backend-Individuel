package couchpotatoes.spurven.application.service;


import couchpotatoes.spurven.application.dto.EventRequest;
import couchpotatoes.spurven.application.entity.Event;
import couchpotatoes.spurven.application.repository.EventRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
    public Event getEvent(int id) {
        return eventRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Event not found"));
    }
    public Event createEvent(String title, String description, LocalDateTime start, LocalDateTime end) {
        Event event = new Event(title, description, start, end);
        return eventRepository.save(event);
    }
    public Event editEvent(EventRequest body, int id) {
        Event event = getEvent(id);
        event.setTitle(body.getTitle());
        event.setDescription(body.getDescription());
        event.setStart(body.getStart());
        event.setEnd(body.getEnd());
        return eventRepository.save(event);
    }
    public String deleteEvent(int id) {
        Event event = getEvent(id);
        eventRepository.delete(event);
        return "Event got deleted";
    }
}
