package com.xuenan.lab.daily_information.service.impl;

import com.xuenan.lab.daily_information.dao.HumanityInformationDao;
import com.xuenan.lab.daily_information.model.ResponseModel;
import com.xuenan.lab.daily_information.service.HumanityInformationService;
import com.xuenan.lab.entity.HumanityInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HumanityinformationServiceImpl implements HumanityInformationService {

    @Autowired
    private HumanityInformationDao humanityInformationDao;

    @Override
    public ResponseModel queryHumanityInformation() {

        ResponseModel responseModel ;
        List<HumanityInformation> information = humanityInformationDao.queryHumanityInformation();
        if( information != null ){
            responseModel = new ResponseModel();
            responseModel.setData(information);
        }else {
            responseModel = new ResponseModel(1001,"不存在有效的人员信息");
        }
        return  responseModel ;


    }
}
