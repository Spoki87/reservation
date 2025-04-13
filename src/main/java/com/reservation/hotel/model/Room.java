package com.reservation.hotel.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false)
    private String number;

    private String description;

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @Column(unique = true, nullable = false)
    private Double pricePerDay;

    @Column(unique = true, nullable = false)
    private int capacity;

    @Enumerated(EnumType.STRING)
    private RoomStatus status;
}
