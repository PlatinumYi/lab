package com.xuenan.lab.equipment_management.service.impl;

import com.xuenan.lab.entity.EquipmentInformation;
import com.xuenan.lab.entity.EquipmentReservationRecord;
import com.xuenan.lab.entity.User;
import com.xuenan.lab.equipment_management.dao.EquipmentManagementDao;
import com.xuenan.lab.equipment_management.model.ResponseModel;
import com.xuenan.lab.equipment_management.service.EquipmentManagementService;
import com.xuenan.lab.tool.BeijingTime;
import com.xuenan.lab.user_management.dao.UserDao;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author Howie Lu
 * @version Updated at 2019/10/15
 * @description
 */
@Service("EquipmentManagementService")
public class EquipmentManagementServiceImpl implements EquipmentManagementService {

    private static final long MAX_GUIDE_BOOK_SIZE = 1024*1024*20;

    private static final String LOCAL_STORAGE ="/root/LabFiles" ;

    private static final String MODULE_URL = "/experiment/guide/" ;

    private static final String DATA_URL = "/data/" ;

    @Autowired
    EquipmentManagementDao equipmentManagementDao;

    @Autowired
    UserDao userDao ;
    @Override
    public ResponseModel getAllEquipments() {
        ResponseModel responseModel;
        List<EquipmentInformation> equipments = equipmentManagementDao.getAllEquipments();
        if (equipments == null || equipments.size() < 1) {
            responseModel = new ResponseModel(111, "没有实验设备");
        } else {
            responseModel = new ResponseModel();
            responseModel.setData(equipments);
        }
        return responseModel;
    }

    @Override
    public ResponseModel getAvailableEquipments() {
        ResponseModel responseModel;
        List<EquipmentInformation> equipments = equipmentManagementDao.getAvailableEquipments();
        if (equipments == null || equipments.size() < 1) {
            responseModel = new ResponseModel(111, "没有可用的实验设备");
        } else {
            responseModel = new ResponseModel();
            responseModel.setData(equipments);
        }
        return responseModel;
    }

    @Override
    public ResponseModel getReservedEquipments() {
        ResponseModel responseModel;
        List<EquipmentInformation> equipments = equipmentManagementDao.getReservedEquipments();
        if (equipments == null || equipments.size() < 1) {
            responseModel = new ResponseModel(111, "没有已预约的实验设备");
        } else {
            responseModel = new ResponseModel();
            responseModel.setData(equipments);
        }
        return responseModel;
    }

    @Override
    public ResponseModel getReservationRecord(Integer userId) {
        ResponseModel responseModel = null;
        List<EquipmentReservationRecord> records = equipmentManagementDao.getReservationRecord(userId);
        if (records == null || records.size() < 1) {
            responseModel = new ResponseModel(111, "您还没有预约设备");
        } else {
            try {
                List<Map<String, Object>> model = new ArrayList<>();
                for (EquipmentReservationRecord record : records) {
                    Map<String, Object> map = new HashMap<>();
                    responseModel = new ResponseModel();
                    EquipmentInformation information = equipmentManagementDao.getEquipmentById(record.getEquipmentId());
                    map.put("recordId", record.getId());
                    map.put("equipmentId", record.getEquipmentId());
                    map.put("equipmentName", information.getName());
                    map.put("userId", record.getUserId());
                    map.put("reserveTime", record.getReserveTime());
                    map.put("reserveDuration", record.getReserveDuration());
                    model.add(map);
                }
                responseModel.setData(model);
            }catch (Exception e){
                responseModel = new ResponseModel(112, "操作失败，请重试");
            }

        }
        return responseModel;
    }

    @Override
    public ResponseModel getReservationRecordByEquipmentId(Integer equipmentId) {
        ResponseModel responseModel = null;
        List<EquipmentReservationRecord> records = equipmentManagementDao.getReservationRecordByEquipmentId(equipmentId);
        if (records == null || records.size() < 1) {
            responseModel = new ResponseModel(111, "该设备尚无预约记录");
        } else {
            try {
                responseModel = new ResponseModel();
                List<Map<String, Object>> model = new ArrayList<>();
                for (EquipmentReservationRecord record : records) {
                    //map.clear();
                    Map<String, Object> map = new HashMap<>();
                    map.put("recordId", record.getId());
                    map.put("userId", record.getUserId());
                    map.put("equipmentId", record.getEquipmentId());
                    map.put("reservedDate", record.getReserveTime());
                    map.put("reservedDuration", record.getReserveDuration());
                    model.add(map);
                }
                responseModel.setData(model);
            } catch (Exception e) {
                responseModel = new ResponseModel(112, "操作失败，请重试");
            }
        }
        return responseModel;
    }

    @Override
    public ResponseModel getEquipmentByName(String name) {
        ResponseModel responseModel = null;
        List<EquipmentInformation> equipments = equipmentManagementDao.getEquipmentByName(name);
        if (equipments == null || equipments.size() < 1) {
            responseModel = new ResponseModel(111, "不存在该设备");
        } else {
            for (EquipmentInformation equipment : equipments) {
                if (equipment.getValid() == 0) {
                    responseModel = new ResponseModel(100, "所有设备都已被预约");
                } else {
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
        if (reservationRecords == null || reservationRecords.size() < 1) {
            responseModel = new ResponseModel(100, "您并未预约任何设备");
        } else {
            try {
                equipmentManagementDao.cancelReservation(recordId, userId);
                responseModel = new ResponseModel();
            } catch (Exception e) {
                responseModel = new ResponseModel(112, "取消失败，请重试");
            }
        }
        return responseModel;
    }

    //@Override
    //public ResponseModel reserveEquipment(Integer equipmentId, Integer userId, Date reserveTime, Integer reserveDuration) {
    //    ResponseModel responseModel = null;
    //    //检查是否达到预约设备上限
    //    List<EquipmentReservationRecord> reservationRecords = equipmentManagementDao.getReservationRecord(userId);
    //    List<EquipmentReservationRecord> equipmentReservationRecords = equipmentManagementDao.getReservationRecordByEquipmentId(equipmentId);
    //    if(reservationRecords.size() < 5){
    //        //可以继续预约
    //        if(equipmentReservationRecords.size() < 1){
    //            equipmentManagementDao.reserveEquipment(equipmentId, userId, reserveTime, reserveDuration);
    //            responseModel = new ResponseModel();
    //        }else{
    //            int flag = 0; //计数器
    //            for(EquipmentReservationRecord record : equipmentReservationRecords){
    //                Date startTime = record.getReserveTime();
    //                Integer duration = record.getReserveDuration();
    //                Calendar record_start = Calendar.getInstance();
    //                Calendar record_end = Calendar.getInstance();
    //                Calendar reserve_start = Calendar.getInstance();
    //                Calendar reserve_end = Calendar.getInstance();
    //                record_start.setTime(startTime);
    //                record_end.setTime(startTime);
    //                record_end.add(Calendar.HOUR_OF_DAY, duration); // 计算设备释放的时间
    //
    //                reserve_start.setTime(reserveTime);
    //                reserve_end.setTime(reserveTime);
    //                reserve_end.add(Calendar.HOUR_OF_DAY, reserveDuration);// 计算预约设备预计的释放时间
    //
    //                // 计算四个时间节点的特征值（精确到小时）
    //                int lambdaReserveStart = 1000000 * reserve_start.get(Calendar.YEAR) +
    //                        10000 * reserve_start.get(Calendar.MONTH) +
    //                        100 * reserve_start.get(Calendar.DATE) +
    //                        reserve_start.get(Calendar.HOUR);
    //                int lambdaReserveEnd = 1000000 * reserve_end.get(Calendar.YEAR) +
    //                        10000 * reserve_end.get(Calendar.MONTH) +
    //                        100 * reserve_end.get(Calendar.DATE) +
    //                        reserve_end.get(Calendar.HOUR);
    //                int lambdaRecordStart = 1000000 * record_start.get(Calendar.YEAR) +
    //                        10000 * record_start.get(Calendar.MONTH) +
    //                        100 * record_start.get(Calendar.DATE) +
    //                        record_start.get(Calendar.HOUR);
    //                int lambdaRecordEnd = 1000000 * record_end.get(Calendar.YEAR) +
    //                        10000 * record_end.get(Calendar.MONTH) +
    //                        100 * record_end.get(Calendar.DATE) +
    //                        record_end.get(Calendar.HOUR);
    //                if(record.getStatus() != 1 && (lambdaReserveStart >= lambdaRecordEnd || lambdaReserveEnd <= lambdaRecordStart)){
    //                    flag++;
    //                }
    //            }
    //            if(flag == equipmentReservationRecords.size()){
    //                equipmentManagementDao.reserveEquipment(equipmentId, userId, reserveTime, reserveDuration);
    //                responseModel = new ResponseModel();
    //            }else{
    //                responseModel = new ResponseModel(111, "该设备已被预约，请选择其他时间或其他设备");
    //            }
    //        }
    //    }else{
    //        //预约上限
    //        responseModel = new ResponseModel(112, "您的预约达到上限");
    //    }
    //    return responseModel;
    //}


    @Override
    public ResponseModel reserveEquipment(Integer equipmentId, Integer userId, Date reserveTime, Integer reserveDuration) {
        ResponseModel responseModel = null;
        // 逻辑应为：预约设备首先检查该用户是否达到预约上限，其次检查该设备在该时间段是否可用；两个检查都通过才可以预约成功

        //检查是否达到用户的预约设备上限
        List<EquipmentReservationRecord> reservationRecords = equipmentManagementDao.getReservationRecord(userId);
        List<EquipmentReservationRecord> equipmentReservationRecords = equipmentManagementDao.getReservationRecordByEquipmentId(equipmentId);
        if (reservationRecords.size() < 5) {
            //用户预约的设备不超过5台，可以继续预约

            if (equipmentReservationRecords.size() < 1) {
                //设备预约记录小于1个，
                equipmentManagementDao.reserveEquipment(equipmentId, userId, reserveTime, reserveDuration);
                responseModel = new ResponseModel();
            } else {
                //当前时间
                Date now = BeijingTime.getBeijingTime(new Date());
                long mills = now.getTime() + 1000 * 24 * 3600 * 4;
                Date nowPlusDays = new Date(mills);
                System.out.println("EquipmentManagementService:now : " + now);
                System.out.println("EquipmentManagementService:nowPlusDays : " + nowPlusDays);
                Date reserve = BeijingTime.getBeijingTime(reserveTime);
                System.out.println ("EquipmentManagementService:reserve : " + reserve);

                // 通过检查预约记录判断当前设备在预约时段是否可用
                boolean flag = false; //表示是否可以预约
                for (EquipmentReservationRecord record : equipmentReservationRecords) {
                    flag = false;
                    if (record.getStatus() == 1) {
                        if (!record.getReserveTime().equals(reserveTime)) {
                            // 记录中的预约记录与预约时间不同日, 可以直接预约
                            flag = true;
                        } else {
                            // 只需检查记录中与预约时间同天的，且已经被同意的预约记录
                            if (reserve.before(nowPlusDays) && (record.getReserveDuration() & reserveDuration) != 0) {
                                // 按位与操作，若记录中和预约请求有同为1的位，则会预约失败
                                // 例如：记录中有111（预约早中晚），而预约请求中为100（只预约早上），按位与操作不是0，预约失败；
                                // 但若记录中为110（预约早上和下午），请求为001（只预约晚上），按位与操作为0，预约可以成功
                                // 预约的时间大于当前时间加上4，即提前五天以内，可以预约
                                System.out.println("situation 1");
                                flag = true;
                            } else {
                                System.out.println("situation 2");
                                responseModel = new ResponseModel(111, "该设备已被预约，请选择其他时间或其他设备");
                            }
                        }
                    } else {
                        responseModel = new ResponseModel(112, "预约失败");
                    }
                }
                if (flag) {
                    //long mill0 = reserve.getTime() + 24*3600*1000;
                    //Date
                    try{
                        equipmentManagementDao.reserveEquipment(equipmentId, userId, reserve, reserveDuration);
                        EquipmentReservationRecord returnRecord = equipmentManagementDao.getOneReservationRecord(equipmentId, userId, reserve, reserveDuration);
                        responseModel = new ResponseModel();
                        responseModel.setData(returnRecord);
                    }catch (Exception e){
                        responseModel = new ResponseModel(112, "操作失败，请稍候再试");
                    }

                }
            }
        } else {
            responseModel = new ResponseModel(112, "您的预约超过限制");
        }
        return responseModel;
    }

    @Override
    public ResponseModel approveReservation(Integer teacherId, Integer reservationId, Integer opinion) {
        ResponseModel responseModel;
        int row = equipmentManagementDao.approveReservation(teacherId, reservationId, opinion);
        if (row != 0) {
            responseModel = new ResponseModel();
        } else {
            responseModel = new ResponseModel(112, "操作失败，请重试");
        }
        return responseModel;
    }

    @Override
    public ResponseModel getReservationRecordExcel() {
        ResponseModel model ;
        List<EquipmentReservationRecord>  records = equipmentManagementDao.getAllReservationRecord() ;
        if( records == null ){
            model = new ResponseModel(113,"获取记录数据失败");
        }else {
            HSSFWorkbook workbook = new HSSFWorkbook();
            String[] title = {"设备名称","借用人员姓名","借用人员学号","借用时间","审批情况"} ;
            HSSFSheet sheet = workbook.createSheet("借用记录");
            HSSFRow row = sheet.createRow(0);
            for( int i=0 ; i<title.length ; i++ ){
                row.createCell(i).setCellValue(title[i]);
            }
            for( int i=1 ; i<=records.size() ; i++ ){
                row = sheet.createRow(i);
                EquipmentReservationRecord record = records.get(i);
                if( record.getEquipmentId() != null ) {
                    EquipmentInformation information = equipmentManagementDao.getEquipmentById(record.getId());
                    row.createCell(0).setCellValue(information.getName());
                }
                if( record.getUserId() != null ) {
                    User user = userDao.queryUserById(record.getUserId());
                    row.createCell(1).setCellValue(user.getName());
                    row.createCell(2).setCellValue(user.getSchoolNumber());
                }
                if( record.getReserveTime() != null ) {
                    String duration = "" ;
                    if( record.getReserveDuration() != null ){
                        Integer num = record.getReserveDuration();
                        if( num>4 ){
                            duration = duration + "早晨 " ;
                            num -= 4;
                        }
                        if( num>2 ){
                            duration = duration + "下午 " ;
                            num -= 2;
                        }
                        if( num>1 ){
                            duration = duration + "晚上 " ;
                            num -= 1;
                        }
                    }
                    row.createCell(3).setCellValue(new SimpleDateFormat("yyyy-MM-dd").format(record.getReserveTime())+duration);
                }
                if( record.getStatus() != null ) {
                    row.createCell(4).setCellValue(record.getStatus()==1?"是":"否");
                }
            }

            String fileName = "EquipmentReservationRecord"+".xls" ;
            File file = new File(LOCAL_STORAGE+DATA_URL+fileName);
            if( file.exists() ){
                file.delete() ;
            }

            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                workbook.write(outputStream);
                outputStream.close();
            } catch (IOException e) {
                model = new ResponseModel(114,"导出设备预约记录失败");
                return model ;
            }

            model = new ResponseModel();
            model.setData(DATA_URL+fileName);

        }
        return model ;
    }
}
