package com.xuenan.lab.entity;

import java.util.Date;

/**
 * CREATE TABLE `NewTable` (
 * `ID`  int(11) NOT NULL AUTO_INCREMENT ,
 * `SCHOOL_NUMBER`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
 * `SESSION_KEY`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
 * `LOGIN_TIME`  datetime NOT NULL ,
 * PRIMARY KEY (`ID`)
 * )
 */

public class LoginSession {

    private Integer id ;
    private String schoolNumber ;
    private String sessionKey ;
    private Date loginTime ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolNumber() {
        return schoolNumber;
    }

    public void setSchoolNumber(String schoolNumber) {
        this.schoolNumber = schoolNumber;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
}
