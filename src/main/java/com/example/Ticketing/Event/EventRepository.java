package com.example.Ticketing.Event;

// import java.util.List;
// import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// import com.example.Ticketing.Ticket.Ticket;

@Repository
public interface EventRepository extends MongoRepository<Event, ObjectId> {
    Event findById(long ObjectId);
    boolean existsById(long ObjectId);
}