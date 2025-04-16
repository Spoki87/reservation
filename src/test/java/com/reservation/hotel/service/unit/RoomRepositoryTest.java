package com.reservation.hotel.service.unit;

import com.reservation.hotel.model.Room;
import com.reservation.hotel.model.RoomType;
import com.reservation.hotel.repository.RoomRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    void shouldCreateAndRetrieveRoom() {

        //Arrange
        Room room = new Room("101", "Test room", RoomType.SINGLE, 120.0, 2);

        //Act
        Room savedRoom = roomRepository.save(room);

        //Asserts
        Assertions.assertThat(savedRoom).isNotNull();
        Assertions.assertThat(savedRoom.getId()).isNotNull();
    }



    @Test
    void shouldReturnRoomById() {
//        when(roomRepository.findById(roomId)).thenReturn(Optional.of(testRoom));
//
//        RoomResponse result = roomService.getRoomById(roomId);
//
//        assertThat(result.getId()).isEqualTo(roomId);
//        assertThat(result.getNumber()).isEqualTo("101");
//        verify(roomRepository).findById(roomId);
    }

    @Test
    void shouldThrowWhenRoomNotFound() {
//        when(roomRepository.findById(roomId)).thenReturn(Optional.empty());
//
//        assertThatThrownBy(() -> roomService.getRoomById(roomId))
//                .isInstanceOf(ResourceNotFoundException.class);
    }

}
