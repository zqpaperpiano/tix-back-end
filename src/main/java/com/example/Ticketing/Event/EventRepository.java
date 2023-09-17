package com.example.Ticketing.Event;

// import java.util.List;
// import java.util.Optional;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

// import com.example.Ticketing.Ticket.Ticket;

@Repository
public interface EventRepository extends MongoRepository<Event, ObjectId> {
<<<<<<< HEAD
    Event findById(long ObjectId);
    boolean existsById(long ObjectId);
=======
    Optional<Event> findById(ObjectId ObjectId);
    boolean existsById(ObjectId ObjectId);
>>>>>>> 7247ecd38236646470d78d0ab6e18552a8359cea
}