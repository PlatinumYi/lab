package com.xuenan.starter.user_management.service;

import com.xuenan.starter.user_management.model.ResponseModel;

public interface UserService {

    ResponseModel register(String schoolNumber , String name ,String password) ;
}
