package com.example.Ticketing.Event;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

@RestController
@RequestMapping
public class EventController {
    @Autowired
    private EventService eventService;
<<<<<<< HEAD
    @GetMapping("/events")
    // public ResponseEntity<List<Event>> getAllEvents() {
    //     return new ResponseEntity<List<Event>>(eventService.allEvents(), HttpStatus.OK);
    // }

    public String getApi(){
        return "hello world.";
    }

=======
    @GetMapping("/allevents")
    public ResponseEntity<List<Event>> getAllEvents() {
        return new ResponseEntity<List<Event>>(eventService.allEvents(), HttpStatus.OK);
    }

    // @GetMapping("/allevents")
    // public List<Event> getallEvents(){
    //     return eventService.allEvents();
    // }
>>>>>>> 7247ecd38236646470d78d0ab6e18552a8359cea
}