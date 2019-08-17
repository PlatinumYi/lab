package com.xuenan.lab.experiment_management.dao;

import com.xuenan.lab.entity.Report;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface ReportDao {

    @Insert("INSERT INTO report(EXPERIMENT_ID,STUDENT_ID) VALUES(#{experimentId},#{studentId})")
    Integer createReport(@Param("experimentId") Integer experimentId,@Param("studentId") Integer studentId);

    @Update("UPDATE report SET REPORT_FILE_SRC=#{fileSrc} WHERE ID=#{id}")
    Integer changeFileSrc(@Param("id") Integer id,@Param("fileSrc") String fileSrc);

    @Update("UPDATE report SET MARK=#{mark},IS_MARKED=1 WHERE ID=#{id}")
    Integer markReport(@Param("id") Integer id,@Param("mark") Integer mark);

    @Update("UPDATE report SET IS_SIGNED=1 WHERE ID=#{id}")
    Integer signIn(@Param("id") Integer id);

    @Select("SELECT COUNT(*) FROM report WHERE STUDENT_ID=#{studentId} AND EXPERIMENT_ID=#{experimentId}")
    Integer checkSelectedStudent(@Param("studentId") Integer sid , @Param("experimentId") Integer eid);

    @Select("SELECT * FROM report WHERE STUDENT_ID=#{id}")
    @Results( id="reportMap" , value = {
        @Result( column = "ID",property = "id",id = true),
        @Result( column = "STUDENT_ID",property = "student",one = @One(select = "com.xuenan.lab.user_management.dao.UserDao.queryUserById")),
        @Result( column = "EXPERIMENT_ID",property = "experiment",one = @One(select = "com.xuenan.lab.experiment_management.dao.ExperimentDao.queryExperimentById")),
        @Result( column = "IS_SIGNED",property = "isSigned"),
        @Result( column = "IS_MARKED",property = "isMarked"),
        @Result( column = "MARK",property = "mark"),
        @Result( column = "REPORT_FILE_SRC",property = "reportFileSrc")
    })
    List<Report> queryReportByStudentId(@Param("id") Integer id);

    @Select("SELECT * FROM report WHERE EXPERIMENT_ID=#{id}")
    @ResultMap(value = "reportMap")
    List<Report> queryReportByExperimentId(@Param("id") Integer id);

    @Select("SELECT * FROM report WHERE ID=#{id}")
    @ResultMap(value = "reportMap")
    Report queryReportById(@Param("id") Integer id);

    @Select("SELECT COUNT(*) FROM report WHERE EXPERIMENT_ID=#{id}")
    Integer countReportByExperimentId(@Param("id") Integer id);

    @Delete("DELETE FROM report WHERE ID=#{id}")
    Integer removeReportById(@Param("id") Integer id);

    @Delete("DELETE FROM report WHERE EXPERIMENT_ID=#{id}")
    Integer removeReportByExperimentId(@Param("id") Integer id);
}
