package com.xuenan.lab.user_management.dao;

import com.xuenan.lab.entity.LoginSession;
import org.apache.ibatis.annotations.*;

import java.util.Date;

@Mapper
public interface LoginSessionDao {


    @Insert("INSERT INTO session(SESSION_KEY,SCHOOL_NUMBER,LOGIN_TIME) VALUES(#{sessionKey},#{schoolNumber},#{loginTime})")
    Integer createLoginSession(@Param("sessionKey") String sessionKey,
                          @Param("schoolNumber") String schoolNumber,
                          @Param("loginTime") Date loginTime);

    @Select("SELECT * FROM session WHERE SCHOOL_NUMBER=#{schoolNumber}")
    LoginSession queryLoginSessionBySchoolNumber( @Param("schoolNumber") String schoolNumber );

    @Select("SELECT * FROM session WHERE SESSION_KEY=#{sessionKey}")
    LoginSession queryLoginSessionByKey( @Param("sessionKey") String sessionKey );

    @Update("UPDATE session SET SESSION_KEY=#{sessionKey} AND LOGIN_TIME=#{loginTime} WHERE SCHOOL_NUMBER=#{schoolNumber}")
    Integer updateSessionKey(@Param("sessionKey") String sessionKey,
                             @Param("schoolNumber") String schoolNumber,
                             @Param("loginTime") Date loginTime);


}
