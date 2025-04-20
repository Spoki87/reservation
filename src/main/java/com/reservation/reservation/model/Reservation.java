package com.reservation.reservation.model;

import com.reservation.hotel.model.Room;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private Long reservationNumber;

    private LocalDate checkIn;
    private LocalDate checkOut;

    private ReservationStatus status;

    @Column(unique = true, nullable = false)
    private String firstName;

    @Column(unique = true, nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false)
    private String phone;

    @ManyToMany
    @JoinTable(
            name = "reservation_rooms",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "room_id")
    )
    private List<Room> rooms;

    @PrePersist
    public void generateReservationNumber() {
        this.reservationNumber = System.currentTimeMillis();
    }

    public Reservation(LocalDate checkIn, LocalDate checkOut, String firstName, String lastName, String email, String phone,List<Room> rooms) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.rooms = rooms;
        this.status= ReservationStatus.CONFIRMED;
    }

    public void changeContactInformation(String firstName, String lastName, String email, String phone){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public void cancelReservation() {
        this.status = ReservationStatus.CANCELLED;
    }

}
