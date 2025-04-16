package com.reservation.hotel.repository.unit;

import com.reservation.hotel.model.Room;
import com.reservation.hotel.model.RoomType;
import com.reservation.hotel.repository.RoomRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    @DisplayName("Should create room in the database")
    void shouldCreateAndRetrieveRoom() {

        //Arrange
        Room room = new Room("101", "Test room", RoomType.DOUBLE, 120.0, 2);

        //Act
        Room savedRoom = roomRepository.save(room);

        //Asserts
        Assertions.assertThat(savedRoom).isNotNull();
        Assertions.assertThat(savedRoom.getId()).isNotNull();
    }

    @Test
    @DisplayName("Should return room from the database by ID")
    void shouldReturnRoomById() {
        //Arrange
        Room room = new Room("101", "Test room", RoomType.DOUBLE, 120.0, 2);
        Room savedRoom = roomRepository.save(room);
        UUID id = savedRoom.getId();

        //Act
        Room retrievedRoom = roomRepository.findById(id).orElse(null);

        //Asserts
        Assertions.assertThat(retrievedRoom).isNotNull();
        Assertions.assertThat(retrievedRoom).isEqualTo(savedRoom);
    }

    @Test
    @DisplayName("Should return all rooms from the database")
    void shouldReturnAllRooms() {
        //Arrange
        Room room = new Room("101", "Test room", RoomType.DOUBLE, 120.0, 2);
        Room room2 = new Room("102", "Test room 2", RoomType.SINGLE, 240.0, 1);
        roomRepository.save(room);
        roomRepository.save(room2);

        //Act
        List<Room> rooms = roomRepository.findAll();

        //Asserts
        Assertions.assertThat(rooms).hasSize(2);
    }

    @Test
    @DisplayName("Should delete a room from the database")
    void shouldDeleteRoom() {
        //Arrange
        Room room = new Room("101", "Test room", RoomType.DOUBLE, 120.0, 2);
        roomRepository.save(room);


        //Act
        roomRepository.delete(room);

        //Asserts
        List<Room> rooms = roomRepository.findAll();
        Assertions.assertThat(rooms).hasSize(0);
    }

}
