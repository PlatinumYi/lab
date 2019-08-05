package com.xuenan.starter.equipment_management.service.impl;

import com.xuenan.starter.entity.EquipmentInformation;
import com.xuenan.starter.entity.EquipmentReservationRecord;
import com.xuenan.starter.equipment_management.dao.EquipmentManagementDao;
import com.xuenan.starter.equipment_management.model.ResponseModel;
import com.xuenan.starter.equipment_management.service.EquipmentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Howie Lu
 * @version Updated at 2019/08/01
 * @description
 */
@Service("EquipmentManagementService")
public class EquipmentManagementServiceImpl implements EquipmentManagementService {

    @Autowired
    EquipmentManagementDao equipmentManagementDao;

    @Override
    public ResponseModel getAllEquipments() {
        ResponseModel responseModel;
        List<EquipmentInformation> equipments = equipmentManagementDao.getAllEquipments();
        if (equipments == null || equipments.size() < 1){
            responseModel = new ResponseModel(111, "没有实验设备");
        } else{
            responseModel = new ResponseModel();
            responseModel.setData(equipments);
        }
        return responseModel;
    }

    @Override
    public ResponseModel getAvailableEquipments() {
        ResponseModel responseModel;
        List<EquipmentInformation> equipments = equipmentManagementDao.getAvailableEquipments();
        if (equipments == null || equipments.size() < 1){
            responseModel = new ResponseModel(111, "没有可用的实验设备");
        } else{
            responseModel = new ResponseModel();
            responseModel.setData(equipments);
        }
        return responseModel;
    }

    @Override
    public ResponseModel getReservedEquipments() {
        ResponseModel responseModel;
        List<EquipmentInformation> equipments = equipmentManagementDao.getReservedEquipments();
        if (equipments == null || equipments.size() < 1){
            responseModel = new ResponseModel(111, "没有已预约的实验设备");
        } else{
            responseModel = new ResponseModel();
            responseModel.setData(equipments);
        }
        return responseModel;
    }

    @Override
    public ResponseModel getReservationRecord(Integer userId) {
        ResponseModel responseModel = null;
        List<EquipmentReservationRecord> records = equipmentManagementDao.getReservationRecord(userId);
        if (records == null || records.size() < 1){
            responseModel = new ResponseModel(111, "您还没有预约设备");
        }else{
            for (EquipmentReservationRecord record: records) {
                responseModel = new ResponseModel();
                responseModel.setData(records);
            }
        }
        return responseModel;
    }

    @Override
    public ResponseModel getEquipmentByName(String name) {
        ResponseModel responseModel = null;
        List<EquipmentInformation> equipments = equipmentManagementDao.getEquipmentByName(name);
        if (equipments == null || equipments.size() < 1){
            responseModel = new ResponseModel(111, "不存在该设备");
        }else {
            for (EquipmentInformation equipment : equipments){
                if (equipment.getValid() == 0) {
                    responseModel = new ResponseModel(100, "该设备已被预约");
                }else {
                    responseModel = new ResponseModel();
                    responseModel.setData(equipment);
                }
            }
        }
        return responseModel;
    }

    @Override
    public ResponseModel cancelReservation(Integer equipmentId, Integer userId) {
        ResponseModel responseModel = null;
        List<EquipmentReservationRecord> reservationRecords = equipmentManagementDao.getReservationRecord(userId);
        if (reservationRecords == null || reservationRecords.size() < 1){
            responseModel = new ResponseModel(100, "您并未预约任何设备");
        } else {
            for (EquipmentReservationRecord record: reservationRecords) {
                if(record.getEquipmentId().equals(equipmentId)) {
                    int row = equipmentManagementDao.cancelReservation(equipmentId, userId);
                    if (row != 0){
                        responseModel = new ResponseModel();
                    }else {
                        responseModel = new ResponseModel(112, "取消失败，请重试");
                    }

                }
            }
        }
        return responseModel;
    }

    @Override
    public ResponseModel reserveEquipment(Integer equipmentId, Integer userId, Date reserveTime, Integer reserveDuration) {
        ResponseModel responseModel;
        //检查是否达到预约设备上限
        List<EquipmentReservationRecord> reservationRecords = equipmentManagementDao.getReservationRecord(userId);
        if(reservationRecords.size() < 5){
            //可以继续预约
            equipmentManagementDao.reserveEquipment(equipmentId, userId, reserveTime, reserveDuration);
            responseModel = new ResponseModel();
        }else{
            //预约上限
            responseModel = new ResponseModel(112, "您的预约达到上限");
        }
        return responseModel;
    }

    @Override
    public ResponseModel approveReservation(Integer teacherId, Integer reservationId, Integer opinion) {
        ResponseModel responseModel;
        int row = equipmentManagementDao.approveReservation(teacherId, reservationId, opinion);
        if(row != 0){
            responseModel = new ResponseModel();
        }else{
            responseModel = new ResponseModel(112, "操作失败，请重试");
        }
        return responseModel;
    }
}
