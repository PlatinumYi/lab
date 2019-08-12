package com.xuenan.lab.equipment_management.controller;

import com.xuenan.lab.equipment_management.model.ResponseModel;
import com.xuenan.lab.equipment_management.service.EquipmentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Howie Lu
 * @version Updated at 2019/08/01
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
    ResponseModel getReservationRecord(@PathVariable Integer userId){
        return equipmentManagementService.getReservationRecord(userId);
    }

    @GetMapping("/name")
    ResponseModel getEquipmentByName(@RequestParam("name") String name){
        return equipmentManagementService.getEquipmentByName(name);
    }

    @PostMapping("/reservationRecord")
    ResponseModel reserveEquipment(HttpServletRequest request) throws ParseException {
        Integer equipmentId = Integer.valueOf(request.getParameter("equipmentId"));
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer reserveDuration = Integer.valueOf(request.getParameter("reserveDuration"));
        String time = request.getParameter("reserveTime");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone china = TimeZone.getTimeZone("GMT+:08:00");
        formatter.setTimeZone(china);
        Date reserveTime = formatter.parse(time);
        return equipmentManagementService.reserveEquipment(equipmentId, userId, reserveTime, reserveDuration);
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
    ResponseModel cancelReservation(@RequestParam("equipmentId") Integer equipmentId,
                                    @RequestParam("userId") Integer userId){
        return equipmentManagementService.cancelReservation(equipmentId, userId);
    }

    @PutMapping("/reservationRecord")
    ResponseModel approveReservation(@RequestParam("teacherId") Integer teacherId,
                                     @RequestParam("reservationId") Integer reservationId,
                                     @RequestParam("opinion") Integer opinion){
        return equipmentManagementService.approveReservation(teacherId, reservationId, opinion);
    }
}
