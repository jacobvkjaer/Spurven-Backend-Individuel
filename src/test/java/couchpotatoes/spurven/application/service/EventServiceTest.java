package couchpotatoes.spurven.application.service;

import couchpotatoes.spurven.application.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    EventRepository eventRepo;

    @Autowired
    EventService eventServ;

    @BeforeEach
    public void setup(){
        eventServ = new EventService(eventRepo);
    }

/*
    @Test
    void getAllContacts() {
        Mockito.when(eventRepo.findAll()).thenReturn(List.of(
                Event.builder().id(40).title("Good luck").build(),
                Event.builder().id(50).title("Have fun").build()
        ));

        List<Event> events = eventServ.getAllEvents();

        assertEquals(40,events.get(0).getId());
        assertEquals(2,events.size());
    }

    @Test
    void createEvent() {
        Event event = Event.builder().id(60).title("Fun times").build();

        Mockito.when(eventRepo.save(any(Event.class))).thenReturn(event);

        Event createdEvent = eventServ.createEvent(event);

        assertEquals(60,createdEvent.getId());
        assertEquals("Fun times",createdEvent.getTitle());
    }
*/
    @Test
    void editEvent() {
    }
}