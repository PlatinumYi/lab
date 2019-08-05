package com.xuenan.starter.entity;

import java.util.Date;

/**
 * @author Howie Lu
 * @version Updated at 2019/08/01
 * @description 建表语句如下：
 * CREATE TABLE `equipment_reservation_record` (
 *   `ID` int(11) NOT NULL AUTO_INCREMENT,
 *   `EQUIPMENT_ID` int(11) NOT NULL COMMENT '设备id',
 *   `USER_ID` int(11) NOT NULL COMMENT '预约者id',
 *   `RESERVE_TIME` datetime NOT NULL COMMENT '预约使用时间',
 *   `RESERVE_DURATION` int(11) NOT NULL COMMENT '使用时长',
 *   `STATUS` int(11) NOT NULL DEFAULT '0' COMMENT '0代表未审批，1代表同意，2代表拒绝',
 *   `APPROVER` int(11) NOT NULL DEFAULT '0' COMMENT '审批人的id',
 *   PRIMARY KEY (`ID`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
 */
public class EquipmentReservationRecord {
    private Integer id;
    private Integer equipmentId;
    private Integer userId;
    private Date reserveTime;
    private Integer reserveDuration;
    private Integer status;
    private Integer approver;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }

    public Integer getReserveDuration() {
        return reserveDuration;
    }

    public void setReserveDuration(Integer reserveDuration) {
        this.reserveDuration = reserveDuration;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getApprover() {
        return approver;
    }

    public void setApprover(Integer approver) {
        this.approver = approver;
    }
}
