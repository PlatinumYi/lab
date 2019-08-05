package com.xuenan.lab.daily_information.dao;

import com.xuenan.lab.entity.HumanityInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HumanityInformationDao {

    @Select("SELECT * FROM humanity_information WHERE valid=1")
    List<HumanityInformation> queryHumanityInformation() ;
}
