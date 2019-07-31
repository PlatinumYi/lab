package com.xuenan.starter.daily_information.dao;

import com.xuenan.starter.entity.LabInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface LabInformationDao {

    @Select("SELECT * FROM lab_information WHERE valid=1")
    List<LabInformation> queryLabInformation() ;

}
