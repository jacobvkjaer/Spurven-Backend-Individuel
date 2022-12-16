package couchpotatoes.spurven.application.api;


import couchpotatoes.spurven.application.dto.EventRequest;
import couchpotatoes.spurven.application.entity.Event;
import couchpotatoes.spurven.application.service.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService){
        this.eventService = eventService;
    }
    @GetMapping()
    private List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }
    @GetMapping("/{id}")
    public Event getEvent(@PathVariable int id){
        return eventService.getEvent(id);
    }
    @PostMapping("/")
    public Event createEvent(@RequestBody EventRequest eventRequest){
        return eventService.createEvent(eventRequest.getTitle(), eventRequest.getDescription(), eventRequest.getStart(), eventRequest.getEnd());
    }
    @PutMapping("/{id}")
    public Event editEvent(@RequestBody EventRequest eventRequest, @PathVariable int id){
        return eventService.editEvent(eventRequest, id);
    }

    @DeleteMapping("/{id}")
    public String deleteEvent(@PathVariable int id){
        return eventService.deleteEvent(id);
    }

}
