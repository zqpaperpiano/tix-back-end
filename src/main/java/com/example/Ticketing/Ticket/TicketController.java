package com.example.Ticketing.Ticket;

//ticketing
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

// import javax.validation.Valid;

// import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Ticketing.Event.Event;
import com.example.Ticketing.Event.EventNotFoundException;
import com.example.Ticketing.Event.EventRepository;

@RestController
@RequestMapping("/api/v1/")
public class TicketController {

    @Autowired
    private EventRepository events;
    @Autowired
    private TicketRepository tickets;

    public TicketController(TicketRepository tickets, EventRepository events) {
        this.tickets = tickets;
        this.events = events;
    }

    // @GetMapping("/events/{eventId}/tickets")
    // public List<Ticket> getAllTicketsByEventId(@PathVariable ( value = "eventId")
    // ObjectId eventId) {
    // if(!events.existsById(eventId)) {
    // throw new EventNotFoundException(eventId);
    // }
    // return tickets.findByEventId(eventId);
    // }
    @GetMapping("/events/{eventId}/tickets")
    public List<Ticket> getAllTicketsByEventId(@PathVariable(value = "eventId") ObjectId eventId) {
        if (!events.existsById(eventId)) {
            throw new EventNotFoundException(eventId);
        }
        return tickets.findByEventId(eventId);
    }
/*------------------------------ Gerald start ---------------------------------- */
    @GetMapping("/events/{eventId}/ticketByCategory/{category}")
    public Optional <List<Ticket>> getTicketByEventIdAndCategory(@PathVariable(value = "eventId") ObjectId eventId, 
    @PathVariable(value = "category") int category) {

        if (!events.existsById(eventId)) {
            throw new EventNotFoundException(eventId);
        }

        return tickets.findByEventIdAndCategory(eventId, category);
    }

    //get ticket by EventName and Category

    // @GetMapping("/events/getByEventName/{eventName}/ticketByCategory/{category}")
    // public Optional <List<Ticket>> getTicketByEventNameAndCategory(@PathVariable(value = "eventName") String eventName, 
    // @PathVariable(value = "category") int category) {

    //     Event e = events.findByName(eventName); //find event by name
    //     if (e == null){ throw new EventNotFoundException(eventName);}
    //     else if (!events.existsById(e.getId())){
    //         throw new EventNotFoundException(eventName);
    //     }

    //     return tickets.findByEventIdAndCategory(e.getId(), category);
    // }
/*------------------------------ Gerald End ---------------------------------- */


    @GetMapping("/events/{eventId}/ticketBySeat/{seat_num}")
    public Optional<Ticket> getTicketByEventIdAndSeatNum(@PathVariable(value = "eventId") ObjectId eventId, @PathVariable int seat_num) {
        if (!events.existsById(eventId)) {
            throw new EventNotFoundException(eventId);
        }
        return tickets.findByEventIdAndSeatNum(eventId, seat_num);
    }


    // adds ticket to ticket list in Event 
    // http body -> int seatNum, boolean sold, int category
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/events/{eventId}/tickets")
    public Ticket addTicket(@PathVariable(value = "eventId") ObjectId id, @RequestBody Ticket ticket) {
        // using "map" to handle the returned Optional object from "findById(eventId)"
        return events.findById(id).map(event -> {
            event.getTicketIds().add(ticket);
            ticket.setEventId(id);
            ticket.setPrice(event.getPrice()[(ticket.getCategory() - 1)]);
            events.save(event);
            return tickets.save(ticket);
        }).orElseThrow(() -> new EventNotFoundException(id));
    }

    // sets boolean sold to true
    @PutMapping("/events/{eventId}/tickets/{ticketId}/sell")
    public Ticket sellTicket(@PathVariable(value = "eventId") ObjectId eventId,
            @PathVariable(value = "ticketId") ObjectId ticketId,
            @RequestBody Ticket newTicket) {
        if (!events.existsById(eventId)) {
            throw new EventNotFoundException(eventId);
        }
        return tickets.findByIdAndEventId(ticketId, eventId).map(ticket -> {
            ticket.setSold(true);
            return tickets.save(ticket);
        }).orElseThrow(() -> new TicketNotFoundException(ticketId));
    }

    // sets boolean sold to false
    @PutMapping("/events/{eventId}/tickets/{ticketId}/cancel")
    public Ticket cancelTicket(@PathVariable(value = "eventId") ObjectId eventId,
            @PathVariable(value = "ticketId") ObjectId ticketId,
            @RequestBody Ticket newTicket) {
        if (!events.existsById(eventId)) {
            throw new EventNotFoundException(eventId);
        }
        return tickets.findByIdAndEventId(ticketId, eventId).map(ticket -> {
            ticket.setSold(false);
            return tickets.save(ticket);
        }).orElseThrow(() -> new TicketNotFoundException(ticketId));
    }

    // @DeleteMapping("/events/{eventId}/tickets/{ticketId}")
    // public ResponseEntity<?> deleteTicket(@PathVariable (value = "eventId") Long
    // eventId,
    // @PathVariable (value = "ticketId") Long ticketId) {

    // if(!events.existsById(eventId)) {
    // throw new EventNotFoundException(eventId);
    // }

    // return tickets.findByIdAndEventId(ticketId, eventId).map(ticket -> {
    // tickets.delete(ticket);
    // return ResponseEntity.ok().build();
    // }).orElseThrow(() -> new TicketNotFoundException(ticketId));
    // }
}
