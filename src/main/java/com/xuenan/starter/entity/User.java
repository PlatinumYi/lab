package com.xuenan.starter.entity;


import java.util.Date;

/**
 * CREATE TABLE `NewTable` (
 * `ID`  int(11) NOT NULL AUTO_INCREMENT ,
 * `SCHOOL_NUMBER`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号或者工号' ,
 * `PASSWORD`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录密码（加密存储）' ,
 * `NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '真实姓名' ,
 * `TYPE`  int(11) NOT NULL DEFAULT 1 COMMENT '用户类型 默认为1学生 可以被赋权为 2老师 3管理人员' ,
 * `CREATED_AT`  datetime NOT NULL COMMENT '用户注册时间' ,
 * `VALID`  int(11) NOT NULL DEFAULT 1 COMMENT '该用户是否被禁用/取消资格' ,
 * PRIMARY KEY (`ID`)
 * )
 */
public class User {

    private Integer id ;
    private String schoolNumber ;
    private String password ;
    private String name ;
    private Integer type ;
    private Date createdAt ;
    private Integer valid ;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}
