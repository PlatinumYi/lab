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
    LoginSession queryLoginSessionBySchoolNumber( @Param("userId") Integer userId);

//    @Select("SELECT COUNT(*) FROM session WHERE TOKEN=#{token}")
//    Integer countLoginSessionByKey( @Param("token") String token );

    @Select("SELECT * FROM session WHERE TOKEN=#{token}")
    LoginSession queryLoginSessionByKey( @Param("token") String token );

    @Update("UPDATE session SET TOKEN=#{token},LOGIN_TIME=#{loginTime} WHERE USER_ID=#{userId}")
    Integer updateSessionKey(@Param("token") String token,
                             @Param("userId") Integer userId,
                             @Param("loginTime") Date loginTime);

    @Delete("DELETE FROM session WHERE TOKEN=#{token}")
    Integer removeSessionKey(@Param("token") String token);

}
