package com.xuenan.lab.user_management.dao;

import com.xuenan.lab.entity.User;
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

    @Select("SELECT * FROM user WHERE SCHOOL_NUMBER = #{schoolNumber} AND password=#{password}")
    User queryUserBySchoolNumberAndPassword(@Param("schoolNumber") String schoolNumber , @Param("password") String password ); ;
}
