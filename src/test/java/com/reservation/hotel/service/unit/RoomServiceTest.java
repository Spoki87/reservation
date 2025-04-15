package com.reservation.hotel.service.unit;

import com.reservation.exception.domain.ResourceNotFoundException;
import com.reservation.hotel.dto.request.CreateRoomRequest;
import com.reservation.hotel.dto.response.RoomResponse;
import com.reservation.hotel.model.Room;
import com.reservation.hotel.model.RoomType;
import com.reservation.hotel.repository.RoomRepository;
import com.reservation.hotel.service.RoomService;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;
import java.util.UUID;


import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    private final UUID roomId = UUID.randomUUID();
    private Room testRoom;

    @BeforeEach
    void setUp() {
        testRoom = new Room("101", "Test room", RoomType.SINGLE, 100.0, 2);
        ReflectionTestUtils.setField(testRoom, "id", roomId);
    }

    @Test
    void shouldCreateRoom() {
        CreateRoomRequest request = new CreateRoomRequest("101", "Test room", RoomType.SINGLE, 120.0, 2);
        when(roomRepository.save(any(Room.class))).thenReturn(testRoom);

        RoomResponse result = roomService.createRoom(request);

        assertThat(result.getNumber()).isEqualTo("101");
        verify(roomRepository).save(any(Room.class));
    }

    @Test
    void shouldReturnRoomById() {
        when(roomRepository.findById(roomId)).thenReturn(Optional.of(testRoom));

        RoomResponse result = roomService.getRoomById(roomId);

        assertThat(result.getId()).isEqualTo(roomId);
        assertThat(result.getNumber()).isEqualTo("101");
        verify(roomRepository).findById(roomId);
    }

    @Test
    void shouldThrowWhenRoomNotFound() {
        when(roomRepository.findById(roomId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> roomService.getRoomById(roomId))
                .isInstanceOf(ResourceNotFoundException.class);
    }

}
