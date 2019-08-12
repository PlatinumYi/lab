package com.xuenan.lab.experiment_management.controller;

import com.xuenan.lab.entity.LoginSession;
import com.xuenan.lab.entity.User;
import com.xuenan.lab.experiment_management.model.ResponseModel;
import com.xuenan.lab.experiment_management.service.ReportService;
import com.xuenan.lab.user_management.service.LoginSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService ;

    @Autowired
    private LoginSessionService loginSessionService ;

    @GetMapping("/experiment/{id}")
    @ResponseBody
    public ResponseModel queryReportByExperimentId(HttpServletRequest request, @PathVariable Integer id){
        String token = request.getHeader("token");
        LoginSession currentUser = loginSessionService.queryValidLoginSessionByToken(token);
        return reportService.queryReportByExperimentId(currentUser.getUserId(),id);
    }

    @GetMapping("/student/{id}")
    @ResponseBody
    public ResponseModel queryReportByStudentId(HttpServletRequest request, @PathVariable Integer id){
        String token = request.getHeader("token");
        LoginSession currentUser = loginSessionService.queryValidLoginSessionByToken(token);
        return reportService.queryReportByStudentId(currentUser.getUserId(),id);
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseModel createReport(HttpServletRequest request,@RequestParam("experimentId") Integer experimentId){
        String token = request.getHeader("token");
        LoginSession currentUser = loginSessionService.queryValidLoginSessionByToken(token);
        return reportService.createReport(currentUser.getUserId(),experimentId);
    }

    @PostMapping("/remove/{id}")
    @ResponseBody
    public ResponseModel removeReport(HttpServletRequest request,@PathVariable Integer id){
        String token = request.getHeader("token");
        LoginSession currentUser = loginSessionService.queryValidLoginSessionByToken(token);
        return reportService.removeReport(currentUser.getUserId(),id);
    }

    @PutMapping("/upload/{id}")
    @ResponseBody
    public ResponseModel changeReportFile(HttpServletRequest request,
                                      @RequestParam("file")MultipartFile file,
                                      @PathVariable Integer id){
        String token = request.getHeader("token");
        LoginSession currentUser = loginSessionService.queryValidLoginSessionByToken(token);
        return reportService.changeReportFile(currentUser.getUserId(),file,id);
    }

    @PutMapping("/mark/{id}")
    @ResponseBody
    public ResponseModel markReport(HttpServletRequest request,
                                      @PathVariable Integer id,
                                      @RequestParam("mark") Integer mark){
        String token = request.getHeader("token");
        LoginSession currentUser = loginSessionService.queryValidLoginSessionByToken(token);
        return reportService.markReport(currentUser.getUserId(),id,mark);
    }
}
