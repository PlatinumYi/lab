package com.xuenan.lab.daily_information.dao;

import com.xuenan.lab.entity.Room;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RoomDao {

    @Select("SELECT * FROM room")
    List<Room> queryValidRooms();

    @Select("SELECT * FROM room WHERE ID=#{id}")
    Room queryRoomById(Integer id);

    @Update("UPDATE room SET STATUS=1 WHERE ID=#{id}")
    Integer enableRoomById(Integer id);

    @Update("UPDATE room SET STATUS=0 WHERE ID=#{id}")
    Integer banRoomById(Integer id);

    @Insert("INSERT INTO room(NAME,LOCATION) VALUES(#{name},#{location})")
    Integer createRoom(String name ,String location);

}
