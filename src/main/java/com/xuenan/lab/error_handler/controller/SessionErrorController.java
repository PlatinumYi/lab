package com.xuenan.lab.error_handler.controller;

import com.xuenan.lab.user_management.model.ResponseModel;
import com.xuenan.lab.error_handler.service.SessionErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/error/token")
public class SessionErrorController {

    @Autowired
    private SessionErrorService sessionErrorService ;

    @RequestMapping("/invalid")
    @ResponseBody
    public ResponseModel invalidToken(){
        return sessionErrorService.reportInvalidError();
    }

    @RequestMapping("/null")
    @ResponseBody
    public ResponseModel nullToken(){
        return sessionErrorService.reportNullError();
    }

    @RequestMapping("/not_manager")
    @ResponseBody
    public ResponseModel notManagerToken(){
        return sessionErrorService.reportNotManagerError();
    }

}
