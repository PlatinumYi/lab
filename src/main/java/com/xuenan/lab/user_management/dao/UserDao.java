package com.xuenan.lab.user_management.dao;

import com.xuenan.lab.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserDao {

    @Insert("INSERT INTO user(SCHOOL_NUMBER,PASSWORD,NAME,CREATED_AT,GRADE) VALUES(#{schoolNumber},#{password},#{name},#{createdAt},#{grade})")
    Integer register(@Param("schoolNumber") String schoolNumber,
                     @Param("password") String password,
                     @Param("name") String name,
                     @Param("createdAt") Date createdAt,
                     @Param("grade") String grade) ;



    @Select("SELECT COUNT(*) FROM user WHERE SCHOOL_NUMBER = #{schoolNumber}")
    Integer checkSchoolNumberExist( @Param("schoolNumber") String schoolNUmber) ;

    @Select("SELECT * FROM user WHERE SCHOOL_NUMBER = #{schoolNumber} AND password=#{password}")
    User queryUserBySchoolNumberAndPassword(@Param("schoolNumber") String schoolNumber , @Param("password") String password );

    @Select("SELECT ID,SCHOOL_NUMBER,NAME,CREATED_AT,TYPE,VALID,GRADE FROM user WHERE ID=#{id}")
    User queryUserById(@Param("id") Integer id);

    @Select("SELECT ID,SCHOOL_NUMBER,NAME,CREATED_AT,TYPE,VALID,GRADE FROM user WHERE valid = 1")
    List<User> queryValidUsers();

    @Select("SELECT ID,SCHOOL_NUMBER,NAME,CREATED_AT,TYPE,VALID,GRADE FROM user WHERE valid = 0")
    List<User> queryInvalidUsers();

    @Delete("DELETE FROM user WHERE ID=#{id} AND valid=0")
    Integer removeUserById(@Param("id") Integer id );

    @Select("SELECT ID,SCHOOL_NUMBER,NAME,CREATED_AT,TYPE,VALID,GRADE FROM user WHERE SCHOOL_NUMBER=#{key} OR NAME LIKE CONCAT('%',#{key},'%')")
    List<User> queryUserBySchoolNumberOrName(@Param("key") String key);

    @Update("UPDATE user SET valid = 0 WHERE ID=#{id}")
    Integer banUser(@Param("id") Integer id);

    @Update("UPDATE user SET valid = 1 WHERE ID=#{id}")
    Integer enableUser( @Param("id") Integer id );

    @Update("UPDATE user SET PASSWORD=#{newPass} WHERE ID=#{id} AND PASSWORD=#{oldPass}")
    Integer changePassword( @Param("id") Integer id ,@Param("newPass") String newPass ,@Param("oldPass") String oldPass);

    @Update("UPDATE user SET PASSWORD=123456 WHERE ID=#{id}")
    Integer resetPassword( @Param("id") Integer id );

    @Update("UPDATE user SET TYPE=#{type} WHERE ID=#{id}")
    Integer setUserType(@Param("type") Integer type , @Param("id") Integer id );
}
