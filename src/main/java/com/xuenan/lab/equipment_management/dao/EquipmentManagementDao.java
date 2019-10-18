package com.xuenan.lab.equipment_management.dao;

import com.xuenan.lab.entity.EquipmentInformation;
import com.xuenan.lab.entity.EquipmentReservationRecord;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author Howie Lu
 * @version Updated at 2019/08/01
 * @description 设备管理
 */

@Mapper
public interface EquipmentManagementDao {

    @Select("SELECT * FROM equipment_information")
    List<EquipmentInformation> getAllEquipments();

    @Select("SELECT * FROM equipment_information WHERE valid=1")
    List<EquipmentInformation> getAvailableEquipments();

    @Select("SELECT * FROM equipment_information WHERE valid=0")
    List<EquipmentInformation> getReservedEquipments();

    @Select("SELECT * FROM equipment_information WHERE id=#{equipmentId}")
    EquipmentInformation getEquipmentById(Integer equipmentId);

    @Select("SELECT * FROM equipment_reservation_record WHERE equipment_id=#{equipmentId}")
    List<EquipmentReservationRecord> getReservationRecordByEquipmentId(Integer equipmentId);

    @Select("SELECT * FROM equipment_reservation_record WHERE equipment_id=#{equipmentId} AND user_id=#{userId} AND " +
            "reserve_time=#{reserveTime} AND reserve_duration=#{reserveDuration}")
    EquipmentReservationRecord getOneReservationRecord(@Param("equipmentId") Integer equipmentId,
                                                       @Param("userId") Integer userId,
                                                       @Param("reserveTime") Date reserveTime,
                                                       @Param("reserveDuration") Integer reserveDuration);
    // 一个人最多预约五台实验设备
    @Select("SELECT * FROM equipment_reservation_record WHERE user_id=#{userId} and reserve_time >= now()")
    List<EquipmentReservationRecord> getReservationRecord(@Param("userId") Integer userId);

    @Select("SELECT * FROM equipment_information WHERE name=#{name, jdbcType=VARCHAR}")
    List<EquipmentInformation> getEquipmentByName(@Param("name") String name);

    /**
     * 预约实验设备
     * @param equipmentId 实验设备id
     * @param userId 用户id
     * @param reserveTime 预约的设备使用时间
     * @param reserveDuration 预约时长
     */
    @Insert("INSERT INTO equipment_reservation_record (equipment_id, user_id, reserve_time, reserve_duration) " +
            "VALUES (#{equipmentId}, #{userId}, #{reserveTime, jdbcType=DATE}, #{reserveDuration})")
    void reserveEquipment(@Param("equipmentId") Integer equipmentId,
                             @Param("userId") Integer userId,
                             @Param("reserveTime") Date reserveTime,
                             @Param("reserveDuration") Integer reserveDuration);

    /**
     * 取消预约
     * @param recordId 预约记录id
     * @param userId 用户id
     * @return 被影响的行数
     */
    @Delete("DELETE FROM equipment_reservation_record WHERE id=#{recordId} AND user_id=#{userId}")
    Integer cancelReservation(@Param("recordId") Integer recordId,
                              @Param("userId") Integer userId);

    @Update("UPDATE equipment_reservation_record SET status=#{opinion}, approver=#{teacherId} " +
            "WHERE id=#{reservationId}")
    Integer approveReservation(@Param("teacherId") Integer teacherId,
                               @Param("reservationId") Integer reservationId,
                               @Param("opinion") Integer opinion);
}
