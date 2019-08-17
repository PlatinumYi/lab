package com.xuenan.lab.experiment_management.controller;

import com.xuenan.lab.entity.LoginSession;
import com.xuenan.lab.entity.User;
import com.xuenan.lab.experiment_management.model.ResponseModel;
import com.xuenan.lab.experiment_management.service.ReportService;
import com.xuenan.lab.user_management.service.LoginSessionService;
import org.apache.ibatis.annotations.Delete;
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
        return reportService.queryReportByExperimentId(currentUser.getUser().getId(),id);
    }

    @GetMapping("/self")
    @ResponseBody
    public ResponseModel queryReportByStudentId(HttpServletRequest request){
        String token = request.getHeader("token");
        LoginSession currentUser = loginSessionService.queryValidLoginSessionByToken(token);
        return reportService.queryReportByStudentId(currentUser.getUser().getId());
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseModel createReport(HttpServletRequest request,@RequestParam("experiment_id") Integer experimentId){
        String token = request.getHeader("token");
        LoginSession currentUser = loginSessionService.queryValidLoginSessionByToken(token);
        return reportService.createReport(currentUser.getUser().getId(),experimentId);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseModel removeReport(HttpServletRequest request,@PathVariable Integer id){
        String token = request.getHeader("token");
        LoginSession currentUser = loginSessionService.queryValidLoginSessionByToken(token);
        return reportService.removeReport(currentUser.getUser().getId(),id);
    }

    @PutMapping("/upload/{id}")
    @ResponseBody
    public ResponseModel changeReportFile(HttpServletRequest request,
                                      @RequestParam("file")MultipartFile file,
                                      @PathVariable Integer id){
        String token = request.getHeader("token");
        LoginSession currentUser = loginSessionService.queryValidLoginSessionByToken(token);
        return reportService.changeReportFile(currentUser.getUser().getId(),file,id);
    }

    @PutMapping("/mark/{id}")
    @ResponseBody
    public ResponseModel markReport(HttpServletRequest request,
                                      @PathVariable Integer id,
                                      @RequestParam("mark") Integer mark){
        String token = request.getHeader("token");
        LoginSession currentUser = loginSessionService.queryValidLoginSessionByToken(token);
        return reportService.markReport(currentUser.getUser().getId(),id,mark);
    }
}
