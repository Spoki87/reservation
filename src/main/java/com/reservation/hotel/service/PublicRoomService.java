package com.reservation.hotel.service;

import com.reservation.hotel.dto.response.RoomResponse;
import com.reservation.hotel.model.RoomStatus;
import com.reservation.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PublicRoomService {

    private final RoomRepository roomRepository;

    @Cacheable(value = "availableRooms",  key = "'staticKey'")
    public List<RoomResponse> getAvailableRooms(){
        return roomRepository.findByStatus(RoomStatus.AVAILABLE).stream()
                .map(RoomResponse::from)
                .toList();
    }

    @Cacheable(value = "availableRooms", key = "#from.toString() + #to.toString()")
    public List<RoomResponse> getAvailableRoomsByDates(LocalDate from, LocalDate to){
        return roomRepository.findAvailableRoomsInDateRange(from,to).stream()
                .map(RoomResponse::from)
                .toList();
    }

}
