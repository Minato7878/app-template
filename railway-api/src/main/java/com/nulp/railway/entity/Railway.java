package com.nulp.railway.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "railways")
public class Railway {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "railway_number", nullable = false)
    private String railwayNumber;

    @Column(name = "departure_station", nullable = false)
    private String departureStation;

    @Column(name = "arrival_station", nullable = false)
    private String arrivalStation;

    @Column(name = "departure_datetime", nullable = false)
    private LocalDateTime departureDatetime;

    @Column(name = "arrival_datetime", nullable = false)
    private LocalDateTime arrivalDatetime;

    @OneToMany(mappedBy = "railway", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats;

    @PrePersist
    private void generateSeats() {
        if (seats == null || seats.isEmpty()) {
            seats = new ArrayList<>();

            String[] letters = {"A", "B", "C", "D"};
            int[] numbers = {1, 2, 3, 4};

            for (String letter : letters) {
                for (int number : numbers) {
                    String seatNumber = letter + number;
                    String carriage = "Carriage " + number;
                    Seat seat = new Seat(seatNumber, carriage, true);
                    seat.setRailway(this); // Set the railway reference for the seat
                    seats.add(seat);
                }
            }
        }
    }
}
