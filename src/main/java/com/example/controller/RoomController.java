package com.example.controller;

import com.example.Service.RoomService;
import com.example.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/room")
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/create-room")
    public Room createRoomReg(@RequestBody Room room){
        Room room1 = roomService.creteRoom(room);
        return room1;
    }
}
