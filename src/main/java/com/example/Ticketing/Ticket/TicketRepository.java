package com.example.demo.Ticket;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
// import com.example.demo.Ticket.Ticket;

public interface TicketRepository extends MongoRepository<Ticket, Long> {
	List<Ticket> findByEventId(long eventId) ;
    Optional<Ticket> findByIdAndEventId(Long id, Long bookId);
}
