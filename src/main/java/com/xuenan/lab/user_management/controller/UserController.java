package com.xuenan.lab.user_management.controller;


import com.xuenan.lab.user_management.model.ResponseModel;
import com.xuenan.lab.user_management.service.UserService;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @RequestMapping("/register")
    @ResponseBody
    public ResponseModel register( @RequestParam(value = "school_number") String schoolNumber,
                                   @RequestParam(value = "name") String name ,
                                   @RequestParam(value = "password") String password){

        return  userService.register(schoolNumber, name, password) ;
    }

    @RequestMapping("/login")
    @ResponseBody
    public ResponseModel login( @RequestParam(value = "school_number") String schoolNumber,
                                @RequestParam(value = "password") String password){

        return  userService.login(schoolNumber,password);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ResponseModel logout(HttpServletRequest request){

        String token = request.getHeader("token") ;
        return  userService.logout(token);
    }

    @GetMapping("/all/valid")
    @ResponseBody
    public ResponseModel queryValid(){
        return userService.queryallValid();
    }

    @GetMapping("/all/invalid")
    @ResponseBody
    public ResponseModel queryInvalid(){
        return userService.queryallInvalid();
    }

    @GetMapping("/all/selected")
    @ResponseBody
    public ResponseModel querySelected( @RequestParam("key") String key ){
        return userService.queryUserByNameOrSchoolNumber(key);
    }

    @PutMapping("/ban")
    @ResponseBody
    public ResponseModel banUser(@RequestParam("id") Integer id ){
        return userService.banUser(id);
    }

    @PutMapping("/enable")
    @ResponseBody
    public ResponseModel enableUser(@RequestParam("id") Integer id ){
        return userService.enableUser(id);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseModel deleteUser(@RequestParam("id") Integer id ){
        return userService.deleteUser(id);
    }

    @PutMapping("/type")
    @ResponseBody
    public ResponseModel setUserType(@RequestParam("id") Integer id ,@RequestParam("type") Integer type ){
        return userService.setUserType(id,type);
    }
}
