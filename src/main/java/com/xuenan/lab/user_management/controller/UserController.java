package com.xuenan.lab.user_management.controller;


import com.xuenan.lab.user_management.model.ResponseModel;
import com.xuenan.lab.user_management.service.UserService;
import org.apache.http.HttpHeaders;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService ;

    @PostMapping("/register")
    @ResponseBody
    public ResponseModel register( @RequestParam(value = "student_number") String schoolNumber,
                                   @RequestParam(value = "name") String name ,
                                   @RequestParam(value = "password") String password,
                                   @RequestParam(value = "grade") String grade){

        return  userService.register(schoolNumber, name, password , grade ) ;
    }

    @PostMapping("/multi/register")
    @ResponseBody
    public ResponseModel multiRegister(@RequestParam(value = "file") MultipartFile file){

        return  userService.multiRegister(file);
    }


    @PutMapping("/password")
    @ResponseBody
    public ResponseModel changePassword(HttpServletRequest request,
                                        @RequestParam("old_password") String oldPassword,
                                        @RequestParam("new_password") String newPassword){

        String token = request.getHeader("token");
        return userService.changePassword(token,oldPassword,newPassword);
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseModel login( @RequestParam(value = "student_number") String schoolNumber,
                                @RequestParam(value = "password") String password){

        return  userService.login(schoolNumber,password);
    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponseModel logout(HttpServletRequest request){

        String token = request.getHeader("token") ;
        return  userService.logout(token);
    }

    @GetMapping("/current")
    @ResponseBody
    public ResponseModel currentUser(HttpServletRequest request){

        String token = request.getHeader("token") ;
        return  userService.currentUser(token);
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

    @PutMapping("/ban/{id}")
    @ResponseBody
    public ResponseModel banUser(@PathVariable Integer id ){
        return userService.banUser(id);
    }

    @PutMapping("/enable/{id}")
    @ResponseBody
    public ResponseModel enableUser(@PathVariable Integer id ){
        return userService.enableUser(id);
    }

    @PutMapping("/reset/{id}")
    @ResponseBody
    public ResponseModel resetUserPassword(@PathVariable Integer id ){
        return userService.resetPassword(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseModel deleteUser(@PathVariable Integer id ){
        return userService.deleteUser(id);
    }

    @PutMapping("/type/{id}")
    @ResponseBody
    public ResponseModel setUserType(@PathVariable Integer id ,@RequestParam("type") Integer type ){
        return userService.setUserType(id,type);
    }
}
