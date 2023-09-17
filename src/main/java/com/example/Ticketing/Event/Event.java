package com.example.Ticketing.Event;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import java.util.*;
import com.example.Ticketing.Ticket.*;

@Document(collection = "event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    private ObjectId id;

    private String name;

    private String date;

    @DocumentReference
    private List<Ticket> ticketIds;

    public Optional<Ticket> map(Object object) {
        return null;
    }
}