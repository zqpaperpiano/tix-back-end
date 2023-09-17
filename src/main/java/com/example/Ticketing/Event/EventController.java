package com.example.demo.Event;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping
public class EventController {
    @Autowired
    private EventService eventService;
    @GetMapping
    public ResponseEntity<String> getAllEvents() {
        return new ResponseEntity<List<Event>>(eventService.allEvents(), HttpStatus.OK);
    }
}