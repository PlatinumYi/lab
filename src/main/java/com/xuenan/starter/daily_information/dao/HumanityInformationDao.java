package com.xuenan.starter.daily_information.dao;

import com.xuenan.starter.entity.HumanityInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HumanityInformationDao {

    @Select("SELECT * FROM humanity_information WHERE valid=1")
    List<HumanityInformation> queryHumanityInformation() ;
}
