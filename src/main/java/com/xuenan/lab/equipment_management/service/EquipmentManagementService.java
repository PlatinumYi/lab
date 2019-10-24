package com.xuenan.lab.equipment_management.service;

import com.xuenan.lab.equipment_management.model.ResponseModel;

import java.util.Date;

/**
 * @author Howie Lu
 * @version Updated at 2019/10/15
 * @description
 */
public interface EquipmentManagementService {
    /**
     * 获得所有设备（包括不可用）
     * @return 数据模型
     */
    ResponseModel getAllEquipments();

    /**
     * 获得所有可用设备
     * @return 数据模型
     */
    ResponseModel getAvailableEquipments();

    /**
     * 获得已被预约的设备
     * @return 数据模型
     */
    ResponseModel getReservedEquipments();

    /**
     * 获得某用户的预约记录
     * @param userId 用户id
     * @return 数据模型
     */
    ResponseModel getReservationRecord(Integer userId);

    /**
     * 获得某用户的预约记录
     * @param equipmentId 用户id
     * @return 数据模型
     */
    ResponseModel getReservationRecordByEquipmentId(Integer equipmentId);

    /**
     * 通过设备名称获得设备信息
     * @param name 设备名
     * @return 数据模型
     */
    ResponseModel getEquipmentByName(String name);

    /**
     * 预约某设备（对某设备发起预约申请）
     * @param equipmentId 设备id
     * @param userId 用户id
     * @param reserveTime 预约的使用时间
     * @param reserveDuration 设备使用时长
     * @return 数据模型
     */
    ResponseModel reserveEquipment(Integer equipmentId, Integer userId, Date reserveTime, Integer reserveDuration);

    /**
     * 用户取消预约
     * @param recordId 预约记录id
     * @param userId 用户id
     * @return 数据模型
     */
    ResponseModel cancelReservation(Integer recordId, Integer userId);

    /**
     * 教师审批设备预约申请
     * @param teacherId 教师的id（实质上仍是用户id）
     * @param reservationId 预约单的id
     * @param opinion 审批意见（同意为1，拒绝为2）
     * @return 数据模型
     */
    ResponseModel approveReservation(Integer teacherId, Integer reservationId, Integer opinion);

    ResponseModel getReservationRecordExcel();
}
