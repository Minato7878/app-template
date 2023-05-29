package com.nulp.railway.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "seats")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(nullable = false)
    private String carriage;

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "railway_id", nullable = false)
    private Railway railway;

    // Default constructor
    public Seat() {
    }

    // Constructor with parameters
    public Seat(String seatNumber, String carriage, boolean isAvailable) {
        this.seatNumber = seatNumber;
        this.carriage = carriage;
        this.isAvailable = isAvailable;
    }
}