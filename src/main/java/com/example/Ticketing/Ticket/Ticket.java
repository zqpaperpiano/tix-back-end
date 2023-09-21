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
    private int seatNum; // 1 - 400
    private boolean sold = false;
    private int category;
    private float price;

    // @DocumentReference(lookup = "{ 'name' : ?#{Taylor Swift} }")
    private ObjectId eventId;

    public Ticket(int seatNum, boolean sold, int category){
        this.category = category;
        this.seatNum = seatNum;
        this.sold = sold;
    }
}
