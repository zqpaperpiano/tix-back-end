package com.example.Ticketing.Event;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Ticketing.Ticket.Ticket;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@RestController
@RequestMapping("/api/v1/")
public class EventController {
    @Autowired
    private EventService eventService;
    @GetMapping("/events")
    public ResponseEntity<List<Event>> getAllEvents() {
        return new ResponseEntity<List<Event>>(eventService.allEvents(), HttpStatus.OK);
    }

    // @GetMapping("/allevents")
    // public List<Event> getallEvents(){
    // return eventService.allEvents();
    // }

    @GetMapping("/events/{id}")
    public ResponseEntity<Optional<Event>> getSingleEvent(@PathVariable ObjectId id) {
        return new ResponseEntity<Optional<Event>>(eventService.singleEvent(id), HttpStatus.OK);
    }

    @GetMapping("/events/{id}/date")

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/events/add")
    public Event addEvent(@RequestBody Event event) {
        return eventService.save(event);
    }
}