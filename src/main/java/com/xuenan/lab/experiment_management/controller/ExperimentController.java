package com.xuenan.lab.experiment_management.controller;

import com.xuenan.lab.experiment_management.model.ResponseModel;
import com.xuenan.lab.experiment_management.service.ExperimentService;
import com.xuenan.lab.user_management.service.LoginSessionService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/experiment")
public class ExperimentController {

    @Autowired
    private ExperimentService experimentService ;

    @Autowired
    private LoginSessionService loginSessionService ;

    @GetMapping("/self")
    @ResponseBody
    public ResponseModel querySelfExperiment(HttpServletRequest request){
        String token = request.getHeader("token");
        Integer id = loginSessionService.queryValidLoginSessionByToken(token).getUser().getId();
        return experimentService.querySelfExperiment(id);
    }

    @GetMapping("/accessible")
    @ResponseBody
    public ResponseModel queryAccessibleExperiment(){
        return experimentService.queryAccessibleExperiment();
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseModel createExperiment(
            HttpServletRequest request,
            @RequestParam("name") String name,
            @RequestParam("instruction") String instruction,
            @RequestParam("teacher_name") String teacherName,
            @RequestParam("accessible_until") @DateTimeFormat(pattern ="yyyy-MM-dd") Date accessibleUntil,
            @RequestParam("report_until") @DateTimeFormat(pattern ="yyyy-MM-dd") Date reportUntil,
            @RequestParam("max_student_number") Integer maxStudentNumber,
            @RequestParam("begin_time") Integer beginTime,
            @RequestParam("stop_time") Integer stopTime,
            @RequestParam("room_id") Integer roomId){
        String token = request.getHeader("token");
        Integer id = loginSessionService.queryValidLoginSessionByToken(token).getUser().getId();
        return experimentService.createExperiment(name,instruction,id,teacherName,accessibleUntil,reportUntil,maxStudentNumber,beginTime,stopTime,roomId);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseModel changeExperiment(
            HttpServletRequest request,
            @PathVariable Integer id,
            @RequestParam("name") String name,
            @RequestParam("instruction") String instruction,
            @RequestParam("teacher_name") String teacherName,
            @RequestParam("accessible_until") @DateTimeFormat(pattern ="yyyy-MM-dd") Date accessibleUntil,
            @RequestParam("report_until") @DateTimeFormat(pattern ="yyyy-MM-dd") Date reportUntil,
            @RequestParam("max_student_number") Integer maxStudentNumber,
            @RequestParam("begin_time") Integer beginTime,
            @RequestParam("stop_time") Integer stopTime,
            @RequestParam("room_id") Integer roomId){
        String token = request.getHeader("token");
        Integer user_id = loginSessionService.queryValidLoginSessionByToken(token).getUser().getId();
        return experimentService.changeExperiment(user_id,id, name, instruction, teacherName, accessibleUntil, reportUntil, maxStudentNumber,beginTime,stopTime,roomId);
    }

    @PutMapping("/book/{id}")
    @ResponseBody
    public ResponseModel changeExperimentBook(
            HttpServletRequest request,
            @PathVariable Integer id,
            @RequestParam("book") MultipartFile file){
        String token = request.getHeader("token");
        Integer user_id = loginSessionService.queryValidLoginSessionByToken(token).getUser().getId();
        return experimentService.changeExperimentBook(user_id,id,file);
    }

    @GetMapping("/excel/{id}")
    @ResponseBody
    public ResponseModel getExcel(
            HttpServletRequest request,
            @PathVariable Integer id){
        String token = request.getHeader("token");
        Integer user_id = loginSessionService.queryValidLoginSessionByToken(token).getUser().getId();
        return experimentService.getFile(user_id,id);
    }

    @PutMapping("/start/{id}")
    @ResponseBody
    public ResponseModel startSignIn(
            HttpServletRequest request,
            @PathVariable Integer id,
            @RequestParam("longitude") Double longitude,
            @RequestParam("latitude") Double latitude){
        String token = request.getHeader("token");
        Integer user_id = loginSessionService.queryValidLoginSessionByToken(token).getUser().getId();
        return experimentService.startSignIn(user_id,id,longitude,latitude);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseModel deleteExperiment(HttpServletRequest request, @PathVariable Integer id){
        String token = request.getHeader("token");
        Integer user_id = loginSessionService.queryValidLoginSessionByToken(token).getUser().getId();
        return experimentService.deleteExperiment(user_id,id);
    }
}
