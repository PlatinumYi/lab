package com.xuenan.lab.entity;

/**
 * CREATE TABLE `NewTable` (
 * `ID`  int(11) NOT NULL AUTO_INCREMENT ,
 * `NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '人员名称' ,
 * `PHOTO_SRC`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径' ,
 * `INTRODUCTION`  varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '人物介绍' ,
 * `VALID`  int(1) NOT NULL DEFAULT 1 COMMENT '是否有效' ,
 * PRIMARY KEY (`ID`)
 * )
 */
public class HumanityInformation {

    private Integer id ;
    private String name ;
    private String photoSrc ;
    private String introduction ;
    private Integer valid ;


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

    public String getPhotoSrc() {
        return photoSrc;
    }

    public void setPhotoSrc(String photoSrc) {
        this.photoSrc = photoSrc;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}
