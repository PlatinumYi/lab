package com.xuenan.lab.entity;

/**
 *CREATE TABLE `NewTable` (
 * `ID`  int(11) NOT NULL AUTO_INCREMENT ,
 * `NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
 * `SRC`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
 * PRIMARY KEY (`ID`)
 * )
 */
public class LabPhoto {

     private Integer id ;
     private String name ;
     private String src ;

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

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
