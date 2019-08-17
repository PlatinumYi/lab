package com.xuenan.lab.user_management.dao;

import com.xuenan.lab.entity.LoginSession;
import org.apache.ibatis.annotations.*;

import java.util.Date;

@Mapper
public interface LoginSessionDao {


    @Insert("INSERT INTO session(TOKEN,USER_ID,LOGIN_TIME) VALUES(#{token},#{userId},#{loginTime})")
    Integer createLoginSession(@Param("token") String token,
                          @Param("userId") Integer userId,
                          @Param("loginTime") Date loginTime);

    @Select("SELECT * FROM session WHERE USER_ID=#{userId}")
    @Results( id = "sessionMap" , value = {
        @Result( column = "ID" , property = "id" ,id = true),
        @Result( column = "USER_ID" , property = "user" , one = @One(select = "com.xuenan.lab.user_management.dao.UserDao.queryUserById")),
        @Result( column = "TOKEN" , property = "token" ),
        @Result( column = "LOGIN_TIME" , property = "loginTime")
    })
    LoginSession queryLoginSessionBySchoolNumber( @Param("userId") Integer userId);


    @Select("SELECT * FROM session WHERE TOKEN=#{token}")
    @ResultMap( value = "sessionMap" )
    LoginSession queryLoginSessionByKey( @Param("token") String token );

    @Update("UPDATE session SET TOKEN=#{token},LOGIN_TIME=#{loginTime} WHERE USER_ID=#{userId}")
    Integer updateSessionKey(@Param("token") String token,
                             @Param("userId") Integer userId,
                             @Param("loginTime") Date loginTime);

    @Delete("DELETE FROM session WHERE TOKEN=#{token}")
    Integer removeSessionKey(@Param("token") String token);

}
