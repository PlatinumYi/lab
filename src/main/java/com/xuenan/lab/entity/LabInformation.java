package com.xuenan.lab.entity;


/**
 * CREATE TABLE `NewTable` (
 * `ID`  int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID' ,
 * `NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '实验室名称' ,
 * `INTRODUCTION`  varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '实验室介绍' ,
 * `VALID`  int(11) NOT NULL DEFAULT 0 COMMENT '当前是否使用' ,
 * PRIMARY KEY (`ID`)
 * )
 */
public class LabInformation {

    private Integer id ;
    private String name ;
    private String introduction ;
    private String fileSrc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) { this.introduction = introduction; }

    public String getFileSrc() {
        return fileSrc;
    }

    public void setFileSrc(String fileSrc) {
        this.fileSrc = fileSrc;
    }
}
