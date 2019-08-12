package com.xuenan.lab.user_management.service;

import com.xuenan.lab.entity.LoginSession;

public interface LoginSessionService {

    LoginSession queryValidLoginSessionByToken( String token ) ;

}
