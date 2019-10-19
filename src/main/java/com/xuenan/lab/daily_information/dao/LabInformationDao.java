package com.xuenan.lab.daily_information.dao;

import com.xuenan.lab.entity.LabInformation;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface LabInformationDao {

    @Select("SELECT * FROM lab_information LIMIT 1")
    LabInformation queryLabInformation() ;

    @Select("SELECT COUNT(*) FROM lab_information")
    Integer countInformation();

    @Insert("INSERT INTO lab_information(NAME,INTRODUCTION) VALUES (#{name},#{introduction})")
    Integer createInformation(@Param("name") String name ,@Param("introduction") String introduction );

    @Update("UPDATE lab_information SET NAME=#{name},INTRODUCTION=#{introduction} WHERE ID=#{id}")
    Integer updateInformation(@Param("name") String name ,@Param("introduction") String introduction,@Param("id") Integer id);

    @Update("UPDATE lab_information SET FILE_SRC=#{fileSrc} WHERE ID=#{id}")
    Integer updateFile(@Param("fileSrc") String fileSrc ,@Param("id") Integer id);

}
