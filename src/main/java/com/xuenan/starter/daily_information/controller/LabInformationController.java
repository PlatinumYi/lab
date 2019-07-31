package com.xuenan.starter.daily_information.controller;

import com.xuenan.starter.daily_information.model.ResponseModel;
import com.xuenan.starter.daily_information.service.LabInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/information/lab")
public class LabInformationController {

    @Autowired
    private LabInformationService labInformationService ;

    @RequestMapping("/")
    @ResponseBody
    public ResponseModel query(){
        return labInformationService.queryLabInformation() ;
    }

}
