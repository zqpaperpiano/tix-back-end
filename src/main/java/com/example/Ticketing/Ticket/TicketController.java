package com.example.Ticketing.Ticket;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

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

    public TicketController(TicketRepository tickets, EventRepository events){
        this.tickets = tickets;
        this.events = events;
    }

    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        return tickets.findAll();
    }

    @PostMapping("/tickets")
    public Ticket addTicket(@RequestBody Ticket ticket) {
        return tickets.save(ticket);
    }

    @GetMapping("/events/{eventId}/tickets")
    public List<Ticket> getAllTicketsByEventId(@PathVariable ( value = "eventId") ObjectId eventId) {
        if(!events.existsById(eventId)) {
            throw new EventNotFoundException(eventId);
        }
        return tickets.findByEventId(eventId);
    }

    // @PostMapping("/events/{eventId}/tickets")
    // public Ticket addTicket(@PathVariable (value = "eventId") ObjectId eventId, @RequestBody Ticket ticket) {
    //     // using "map" to handle the returned Optional object from "findById(eventId)"
    //     return events.findById(eventId).map(event ->{
    //         event.getTicketIds().add(ticket);
    //         ticket.setEvent(event);
    //         return tickets.save(ticket);
    //     }).orElseThrow(() -> new EventNotFoundException(eventId));
    // }
    /*------------------------------------- my version -------------------------------------*/ //maybe ticket should refer to event cos db will be in infinite loop
    @PostMapping("/events/{eventId}/tickets")
    public ResponseEntity<Ticket> addTicket(@PathVariable (value = "eventId") ObjectId eventId, @RequestBody Ticket ticket) {
        // using "map" to handle the returned Optional object from "findById(eventId)"
        Event event = events.findById(eventId)
            .orElseThrow(() -> new EventNotFoundException(eventId));
            ticket.setEvent(event); //set TS event to ticket
            tickets.save(ticket); //save ticket entity into db
            event.getTicketIds().add(ticket); // add ticket to list
            events.save(event); // save event entity to db
            return new ResponseEntity<>(ticket, HttpStatus.CREATED);

        // return events.findById(eventId).map(event ->{
            // ArrayList<Ticket> L = (ArrayList<Ticket>) event.getTicketIds();
            // if(L == null){
            //     L = new ArrayList<Ticket>();
            //     L.add(ticket);
            //     // System.out.println("blahblahblah");
            // }
            // event.setTicketIds(L);
        //     ticket.setEvent(event); //set TS event to ticket
        //     tickets.save(ticket); //save ticket entity into db
        //     event.getTicketIds().add(ticket); // add ticket to list
        //     events.save(event);
            
        //     return tickets.save(ticket);
        // }).orElseThrow(() -> new EventNotFoundException(eventId));
    }
    /*-------------------------------------------------------------------------------- */

    /*------------------------------------- jing heng version -------------------------------------*/
    // @PostMapping("/events/{eventId}/tickets") 
    // public Ticket addTicket(@PathVariable(value = "eventId") ObjectId eventId, @RequestBody Ticket ticket) { 
    //     // using "map" to handle the returned Optional object from "findById(eventId)" 
    //     return events.findById(eventId).map(event -> { 
    //         List<Ticket> ticketList = event.getTicketIds(); 
    //         if(ticketList == null){ 
    //             ticketList = new ArrayList<Ticket>(); 
    //         } 
    //         ticketList.add(ticket); 
    //         event.setTicketIds(ticketList); 
    //         ticket.setEvent(event); 
    //         return tickets.save(ticket); 
    //     }).orElseThrow(() -> new EventNotFoundException(eventId)); 
    // }
    /*-------------------------------------------------------------------------------- */

    // @PutMapping("/events/{eventId}/tickets/{ticketId}/sell")
    // public Ticket sellTicket(@PathVariable (value = "eventId") Long eventId,
    //                              @PathVariable (value = "ticketId") Long ticketId,
    //                              @RequestBody Ticket newTicket) {
    //     if(!events.existsById(eventId)) {
    //         throw new EventNotFoundException(eventId);
    //     }
    //     return tickets.findByIdAndEventId(ticketId, eventId).map(ticket -> {
    //         ticket.setSold(true);
    //         return tickets.save(ticket);
    //     }).orElseThrow(() -> new TicketNotFoundException(ticketId));
    // }

    // @PutMapping("/events/{eventId}/tickets/{ticketId}/cancel")
    // public Ticket cancelTicket(@PathVariable (value = "eventId") Long eventId,
    //                              @PathVariable (value = "ticketId") Long ticketId,
    //                              @Valid @RequestBody Ticket newTicket) {
    //     if(!events.existsById(eventId)) {
    //         throw new EventNotFoundException(eventId);
    //     }
    //     return tickets.findByIdAndEventId(ticketId, eventId).map(ticket -> {
    //         ticket.setSold(false);
    //         return tickets.save(ticket);
    //     }).orElseThrow(() -> new TicketNotFoundException(ticketId));
    // }

    // @DeleteMapping("/events/{eventId}/tickets/{ticketId}")
    // public ResponseEntity<?> deleteTicket(@PathVariable (value = "eventId") Long eventId,
    //                           @PathVariable (value = "ticketId") Long ticketId) {
        
    //     if(!events.existsById(eventId)) {
    //         throw new EventNotFoundException(eventId);
    //     }

    //     return tickets.findByIdAndEventId(ticketId, eventId).map(ticket -> {
    //         tickets.delete(ticket);
    //         return ResponseEntity.ok().build();
    //     }).orElseThrow(() -> new TicketNotFoundException(ticketId));
    // }
}
