package com.xuenan.lab.experiment_management.dao;

import com.xuenan.lab.entity.Experiment;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface ExperimentDao {


    @Insert("INSERT INTO experiment(NAME,INSTRUCTION,STARTER_ID,TEACHER_NAME,ACCESSIBLE_UNTIL,REPORT_UNTIL,MAX_STUDENT_NUMBER,BEGIN_TIME,STOP_TIME,ROOM_ID)" +
            "VALUES(#{name},#{instruction},#{starterId},#{teacherName},#{accessibleUntil},#{reportUntil},#{maxStudentNumber},#{beginTime},#{stopTime},#{roomId})")
    Integer createExperiment(@Param("name") String name,
                             @Param("instruction") String instruction,
                             @Param("starterId") Integer starterId ,
                             @Param("teacherName") String teacherName ,
                             @Param("accessibleUntil") Date accessibleUntil,
                             @Param("reportUntil") Date reportUntil,
                             @Param("maxStudentNumber") Integer maxStudentNumber,
                             @Param("beginTime") Integer beginTime,
                             @Param("stopTime") Integer stopTime,
                             @Param("roomId") Integer roomId);

    @Update("UPDATE experiment SET NAME=#{name},INSTRUCTION=#{instruction},TEACHER_NAME=#{teacherName},BEGIN_TIME={beginTime},STOP_TIME=#{stopTime}" +
            "ACCESSIBLE_UNTIL=#{accessibleUntil},REPORT_UNTIL=#{reportUntil},MAX_STUDENT_NUMBER=#{maxStudentNumber},ROOM_ID=#{roomId} WHERE ID=#{id}")
    Integer changeExperiment(@Param("id") Integer id,
                             @Param("name") String name,
                             @Param("instruction") String instruction,
                             @Param("teacherName") String teacherName ,
                             @Param("accessibleUntil") Date accessibleUntil,
                             @Param("reportUntil") Date reportUntil,
                             @Param("maxStudentNumber") Integer maxStudentNumber,
                             @Param("beginTime") Integer beginTime,
                             @Param("stopTime") Integer stopTime,
                             @Param("roomId") Integer roomId);

    @Update("UPDATE experiment SET GUIDE_BOOK=#{guideBook} WHERE ID=#{id}")
    Integer changeExperimentBook (@Param("id") Integer id,
                                  @Param("guideBook") String guideBook);

    @Select("SELECT * FROM experiment WHERE STARTER_ID=#{id}")
    @Results( id="experimentMap" , value = {
            @Result( column = "ID",property = "id",id = true),
            @Result( column = "NAME",property = "name"),
            @Result( column = "STARTER_ID",property = "starterId"),
            @Result( column = "TEACHER_NAME",property = "teacherName"),
            @Result( column = "ACCESSIBLE_UNTIL",property = "accessibleUntil"),
            @Result( column = "REPORT_UNTIL",property = "reportUntil"),
            @Result( column = "MAX_STUDENT_NUMBER",property = "maxStudentNumber"),
            @Result( column = "CURRENT_STUDENT_NUMBER",property = "currentStudentNumber"),
            @Result( column = "START_SIGN_IN",property = "startSignIn"),
            @Result( column = "LONGITUDE",property = "longitude"),
            @Result( column = "LATITUDE",property = "latitude"),
            @Result( column = "GUIDE_BOOK",property = "guideBook"),
            @Result( column = "BEGIN_TIME",property = "beginTime"),
            @Result( column = "STOP_TIME",property = "stopTime"),
            @Result( column = "ROOM_ID",property = "room",one = @One(select = "com.xuenan.lab.daily_information.dao.RoomDao.queryRoomById")),

    })
    List<Experiment> queryExperimentByStarterId(@Param("id") Integer id);

    @Select("SELECT * FROM experiment")
    @ResultMap(value = "experimentMap")
    List<Experiment> queryExperiment();

    @Select("SELECT * FROM experiment WHERE ACCESSIBLE_UNTIL>=#{date}")
    @ResultMap(value = "experimentMap")
    List<Experiment> queryAccessibleExperiment(@Param("date") Date date);

    @Select("SELECT * FROM experiment WHERE ID=#{id}")
    @ResultMap(value = "experimentMap")
    Experiment queryExperimentById(@Param("id") Integer id);

    @Delete("DELETE FROM experiment WHERE ID=#{id}")
    Integer deleteExperimentById(@Param("id") Integer id);

    @Update("UPDATE experiment SET CURRENT_STUDENT_NUMBER=#{currentStudentNumber} WHERE ID=#{id}")
    Integer setCurrentStudentNumber(@Param("currentStudentNumber") Integer number,@Param("id") Integer id);

    @Update("UPDATE experiment SET LONGITUDE=#{longitude},LATITUDE=#{latitude},START_SIGN_IN=1 WHERE ID=#{id}")
    Integer startSignIn(@Param("id") Integer id , @Param("longitude") Double longitude , @Param("latitude") Double latitude );
}
