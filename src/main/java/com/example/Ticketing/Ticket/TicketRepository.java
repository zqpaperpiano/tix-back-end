package com.example.Ticketing.Ticket;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
// import com.example.Ticketing.Ticket.Ticket;
import org.yaml.snakeyaml.events.Event.ID;

@Repository
public interface TicketRepository extends MongoRepository<Ticket, ObjectId> {
	List<Ticket> findByEventId(ObjectId eventId) ;
    Optional<Ticket> findByIdAndEventId(ObjectId TicketId, ObjectId eventId);
    Optional<Ticket> findByEventIdAndSeatNum(ObjectId eventId, int category);

    //find ticket by category
    Optional<List<Ticket>> findByEventIdAndCategory(ObjectId eventId, int category);
}
