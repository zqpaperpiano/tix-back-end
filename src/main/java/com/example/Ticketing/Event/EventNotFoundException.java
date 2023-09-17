package com.example.Ticketing.Event;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

<<<<<<< HEAD
    public EventNotFoundException(ObjectId id) {
        super("Could not find ticket " + id);
=======
    public EventNotFoundException(ObjectId eventId) {
        super("Could not find ticket " + eventId);
>>>>>>> 7247ecd38236646470d78d0ab6e18552a8359cea
    }
}