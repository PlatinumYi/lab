package com.xuenan.starter.daily_information.service.impl;

import com.xuenan.starter.daily_information.dao.LabInformationDao;
import com.xuenan.starter.entity.LabInformation;
import com.xuenan.starter.daily_information.model.ResponseModel;
import com.xuenan.starter.daily_information.service.LabInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabInformationServiceImpl implements LabInformationService {


    @Autowired
    private LabInformationDao labInformationDao ;

    @Override
    public ResponseModel queryLabInformation() {

        ResponseModel responseModel ;
        List<LabInformation> information = labInformationDao.queryLabInformation();
        if( information == null || information.size()<1 ){
            responseModel = new ResponseModel(111,"不存在有效的实验室信息");

        }else if( information.size()>1 ){
            responseModel = new ResponseModel(112,"有效的实验室信息多于一条");
        }else{
            responseModel = new ResponseModel();
            responseModel.setData(information);
        }
        return  responseModel ;

    }
}