package com.xuenan.lab.entity;

import java.util.Date;

/**
 * CREATE TABLE `NewTable` (
 * `ID`  int(11) NOT NULL AUTO_INCREMENT ,
 * `USER_ID`  int(255) NOT NULL ,
 * `TOKEN`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
 * `LOGIN_TIME`  datetime NOT NULL ,
 * PRIMARY KEY (`ID`)
 * )
 */

public class LoginSession {

    private Integer id ;
    private User user ;
    private String token ;
    private Date loginTime ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
