package com.xuenan.lab.equipment_management.service.impl;

import com.xuenan.lab.entity.EquipmentInformation;
import com.xuenan.lab.entity.EquipmentReservationRecord;
import com.xuenan.lab.equipment_management.dao.EquipmentManagementDao;
import com.xuenan.lab.equipment_management.model.ResponseModel;
import com.xuenan.lab.equipment_management.service.EquipmentManagementService;
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
                    responseModel = new ResponseModel(100, "所有设备都已被预约");
                }else {
                    responseModel = new ResponseModel();
                    responseModel.setData(equipments);
                }
            }
        }
        return responseModel;
    }

    @Override
    public ResponseModel cancelReservation(Integer recordId, Integer userId) {
        ResponseModel responseModel = null;
        List<EquipmentReservationRecord> reservationRecords = equipmentManagementDao.getReservationRecord(userId);
        if (reservationRecords == null || reservationRecords.size() < 1){
            responseModel = new ResponseModel(100, "您并未预约任何设备");
        } else{
            try{
                equipmentManagementDao.cancelReservation(recordId, userId);
                responseModel = new ResponseModel();
            }catch (Exception e){
                responseModel = new ResponseModel(112, "取消失败，请重试");
            }
        }
        return responseModel;
    }

    @Override
    public ResponseModel reserveEquipment(Integer equipmentId, Integer userId, Date reserveTime, Integer reserveDuration) {
        ResponseModel responseModel = null;
        //检查是否达到预约设备上限
        List<EquipmentReservationRecord> reservationRecords = equipmentManagementDao.getReservationRecord(userId);
        List<EquipmentReservationRecord> equipmentReservationRecords = equipmentManagementDao.getReservationRecordByEquipmentId(equipmentId);
        if(reservationRecords.size() < 5){
            //可以继续预约
            if(equipmentReservationRecords.size() < 1){
                equipmentManagementDao.reserveEquipment(equipmentId, userId, reserveTime, reserveDuration);
                responseModel = new ResponseModel();
            }else{
                int flag = 0; //计数器
                for(EquipmentReservationRecord record : equipmentReservationRecords){
                    Date startTime = record.getReserveTime();
                    Integer duration = record.getReserveDuration();
                    Calendar record_start = Calendar.getInstance();
                    Calendar record_end = Calendar.getInstance();
                    Calendar reserve_start = Calendar.getInstance();
                    Calendar reserve_end = Calendar.getInstance();
                    record_start.setTime(startTime);
                    record_end.setTime(startTime);
                    record_end.add(Calendar.HOUR_OF_DAY, duration); // 计算设备释放的时间

                    reserve_start.setTime(reserveTime);
                    reserve_end.setTime(reserveTime);
                    reserve_end.add(Calendar.HOUR_OF_DAY, reserveDuration);// 计算预约设备预计的释放时间

                    // 计算四个时间节点的特征值（精确到小时）
                    int lambdaReserveStart = 1000000 * reserve_start.get(Calendar.YEAR) +
                            10000 * reserve_start.get(Calendar.MONTH) +
                            100 * reserve_start.get(Calendar.DATE) +
                            reserve_start.get(Calendar.HOUR);
                    int lambdaReserveEnd = 1000000 * reserve_end.get(Calendar.YEAR) +
                            10000 * reserve_end.get(Calendar.MONTH) +
                            100 * reserve_end.get(Calendar.DATE) +
                            reserve_end.get(Calendar.HOUR);
                    int lambdaRecordStart = 1000000 * record_start.get(Calendar.YEAR) +
                            10000 * record_start.get(Calendar.MONTH) +
                            100 * record_start.get(Calendar.DATE) +
                            record_start.get(Calendar.HOUR);
                    int lambdaRecordEnd = 1000000 * record_end.get(Calendar.YEAR) +
                            10000 * record_end.get(Calendar.MONTH) +
                            100 * record_end.get(Calendar.DATE) +
                            record_end.get(Calendar.HOUR);
                    if(record.getStatus() != 1 && (lambdaReserveStart >= lambdaRecordEnd || lambdaReserveEnd <= lambdaRecordStart)){
                        flag++;
                    }
                }
                if(flag == equipmentReservationRecords.size()){
                    equipmentManagementDao.reserveEquipment(equipmentId, userId, reserveTime, reserveDuration);
                    responseModel = new ResponseModel();
                }else{
                    responseModel = new ResponseModel(111, "该设备已被预约，请选择其他时间或其他设备");
                }
            }
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
