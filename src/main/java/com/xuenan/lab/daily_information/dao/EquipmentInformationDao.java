package com.xuenan.lab.daily_information.dao;

import com.xuenan.lab.entity.EquipmentInformation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EquipmentInformationDao {

    @Select("SELECT * FROM equipment_information WHERE valid=1")
    List<EquipmentInformation> queryEquipmentInformation();

}
