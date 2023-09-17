package com.example.Ticketing.Ticket;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
// import com.example.Ticketing.Ticket.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, Long> {
	List<Ticket> findByEventId(ObjectId eventId) ;
<<<<<<< HEAD
    Optional<Ticket> findByIdAndEventId(ObjectId TicketId, ObjectId eventId);
=======
    Optional<Ticket> findByIdAndEventId(Long id, Long bookId);
>>>>>>> 7247ecd38236646470d78d0ab6e18552a8359cea
}
