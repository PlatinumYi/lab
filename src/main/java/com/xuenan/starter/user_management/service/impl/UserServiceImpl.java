package com.xuenan.starter.user_management.service.impl;

import com.xuenan.starter.method.MD5Tools;
import com.xuenan.starter.user_management.dao.UserDao;
import com.xuenan.starter.user_management.model.ResponseModel;
import com.xuenan.starter.user_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDao userDao ;

    @Override
    public ResponseModel register(String schoolNumber, String name, String password) {

        ResponseModel responseModel ;
        // 获取当前北京时间
        Date LondonToday = new Date() ;
        Long currentMillsToday = LondonToday.getTime() + (long)(3600*1000*8) ;
        Date BeijingToday = new Date(currentMillsToday);

        //获取MD5加密后的密码
        password = MD5Tools.MD5(password);

        if (userDao.checkSchoolNumberExist(schoolNumber)>0){
            responseModel = new ResponseModel(201,"该学号已被注册") ;
        }else {
            Integer result = userDao.register(schoolNumber,password,name,BeijingToday) ;
            if( result == 1 ){
                responseModel = new ResponseModel() ;
            }else {
                responseModel = new ResponseModel(202,"远程服务器没有响应请求") ;
            }
        }
        return responseModel ;
    }
}
