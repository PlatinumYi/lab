package com.xuenan.starter.entity;

/**
 * CREATE TABLE `NewTable` (
 * `ID`  int(11) NOT NULL AUTO_INCREMENT ,
 * `NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备名称' ,
 * `DANGEROUS`  int(1) NOT NULL DEFAULT 0 COMMENT '是否为危险设备' ,
 * `INTRODUCTION`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备介绍' ,
 * `VALID`  int(1) NOT NULL DEFAULT 1 COMMENT '是否有效' ,
 * `PHOTO_SRC`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图片路径' ,
 * PRIMARY KEY (`ID`)
 * )
 */
public class EquipmentInformation {

    private Integer id ;
    private String name ;
    private Integer dangerous ;
    private String introduction ;
    private Integer valid ;
    private String photoSrc ;

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

    public Integer getDangerous() {
        return dangerous;
    }

    public void setDangerous(Integer dangerous) {
        this.dangerous = dangerous;
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

    public String getPhotoSrc() {
        return photoSrc;
    }

    public void setPhotoSrc(String photoSrc) {
        this.photoSrc = photoSrc;
    }
}
