package com.xuenan.lab.daily_information.service.impl;

import com.xuenan.lab.daily_information.dao.RoomDao;
import com.xuenan.lab.daily_information.model.ResponseModel;
import com.xuenan.lab.daily_information.service.RoomService;
import com.xuenan.lab.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDao roomDao ;

    @Override
    public ResponseModel queryValidRooms() {

        List<Room> rooms = roomDao.queryValidRooms();
        ResponseModel model = new ResponseModel();
        model.setData(rooms);
        return model ;

    }

    @Override
    public ResponseModel enableRoom(Integer id) {

        ResponseModel model ;
        Room room = roomDao.queryRoomById(id);
        if( room==null ){
            model = new ResponseModel(1501,"目标实验室不存在");
        }else if( room.getStatus()==1 ){
            model = new ResponseModel(1502,"目标实验室已经是可用状态了");
        }else {
            roomDao.enableRoomById(id);
            model = new ResponseModel();
        }
        return model ;
    }

    @Override
    public ResponseModel banRoom(Integer id) {
        ResponseModel model ;
        Room room = roomDao.queryRoomById(id);
        if( room==null ){
            model = new ResponseModel(1501,"目标实验室不存在");
        }else if( room.getStatus()==0 ){
            model = new ResponseModel(1503,"目标实验室已经是禁用状态了");
        }else {
            roomDao.banRoomById(id);
            model = new ResponseModel();
        }
        return model ;
    }

    @Override
    public ResponseModel createRoom(String name, String location) {

        ResponseModel model ;
        Integer result = roomDao.createRoom(name,location);
        if( result<=0 ){
            model = new ResponseModel(1504,"创建实验室失败");
        }else {
            model = new ResponseModel();
        }
        return model ;
    }
}
