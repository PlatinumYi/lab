package com.xuenan.starter.user_management.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

@Mapper
public interface UserDao {

    @Insert("INSERT INTO user(SCHOOL_NUMBER,PASSWORD,NAME,CREATED_AT) VALUES(#{schoolNumber},#{password},#{name},#{createdAt})")
    Integer register(@Param("schoolNumber") String schoolNumber,
                     @Param("password") String password,
                     @Param("name") String name,
                     @Param("createdAt") Date createdAt );


    @Select("SELECT COUNT(*) FROM user WHERE SCHOOL_NUMBER = #{schoolNumber}")
    Integer checkSchoolNumberExist( @Param("schoolNumber") String schoolNUmber) ;
}
