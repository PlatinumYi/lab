package com.xuenan.lab.daily_information.service;

import com.xuenan.lab.daily_information.model.ResponseModel;

public interface RoomService {

    ResponseModel queryValidRooms();
    ResponseModel enableRoom(Integer id);
    ResponseModel banRoom(Integer id);
    ResponseModel createRoom( String name,String location);
}
