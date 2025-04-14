package com.reservation.hotel.service;

import com.reservation.exception.domain.ResourceNotFoundException;
import com.reservation.hotel.dto.response.RoomResponse;
import com.reservation.hotel.model.Room;
import com.reservation.hotel.model.RoomStatus;
import com.reservation.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Setter
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    public List<RoomResponse> getRooms() {
        return roomRepository.findAll().stream()
                .map(RoomResponse::from)
                .toList();
    }

    public RoomResponse getRoomById(UUID id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        return RoomResponse.from(room);
    }

    public RoomResponse getRoomByNumber(String number){
        Room room = roomRepository.findByNumber(number)
                .orElseThrow(ResourceNotFoundException::new);
        return RoomResponse.from(room);
    }

    public List<RoomResponse> getAvailableRooms(){
        return roomRepository.findByStatus(RoomStatus.AVAILABLE).stream()
                .map(RoomResponse::from)
                .toList();
    }

    public List<RoomResponse> getUnavailableRooms(){
        return roomRepository.findByStatusNot(RoomStatus.AVAILABLE).stream()
                .map(RoomResponse::from)
                .toList();
    }

    public List<RoomResponse> getAvailableRoomsByDates(LocalDate from, LocalDate to){
        return null;
    }

    public List<RoomResponse> getUnavailableRoomsByDates(LocalDate from, LocalDate to){
        return null;
    }

    public RoomResponse createRoom(Room room){
        return null;
    }

    public RoomResponse updateRoom(Room room){
        return null;
    }

    public void deleteRoom(Room room){

    }
}
