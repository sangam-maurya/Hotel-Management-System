package com.example.Service;

import com.example.entity.Room;
import com.example.reposetry.RoomRepository;
import jakarta.persistence.Transient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public Room creteRoom(Room room){
        room.setProperty(room.getProperty());
        Room save = roomRepository.save(room);
        return save;
    }
}
