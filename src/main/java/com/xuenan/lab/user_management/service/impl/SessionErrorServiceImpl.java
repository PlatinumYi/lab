package com.xuenan.lab.user_management.service.impl;

import com.xuenan.lab.user_management.model.ResponseModel;
import com.xuenan.lab.user_management.service.SessionErrorService;
import org.springframework.stereotype.Service;

@Service
public class SessionErrorServiceImpl implements SessionErrorService {


    @Override
    public ResponseModel reportError() {
        return new ResponseModel(2101,"session过期或不存在");
    }
}
