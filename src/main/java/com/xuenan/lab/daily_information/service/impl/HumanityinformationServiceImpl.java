package com.xuenan.lab.daily_information.service.impl;

import com.xuenan.lab.daily_information.dao.HumanityInformationDao;
import com.xuenan.lab.daily_information.model.ResponseModel;
import com.xuenan.lab.daily_information.service.HumanityInformationService;
import com.xuenan.lab.entity.HumanityInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class HumanityinformationServiceImpl implements HumanityInformationService {

    @Autowired
    private HumanityInformationDao humanityInformationDao;

    //图片文件最大不能高于5M
    private static final long MAX_PHOTO_SIZE = 5*1024*1024 ;

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

    @Override
    public ResponseModel createHumanityInformation(MultipartFile file, String name, String introduction) {

        ResponseModel model ;

        if( file.isEmpty() ){
            return new ResponseModel(1002,"照片文件上传失败");
        }
        else if( file.getSize() > MAX_PHOTO_SIZE ){
            return new ResponseModel(1003,"照片文件过大");
        }
        String src = "/picture/teacher/" ;
        String fileName = file.getOriginalFilename() ;


        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        if( !suffix.equals("jpg") || !suffix.equals("png") ){
            model = new ResponseModel(1004,"非法的文件后缀名");
        }else {

            Date date = new Date();
            String currentMills = String.valueOf(date.getTime());
            fileName = currentMills+suffix ; //根据当前毫秒数命名文件，防止重名
            try {
                File targetFile = new File(src+fileName);
                file.transferTo(targetFile);
                humanityInformationDao.createHumanityInformation(name,introduction,src+fileName);
                model = new ResponseModel() ;
            }catch (IOException e){
                model = new ResponseModel(1005,"文件存储失败");
            }
        }

        return model ;
    }

    @Override
    public ResponseModel removeHumanityInformation(Integer id) {

        ResponseModel model ;
        HumanityInformation information = humanityInformationDao.queryHumanityInformationById(id);
        if( information == null ){
            model = new ResponseModel(1006,"目标信息不存在");
        }else {
            Integer result = humanityInformationDao.removeHumanityInformation(id);
            if( result>0 ){
                model = new ResponseModel();
            }else {
                model = new ResponseModel(1007,"信息删除失败");
            }
        }
        return  model ;
    }
}
