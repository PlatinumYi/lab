package com.xuenan.lab.equipment_management.controller;

import com.xuenan.lab.equipment_management.model.ResponseModel;
import com.xuenan.lab.equipment_management.service.EquipmentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.Date;

/**
 * @author Howie Lu
 * @version Updated at 2019/10/15
 * @description
 */
@RestController
@RequestMapping("/equipment")
public class EquipmentManagementController {
    @Autowired
    EquipmentManagementService equipmentManagementService;

    @GetMapping("/")
    ResponseModel getAllEquipments(){
        return equipmentManagementService.getAllEquipments();
    }

    @GetMapping("/availableEquipment")
    ResponseModel getAvailableEquipments(){
        return equipmentManagementService.getAvailableEquipments();
    }

    @GetMapping("/reservedEquipment")
    ResponseModel getReservedEquipments(){
        return equipmentManagementService.getReservedEquipments();
    }

    @GetMapping("/reservationRecord/{userId}")
    ResponseModel getReservationRecord(@PathVariable String userId){
        return equipmentManagementService.getReservationRecord(Integer.valueOf(userId));
    }

    @GetMapping("/reservationRecord/equipment/{equipmentId}")
    ResponseModel getReservationRecordByEquipmentId(@PathVariable String equipmentId){
        return equipmentManagementService.getReservationRecordByEquipmentId(Integer.valueOf(equipmentId));
    }

    @GetMapping("/name")
    ResponseModel getEquipmentByName(@RequestParam("name") String name){
        return equipmentManagementService.getEquipmentByName(name);
    }

    //  2019-08-31前的需求代码
    //@PostMapping("/reservationRecord")
    //ResponseModel reserveEquipment(HttpServletRequest request) throws ParseException {
    //    Integer equipmentId = Integer.valueOf(request.getParameter("equipmentId"));
    //    Integer userId = Integer.valueOf(request.getParameter("userId"));
    //    Integer reserveDuration = Integer.valueOf(request.getParameter("reserveDuration"));
    //    String time = request.getParameter("reserveTime");
    //    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //    TimeZone china = TimeZone.getTimeZone("GMT+:08:00");
    //    formatter.setTimeZone(china);
    //    Date reserveTime = formatter.parse(time);
    //    return equipmentManagementService.reserveEquipment(equipmentId, userId, reserveTime, reserveDuration);
    //}

    @PostMapping("/reservationRecord")
    ResponseModel reserveEquipment(@RequestParam("equipmentId") String equipmentIdS,
                                   @RequestParam("userId") String userIdS,
                                   @RequestParam("reserveTime") String reserveTime,
                                   @RequestParam("reserveDuration") String reserveDurationS){
        //用LocalDate类解析"yyyy-MM-dd"，并将其转化为Date
        LocalDate reserveDate = LocalDate.parse(reserveTime);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = reserveDate.atStartOfDay().atZone(zone).toInstant();
        Date date = Date.from(instant);
        Integer equipmentId = Integer.valueOf(equipmentIdS);
        Integer userId = Integer.valueOf(userIdS);
        Integer reserveDuration = Integer.valueOf(reserveDurationS);
        //System.out.println(date);
        return equipmentManagementService.reserveEquipment(equipmentId, userId, date, reserveDuration);
    }

    //@PostMapping("/reservationRecord")
    //ResponseModel reserveEquipment(@RequestParam("equipmentId") Integer equipmentId,
    //                               @RequestParam("userId") Integer userId,
    //                               @RequestParam("reserveTime") Date reserveTime,//?Date怎么传
    //                               @RequestParam("reserveDuration") Integer reserveDuration){
    //
    //    return equipmentManagementService.reserveEquipment(equipmentId, userId, reserveTime, reserveDuration);
    //}

    @DeleteMapping("/reservationRecord")
    ResponseModel cancelReservation(@RequestParam("recordId") Integer recordId,
                                    @RequestParam("userId") Integer userId){
        return equipmentManagementService.cancelReservation(recordId, userId);
    }

    @PutMapping("/reservationRecord")
    ResponseModel approveReservation(@RequestParam("teacherId") Integer teacherId,
                                     @RequestParam("reservationId") Integer reservationId,
                                     @RequestParam("opinion") Integer opinion){
        return equipmentManagementService.approveReservation(teacherId, reservationId, opinion);
    }
}
