package com.example.demo.Ticket;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.Event.EventNotFoundException;
import com.example.demo.Event.EventRepository;

@RestController
public class TicketController {
    private EventRepository events;
    private TicketRepository tickets;

    public TicketController(TicketRepository tickets, EventRepository events){
        this.tickets = tickets;
        this.events = events;
    }

    @GetMapping("/events/{eventId}/tickets")
    public List<Ticket> getAllTicketsByEventId(@PathVariable (value = "eventId") Long eventId) {
        if(!events.existsById(eventId)) {
            throw new EventNotFoundException(eventId);
        }
        return tickets.findByEventId(eventId);
    }

    @PostMapping("/events/{eventId}/tickets")
    public Ticket addTicket(@PathVariable (value = "eventId") Long eventId, @RequestBody Ticket ticket) {
        // using "map" to handle the returned Optional object from "findById(eventId)"
        return events.findById(eventId).map(event ->{
            ticket.setEvent(event);
            return tickets.save(ticket);
        }).orElseThrow(() -> new EventNotFoundException(eventId));
    }

    @PutMapping("/events/{eventId}/tickets/{ticketId}/sell")
    public Ticket sellTicket(@PathVariable (value = "eventId") Long eventId,
                                 @PathVariable (value = "ticketId") Long ticketId,
                                 @Valid @RequestBody Ticket newTicket) {
        if(!events.existsById(eventId)) {
            throw new EventNotFoundException(eventId);
        }
        return tickets.findByIdAndEventId(ticketId, eventId).map(ticket -> {
            ticket.setSold(true);
            return tickets.save(ticket);
        }).orElseThrow(() -> new TicketNotFoundException(ticketId));
    }

    @PutMapping("/events/{eventId}/tickets/{ticketId}/cancel")
    public Ticket cancelTicket(@PathVariable (value = "eventId") Long eventId,
                                 @PathVariable (value = "ticketId") Long ticketId,
                                 @Valid @RequestBody Ticket newTicket) {
        if(!events.existsById(eventId)) {
            throw new EventNotFoundException(eventId);
        }
        return tickets.findByIdAndEventId(ticketId, eventId).map(ticket -> {
            ticket.setSold(false);
            return tickets.save(ticket);
        }).orElseThrow(() -> new TicketNotFoundException(ticketId));
    }

    @DeleteMapping("/events/{eventId}/tickets/{ticketId}")
    public ResponseEntity<?> deleteTicket(@PathVariable (value = "eventId") Long eventId,
                              @PathVariable (value = "ticketId") Long ticketId) {
        
        if(!events.existsById(eventId)) {
            throw new EventNotFoundException(eventId);
        }

        return tickets.findByIdAndEventId(ticketId, eventId).map(ticket -> {
            tickets.delete(ticket);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new TicketNotFoundException(ticketId));
    }
}
