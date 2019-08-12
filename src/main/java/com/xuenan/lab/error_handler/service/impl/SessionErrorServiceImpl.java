package com.xuenan.lab.error_handler.service.impl;

import com.xuenan.lab.user_management.model.ResponseModel;
import com.xuenan.lab.error_handler.service.SessionErrorService;
import org.springframework.stereotype.Service;

@Service
public class SessionErrorServiceImpl implements SessionErrorService {



    @Override
    public ResponseModel reportNullError() {
        return new ResponseModel(2102,"token不存在");
    }

    @Override
    public ResponseModel reportInvalidError() {
        return new ResponseModel(2101,"token过期");
    }

    @Override
    public ResponseModel reportNotManagerError() {return new ResponseModel(2103,"当前登录用户不是管理员");
    }

    @Override
    public ResponseModel reportNotTeacherError() {return new ResponseModel(2104,"当前登录用户不是教师");
    }
}
