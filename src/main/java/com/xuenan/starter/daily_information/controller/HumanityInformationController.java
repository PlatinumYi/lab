package com.xuenan.starter.daily_information.controller;

import com.xuenan.starter.daily_information.model.ResponseModel;
import com.xuenan.starter.daily_information.service.HumanityInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/information/humanity")
public class HumanityInformationController {

    @Autowired
    private HumanityInformationService humanityInformationService ;

    @RequestMapping("/")
    @ResponseBody
    public ResponseModel query(){
        return humanityInformationService.queryHumanityInformation() ;
    }
}
