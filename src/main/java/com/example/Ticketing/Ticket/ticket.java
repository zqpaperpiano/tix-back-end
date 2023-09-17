package com.example.Ticketing.Ticket;

import org.bson.types.ObjectId;

// import javax.persistence.*;
// import javax.validation.constraints.*;

import org.springframework.data.annotation.Id;

import com.example.Ticketing.Event.Event;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Ticket {
    private int seat_num; // 1 - 400
    private @Id ObjectId id;
    private Event event;
    private boolean sold = false;

}
