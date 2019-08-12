package com.xuenan.lab.entity;

/**
 * CREATE TABLE `NewTable` (
 * `ID`  int(11) NOT NULL AUTO_INCREMENT ,
 * `EXPERIMENT_ID`  int(11) NOT NULL COMMENT '实验ID' ,
 * `STUNDENT_ID`  int(11) NOT NULL COMMENT '学生ID' ,
 * `IS_MARKED`  int(11) NOT NULL DEFAULT 0 COMMENT '是否被评分' ,
 * `MARK`  int(11) NULL DEFAULT NULL COMMENT '分数' ,
 * `REPORT_FILE_SRC`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '报告文件路径' ,
 * PRIMARY KEY (`ID`)
 * )
 */
public class Report {

    private Integer id ;
    private Experiment experiment ;
    private User student ;
    private Integer isMarked ;
    private Integer mark ;
    private String reportFileSrc ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Experiment getExperiment() {
        return experiment;
    }

    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    public Integer getIsMarked() {
        return isMarked;
    }

    public void setIsMarked(Integer isMarked) {
        this.isMarked = isMarked;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }

    public String getReportFileSrc() {
        return reportFileSrc;
    }

    public void setReportFileSrc(String reportFileSrc) {
        this.reportFileSrc = reportFileSrc;
    }
}
