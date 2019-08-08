package com.xuenan.lab.user_management.service;

import com.xuenan.lab.user_management.model.ResponseModel;

public interface UserService {

    ResponseModel register(String schoolNumber , String name ,String password) ;
    ResponseModel login(String schoolNumber , String password );
    ResponseModel logout(String sessionKey);
    ResponseModel queryallValid();
    ResponseModel queryallInvalid();
    ResponseModel banUser( Integer id );
    ResponseModel enableUser( Integer id );
    ResponseModel setUserType( Integer id , Integer type) ;
    ResponseModel deleteUser( Integer id );
    ResponseModel queryUserByNameOrSchoolNumber( String key) ;
}
