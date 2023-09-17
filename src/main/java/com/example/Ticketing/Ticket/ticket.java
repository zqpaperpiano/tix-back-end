package com.example.Ticketing.Ticket;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.Ticketing.Event.Event;
import lombok.*;

// @Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "ticket")
public class Ticket {
    private int seat_num; // 1 - 400
    private @Id ObjectId id;
    private Event event;
    private boolean sold = false;

}
