package com.xuenan.lab.daily_information.dao;

import com.xuenan.lab.entity.LabPhoto;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

public interface LabPhotoDao {


    @Select("SELECT * FROM lab_photo")
    List<LabPhoto> queryAllPhotos();

    @Select("SELECT * FROM lab_photo WHERE ID=#{id}")
    LabPhoto queryPhotoById(@Param("id") Integer id);

    @Insert("INSERT INTO lab_photo(NAME,SRC) VALUES (#{name},#{src})")
    Integer createPhoto(String name,String src);

    @Delete("DELETE FROM lab_photo WHERE ID=#{id}")
    Integer deletePhotoById(@Param("id") Integer id );

    @Update("UPDATE lab_photo SET NAME=#{name} WHERE ID=#{id}")
    Integer changePhotoName(@Param("id") Integer id,@Param("name") String name);
}
