package com.example.reposetry;

import com.example.entity.Property;
import com.example.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
 Room  findByTypeAndProperty(String type , Property property);
}