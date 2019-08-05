package com.xuenan.lab.user_management.controller;


import com.xuenan.lab.user_management.model.ResponseModel;
import com.xuenan.lab.user_management.service.UserService;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @RequestMapping("/register")
    public ResponseModel register( @RequestParam(value = "school_number") String schoolNumber,
                                   @RequestParam(value = "name") String name ,
                                   @RequestParam(value = "password") String password){

        return  userService.register(schoolNumber, name, password) ;
    }

    @RequestMapping("/login")
    public ResponseModel login( @RequestParam(value = "school_number") String schoolNumber,
                                @RequestParam(value = "password") String password){

        return  userService.login(schoolNumber,password);
    }

    @RequestMapping("/logout")
    public ResponseModel logout(HttpServletRequest request){

        String token = request.getHeader("token") ;
        return  userService.logout(token);
    }
}
