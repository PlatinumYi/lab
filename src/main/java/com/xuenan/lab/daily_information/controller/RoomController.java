package com.xuenan.lab.daily_information.controller;

import com.xuenan.lab.daily_information.model.ResponseModel;
import com.xuenan.lab.daily_information.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService ;

    @GetMapping("/all")
    @ResponseBody
    public ResponseModel query(){
        return roomService.queryValidRooms();
    }

    @PutMapping("/ban/{id}")
    @ResponseBody
    public ResponseModel ban(@PathVariable("id") Integer id){
        return roomService.banRoom(id);
    }

    @RequestMapping("/enable/{id}")
    @ResponseBody
    public ResponseModel enable(@PathVariable("id") Integer id){
        return roomService.enableRoom(id);
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseModel create(@RequestParam("name") String name,@RequestParam("location") String location){
        return roomService.createRoom(name,location);
    }
}
