package com.xuenan.lab.user_management.dao;

import com.xuenan.lab.entity.LoginSession;
import org.apache.ibatis.annotations.*;

import java.util.Date;

@Mapper
public interface LoginSessionDao {


    @Insert("INSERT INTO session(TOKEN,SCHOOL_NUMBER,LOGIN_TIME) VALUES(#{token},#{schoolNumber},#{loginTime})")
    Integer createLoginSession(@Param("token") String token,
                          @Param("schoolNumber") String schoolNumber,
                          @Param("loginTime") Date loginTime);

    @Select("SELECT * FROM session WHERE SCHOOL_NUMBER=#{schoolNumber}")
    LoginSession queryLoginSessionBySchoolNumber( @Param("schoolNumber") String schoolNumber );

    @Select("SELECT COUNT(*) FROM session WHERE TOKEN=#{token}")
    Integer countLoginSessionByKey( @Param("token") String token );

    @Update("UPDATE session SET TOKEN=#{token} AND LOGIN_TIME=#{loginTime} WHERE SCHOOL_NUMBER=#{schoolNumber}")
    Integer updateSessionKey(@Param("token") String token,
                             @Param("schoolNumber") String schoolNumber,
                             @Param("loginTime") Date loginTime);

    @Delete("DELETE FROM session WHERE TOKEN=#{token}")
    Integer removeSessionKey(@Param("token") String token);

}
