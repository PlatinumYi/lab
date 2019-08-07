package com.xuenan.lab.error_handler.service;

import com.xuenan.lab.user_management.model.ResponseModel;

public interface SessionErrorService {

    ResponseModel reportNullError();
    ResponseModel reportInvalidError();
    ResponseModel reportNotManagerError();
}
