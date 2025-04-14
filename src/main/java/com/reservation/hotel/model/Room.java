package com.reservation.hotel.model;

import com.reservation.reservation.model.Reservation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
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

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    public boolean isAvailable() {
        return status == RoomStatus.AVAILABLE;
    }

    public boolean isReserved() {
        return status == RoomStatus.RESERVED;
    }

    public void reserve() {
        status = RoomStatus.RESERVED;
    }

    public void changeToAvailable() {
        this.status = RoomStatus.AVAILABLE;
    }

    public void changeToUnavailable() {
        this.status = RoomStatus.UNAVAILABLE;
    }

    public void changePricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void changeDescription(String description) {
        this.description = description;
    }
}
