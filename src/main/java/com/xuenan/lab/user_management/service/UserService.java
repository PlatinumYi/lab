package com.xuenan.lab.user_management.service;

import com.xuenan.lab.user_management.model.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    ResponseModel register(String schoolNumber , String name ,String password,String grade) ;
    ResponseModel multiRegister(MultipartFile file);
    ResponseModel changePassword( String token , String oldPassword , String newPassword);
    ResponseModel login(String schoolNumber , String password );
    ResponseModel logout(String sessionKey);
    ResponseModel currentUser(String token);
    ResponseModel queryallValid();
    ResponseModel queryallInvalid();
    ResponseModel banUser( Integer id );
    ResponseModel resetPassword( Integer id );
    ResponseModel enableUser( Integer id );
    ResponseModel setUserType( Integer id , Integer type) ;
    ResponseModel deleteUser( Integer id );
    ResponseModel queryUserByNameOrSchoolNumber( String key) ;
}
