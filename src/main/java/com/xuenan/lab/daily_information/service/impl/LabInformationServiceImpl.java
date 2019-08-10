package com.xuenan.lab.daily_information.service.impl;

import com.xuenan.lab.daily_information.dao.LabInformationDao;
import com.xuenan.lab.entity.LabInformation;
import com.xuenan.lab.daily_information.model.ResponseModel;
import com.xuenan.lab.daily_information.service.LabInformationService;
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
        LabInformation information = labInformationDao.queryLabInformation();
        if( information == null ){
            responseModel = new ResponseModel(1101,"不存在有效的实验室信息");

        }else{
            responseModel = new ResponseModel();
            responseModel.setData(information);
        }
        return  responseModel ;

    }

    @Override
    public ResponseModel editLabInformation(String name, String introduction) {

        ResponseModel model ;
        Integer count = labInformationDao.countInformation();
        if(count<0){
            labInformationDao.createInformation(name,introduction);
            count = labInformationDao.countInformation();
            if( count>0 ){
                model = new ResponseModel();
            }else {
                model = new ResponseModel(1102,"编辑实验室介绍失败");
            }
        }else {
            LabInformation labInformation = labInformationDao.queryLabInformation();
            Integer result = labInformationDao.updateInformation(name,introduction,labInformation.getId());
            if( result>0 ){
                model = new ResponseModel();
            }else {
                model = new ResponseModel(1102,"编辑实验室介绍失败");
            }
        }
        return model ;
    }

}
