package com.example.Ticketing.Ticket;

import org.bson.types.ObjectId;
// import javax.persistence.*;
// import javax.validation.constraints.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
// import org.springframework.data.mongodb.core.mapping.DocumentReference;

import com.example.Ticketing.Event.Event;
import lombok.*;

// @Entity
@Getter
@Setter
@ToString
//  @AllArgsConstructor
//  @NoArgsConstructor
@EqualsAndHashCode
@Document(collection = "ticket")
public class Ticket {
    @Id
    private ObjectId id;
    private int seat_num; // 1 - 400
    private boolean sold = false;

    // @DocumentReference(lookup = "{ 'name' : ?#{Taylor Swift} }")
    private ObjectId eventId;

    public Ticket(int seat_num, boolean sold){
        this.seat_num = seat_num;
        this.sold = sold;
    }
}
