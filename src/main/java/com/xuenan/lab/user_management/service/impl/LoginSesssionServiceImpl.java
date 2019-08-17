package com.xuenan.lab.user_management.service.impl;

import com.xuenan.lab.entity.LoginSession;
import com.xuenan.lab.user_management.dao.LoginSessionDao;
import com.xuenan.lab.user_management.service.LoginSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginSesssionServiceImpl implements LoginSessionService {


    @Autowired
    private LoginSessionDao loginSessionDao ;

    @Override
    public LoginSession queryValidLoginSessionByToken(String token) {

        LoginSession session = loginSessionDao.queryLoginSessionByKey(token);
        if( session != null ){
            Long validUntil = session.getLoginTime().getTime() + (3600*1000*UserServiceImpl.SESSION_LIFE) ;
            Long current = new Date().getTime() + (3600*1000*8) ;
            if( current>validUntil ){
               session = null ;
            }
        }
        return session ;
    } // 如果token合法则返回session否则返回NULL，对时间的判断受变量影响，因此从Dao层独立出来
}
