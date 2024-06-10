package com.booking_house_be.entity;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime startTime;
    private String status;
    private String content;
    private String result;
    private LocalDateTime create_at;
    @ManyToOne
    private House house;
    @ManyToOne
    private Account account;
}
