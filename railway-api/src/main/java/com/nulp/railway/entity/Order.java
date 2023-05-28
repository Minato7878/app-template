package com.nulp.railway.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_datetime", nullable = false)
    private LocalDateTime orderDatetime;

    @ManyToOne
    @JoinColumn(name = "railway_id", referencedColumnName = "id", nullable = false)
    private Railway railway;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @PrePersist
    public void prePersist() {
        orderDatetime = LocalDateTime.now(); // Set the current date and time
    }
}
