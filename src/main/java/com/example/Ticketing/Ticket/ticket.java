package com.example.Ticketing.Ticket;

import org.bson.types.ObjectId;
<<<<<<< HEAD
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
=======

// import javax.persistence.*;
// import javax.validation.constraints.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
>>>>>>> 7247ecd38236646470d78d0ab6e18552a8359cea

import com.example.Ticketing.Event.Event;
import lombok.*;

<<<<<<< HEAD
// @Entity
=======
>>>>>>> 7247ecd38236646470d78d0ab6e18552a8359cea
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "ticket")
public class Ticket {
    private @Id ObjectId id;
    private int seat_num; // 1 - 400
<<<<<<< HEAD
    private @Id ObjectId id;
    private Event event;
=======
>>>>>>> 7247ecd38236646470d78d0ab6e18552a8359cea
    private boolean sold = false;

    @DocumentReference(lookup = "{ 'name' : ?#{Taylor Swift} }")
    private Event event;

}
