package com.example.demo.Ticket;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.annotation.Id;

import com.example.demo.Event.Event;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Ticket {
    private int seat_num; //1 - 400
    private  @Id Long id;
    private Event event;
    private boolean sold = false;

}
