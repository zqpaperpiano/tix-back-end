package com.example.Ticketing.Event;

import lombok.Data;
import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import java.util.*;
import com.example.Ticketing.Ticket.*;

@Document(collection = "event")
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    private ObjectId id;

    private String name;

    private String date;

    @DocumentReference(lazy = true)
    private List<Ticket> ticketIds;
}