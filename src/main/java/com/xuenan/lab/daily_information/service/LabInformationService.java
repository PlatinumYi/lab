package com.xuenan.lab.daily_information.service;

import com.xuenan.lab.daily_information.model.ResponseModel;

public interface LabInformationService {

    ResponseModel queryLabInformation() ;
    ResponseModel editLabInformation(String name,String introduction);
}
