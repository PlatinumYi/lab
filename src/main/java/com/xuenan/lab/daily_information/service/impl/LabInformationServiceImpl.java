package com.xuenan.lab.daily_information.service.impl;

import com.xuenan.lab.daily_information.dao.LabInformationDao;
import com.xuenan.lab.entity.LabInformation;
import com.xuenan.lab.daily_information.model.ResponseModel;
import com.xuenan.lab.daily_information.service.LabInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class LabInformationServiceImpl implements LabInformationService {

    @Autowired
    private LabInformationDao labInformationDao ;

    //图片文件最大不能高于5M
    private static final long MAX_FILE_SIZE = 5*1024*1024 ;

    private static final String LOCAL_STORAGE ="/root/LabFiles" ;

    private static final String MODULE_URL = "/data/";

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

    @Override
    public ResponseModel changeFile(MultipartFile file) {

        ResponseModel model ;
        LabInformation information = labInformationDao.queryLabInformation();

        if( file.isEmpty() ){
            return new ResponseModel(1405,"文件上传失败");
        }
        else if( file.getSize() > MAX_FILE_SIZE ){
            return new ResponseModel(1406,"文件过大");
        }

        String fileName = file.getOriginalFilename() ;
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);



            fileName = "equipmentGuidanceFile"+"."+suffix ; //根据当前毫秒数命名文件，防止重名
            System.out.println(fileName);
            try {
                File targetFile = new File(LOCAL_STORAGE+MODULE_URL+fileName);
                System.out.println(targetFile.getPath());
                file.transferTo(targetFile);
                labInformationDao.updateFile(MODULE_URL+fileName,information.getId());
                model = new ResponseModel() ;
            }catch (IOException e){
                model = new ResponseModel(1404,"文件存储失败");
            }


        return model ;
    }

}
