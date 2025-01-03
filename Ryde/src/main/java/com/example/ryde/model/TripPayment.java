package com.example.ryde.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "trip_payment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TripPayment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long trip_id;
    private Instant payment_date;
    private Double amount;
    private String iban;
}