package com.xuenan.lab.daily_information.dao;

import com.xuenan.lab.entity.EquipmentInformation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EquipmentInformationDao {

    @Select("SELECT * FROM equipment_information WHERE valid=1")
    List<EquipmentInformation> queryValidEquipment();

    @Select("SELECT * FROM equipment_information WHERE valid=0")
    List<EquipmentInformation> queryInvalidEquipment();

    @Select("SELECT * FROM equipment_information WHERE ID=#{id}")
    EquipmentInformation queryEquipmentById(@Param("id") Integer id );

    @Update("UPDATE equipment_information SET valid=0 WHERE ID=#{id}")
    Integer banEquipment(@Param("id") Integer id);

    @Update("UPDATE equipment_information SET valid=1 WHERE ID=#{id}")
    Integer enableEquipment(@Param("id") Integer id);

    @Delete("DELETE FROM equipment_information WHERE valid=0 AND ID=#{id}")
    Integer removeEquipment(@Param("id") Integer id);

    @Insert("INSERT INTO equipment_information(NAME,INTRODUCTION,DANGEROUS,PHOTO_SRC) VALUES (#{name},#{introduction},#{dangerous},#{photoSrc})")
    Integer createEquipment(@Param("name") String name,
                           @Param("introduction") String introduction,
                           @Param("dangerous") Integer dangerous,
                           @Param("photoSrc") String photoSrc);
}
