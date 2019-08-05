package com.xuenan.lab.user_management.controller;

import com.xuenan.lab.user_management.model.ResponseModel;
import com.xuenan.lab.user_management.service.SessionErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/session")
public class SessionErrorController {

    @Autowired
    private SessionErrorService sessionErrorService ;

    @RequestMapping("/invalid")
    @ResponseBody
    public ResponseModel invalidSessionKey(){
        return sessionErrorService.reportError() ;
    }
}
