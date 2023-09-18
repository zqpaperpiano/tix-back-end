package com.example.Ticketing.Event;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> allEvents() {
        return eventRepository.findAll();
    }

    public Optional<Event> singleEvent(ObjectId id) {
        return eventRepository.findById(id);
    }

    public Event save(Event e){
        return eventRepository.save(e);
    }
}