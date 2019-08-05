package com.xuenan.lab.daily_information.service.impl;

import com.xuenan.lab.daily_information.dao.EquipmentInformationDao;
import com.xuenan.lab.daily_information.model.ResponseModel;
import com.xuenan.lab.daily_information.service.EquipmentInformationService;
import com.xuenan.lab.entity.EquipmentInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentInformationServiceImpl implements EquipmentInformationService {

    @Autowired
    private EquipmentInformationDao equipmentInformationDao ;

    @Override
    public ResponseModel queryEquipmentInformation() {

        ResponseModel responseModel ;
        List<EquipmentInformation> information = equipmentInformationDao.queryEquipmentInformation();
        if( information != null ){
            responseModel = new ResponseModel();
            responseModel.setData(information);
        }else {
            responseModel = new ResponseModel(1201,"不存在有效的实验仪器信息");
        }
        return  responseModel ;

    }
}
