package com.xuenan.lab.daily_information.dao;

import com.xuenan.lab.entity.HumanityInformation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface HumanityInformationDao {

    @Select("SELECT * FROM humanity_information ")
    List<HumanityInformation> queryHumanityInformation() ;

    @Select("SELECT * FROM humanity_information WHERE ID=#{id}")
    HumanityInformation queryHumanityInformationById(@Param("id") Integer id) ;

    @Insert("INSERT INTO humanity_information(NAME,INTRODUCTION,PHOTO_SRC) VALUES(#{name}，#{introduction}，#{photoSrc})")
    Integer createHumanityInformation(@Param("name") String name,
                                      @Param("introduction") String introduction,
                                      @Param("photoSrc") String photoSrc );

    @Delete("DELETE FROM humanity_information WHERE ID=#{id}")
    Integer removeHumanityInformation(@Param("id") Integer id );
}
