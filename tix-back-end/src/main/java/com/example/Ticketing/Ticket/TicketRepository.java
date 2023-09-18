package com.example.Ticketing.Ticket;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
// import com.example.Ticketing.Ticket.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, ObjectId> {
	List<Ticket> findByEventId(ObjectId eventId) ;
    Optional<Ticket> findByIdAndEventId(ObjectId TicketId, ObjectId eventId);
}
