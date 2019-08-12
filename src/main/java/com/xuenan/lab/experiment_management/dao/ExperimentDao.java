package com.xuenan.lab.experiment_management.dao;

import com.xuenan.lab.entity.Experiment;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface ExperimentDao {


    @Insert("INSERT INTO experiment(NAME,INSTRUCTION,STARTER_ID,TEACHER_NAME,ACCESSIBLE_UNTIL,REPORT_UNTIL,MAX_STUDENT_NUMBER)" +
            "VALUES(#{name},#{instruction},#{starterId},#{teacherName},#{accessibleUntil},#{reportUntil},#{maxStudentNumber})")
    Integer createExperiment(@Param("name") String name,
                             @Param("instruction") String instruction,
                             @Param("starterId") Integer starterId ,
                             @Param("teacherName") String teacherName ,
                             @Param("accessibleUntil") Date accessibleUntil,
                             @Param("reportUntil") Date reportUntil,
                             @Param("maxStudentNumber") Integer maxStudentNumber );

    @Update("UPDATE experiment SET NAME=#{name},INSTRUCTION=#{instruction},TEACHER_NAME=#{teacherName}," +
            "ACCESSIBLE_UNTIL=#{accessibleUntil},REPORT_UNTIL=#{reportUntil},MAX_STUDENT_NUMBER=#{maxStudentNumber} WHERE ID=#{id}")
    Integer changeExperiment(@Param("id") Integer id,
                             @Param("name") String name,
                             @Param("instruction") String instruction,
                             @Param("teacherName") String teacherName ,
                             @Param("accessibleUntil") Date accessibleUntil,
                             @Param("reportUntil") Date reportUntil,
                             @Param("maxStudentNumber") Integer maxStudentNumber );

    @Select("SELECT * FROM experiment WHERE STARTER_ID=#{id}")
    List<Experiment> queryExperimentByStarterId(@Param("id") Integer id);

    @Select("SELECT * FROM experiment WHERE ACCESSIBLE_UNTIL>=#{date}")
    List<Experiment> queryAccessibleExperiment(@Param("date") Date date);

    @Select("SELECT * FROM experiment WHERE ID=#{id}")
    Experiment queryExperimentById(@Param("id") Integer id);

    @Update("UPDATE experiment SET CURRENT_STUDENT_NUMBER=#{currentStudentNumber} WHERE ID=#{id}")
    Integer setCurrentStudentNumber(@Param("currentStudentNumber") Integer number,@Param("id") Integer id);

}
