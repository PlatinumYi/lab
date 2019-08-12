package com.xuenan.lab.entity;

import java.util.Date;

/**
 * CREATE TABLE `NewTable` (
 * `ID`  int(11) NOT NULL AUTO_INCREMENT ,
 * `TITLE`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告标题' ,
 * `CONTENT`  varchar(4000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '公告内容' ,
 * `CREATED_AT`  datetime NOT NULL COMMENT '创建时间' ,
 * `UPDATED_AT`  datetime NOT NULL COMMENT '修改时间' ,
 * `AUTHOR_NAME`  int(11) NOT NULL COMMENT '作者姓名' ,
 * `VALID`  int(1) NOT NULL DEFAULT 1 COMMENT '是否显示' ,
 * PRIMARY KEY (`ID`)
 * )
 */
public class Notice {

    private Integer id ;
    private String title ;
    private  String content ;
    private Date createdAt ;
    private Date updatedAt ;
    private String authorName ;
    private Integer valid ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}
