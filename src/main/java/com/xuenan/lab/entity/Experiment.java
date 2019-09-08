package com.xuenan.lab.entity;

import java.util.Date;

/**
 * CREATE TABLE `NewTable` (
 * `ID`  int(11) NOT NULL AUTO_INCREMENT ,
 * `NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '实验名称' ,
 * `INSTRUCTION`  varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '简介' ,
 * `STARTER_ID`  int(11) NOT NULL COMMENT '发起人ID' ,
 * `TEACHER_NAME`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参与授课教师姓名（全部）' ,
 * `ACCESSIBLE_UNTIL`  datetime NOT NULL COMMENT '报名截止时间' ,
 * `REPORT_UNTIL`  datetime NOT NULL COMMENT '报告截止时间' ,
 * `MAX_STUDENT_NUMBER`  int(11) NOT NULL COMMENT '最大选课人数' ,
 * `CURRENT_STUDENT_NUMBER`  int(11) NOT NULL DEFAULT 0 COMMENT '当前选课人数' ,
 * `START_SIGN_IN`  int(1) NOT NULL DEFAULT 0 ,
 * `LONGITUDE`  double NULL DEFAULT NULL ,
 * `LATITUDE`  double NULL DEFAULT NULL ,
 * `GUIDE_BOOK`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
 * PRIMARY KEY (`ID`)
 * )
 */
public class Experiment {

    private Integer id ;
    private String name;
    private String instruction ;
    private Integer starterId ;
    private String teacherName ;
    private Date accessibleUntil;
    private Date reportUntil ;
    private Integer maxStudentNumber ;
    private Integer currentStudentNumber ;
    private Integer startSignIn ;
    private Double longitude;
    private Double latitude ;
    private String guideBook ;
    private Integer beginTime ;
    private Integer stopTime ;

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

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public Integer getStarterId() {
        return starterId;
    }

    public void setStarterId(Integer starterId) {
        this.starterId = starterId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Date getAccessibleUntil() {
        return accessibleUntil;
    }

    public void setAccessibleUntil(Date accessibleUntil) {
        this.accessibleUntil = accessibleUntil;
    }

    public Date getReportUntil() {
        return reportUntil;
    }

    public void setReportUntil(Date reportUntil) {
        this.reportUntil = reportUntil;
    }

    public Integer getMaxStudentNumber() {
        return maxStudentNumber;
    }

    public void setMaxStudentNumber(Integer maxStudentNumber) {
        this.maxStudentNumber = maxStudentNumber;
    }

    public Integer getCurrentStudentNumber() {
        return currentStudentNumber;
    }

    public void setCurrentStudentNumber(Integer currentStudentNumber) {
        this.currentStudentNumber = currentStudentNumber;
    }

    public Integer getStartSignIn() {
        return startSignIn;
    }

    public void setStartSignIn(Integer startSignIn) {
        this.startSignIn = startSignIn;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getGuideBook() {
        return guideBook;
    }

    public void setGuideBook(String guideBook) {
        this.guideBook = guideBook;
    }

    public Integer getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getStopTime() {
        return stopTime;
    }

    public void setStopTime(Integer stopTime) {
        this.stopTime = stopTime;
    }
}
