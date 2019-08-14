package com.xuenan.lab.daily_information.service.impl;

import com.xuenan.lab.daily_information.dao.EquipmentInformationDao;
import com.xuenan.lab.daily_information.model.ResponseModel;
import com.xuenan.lab.daily_information.service.EquipmentInformationService;
import com.xuenan.lab.entity.EquipmentInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service("EquipmentInformationService")
public class EquipmentInformationServiceImpl implements EquipmentInformationService {

    @Autowired
    private EquipmentInformationDao equipmentInformationDao ;

    //图片文件最大不能高于5M
    private static final long MAX_PHOTO_SIZE = 5*1024*1024 ;

    private static final String LOCAL_STORAGE ="E:/SpringBootProjectResources/Lab" ;

    private static final String MODULE_URL = "/picture/equipment/";

    @Override
    public ResponseModel queryValidEquipmentInformation() {

        ResponseModel responseModel ;
        List<EquipmentInformation> information = equipmentInformationDao.queryValidEquipment();
        if( information != null ){
            responseModel = new ResponseModel();
            responseModel.setData(information);
        }else {
            responseModel = new ResponseModel(1201,"不存在有效的实验仪器信息");
        }
        return  responseModel ;

    }

    @Override
    public ResponseModel queryInvalidEquipmentInformation() {
        ResponseModel responseModel ;
        List<EquipmentInformation> information = equipmentInformationDao.queryInvalidEquipment();
        if( information != null ){
            responseModel = new ResponseModel();
            responseModel.setData(information);
        }else {
            responseModel = new ResponseModel(1202,"不存在被禁用效的实验仪器信息");
        }
        return  responseModel ;
    }

    @Override
    public ResponseModel enableEquipment(Integer id) {

        ResponseModel responseModel ;

        EquipmentInformation information = equipmentInformationDao.queryEquipmentById(id);
        if( information == null ){
            responseModel = new ResponseModel(1203,"目标设备不存在");
        }else if( information.getValid()==1 ){
            responseModel = new ResponseModel(1204,"该设备已经处于可用状态");
        }else {
            Integer result = equipmentInformationDao.enableEquipment(id);
            if( result>0){
                responseModel = new ResponseModel();
            }else {
                responseModel = new ResponseModel(1205,"设置失败");
            }
        }
        return  responseModel ;
    }

    @Override
    public ResponseModel banEquipment(Integer id) {
        ResponseModel responseModel ;

        EquipmentInformation information = equipmentInformationDao.queryEquipmentById(id);
        if( information == null ){
            responseModel = new ResponseModel(1203,"目标设备不存在");
        }else if( information.getValid()==0 ){
            responseModel = new ResponseModel(1206,"该设备已经处于禁用状态");
        }else {
            Integer result = equipmentInformationDao.banEquipment(id);
            if( result>0){
                responseModel = new ResponseModel();
            }else {
                responseModel = new ResponseModel(1207,"设置失败");
            }
        }
        return  responseModel ;
    }

    @Override
    public ResponseModel removeEquipment(Integer id) {
        ResponseModel responseModel ;

        EquipmentInformation information = equipmentInformationDao.queryEquipmentById(id);
        if( information == null ){
            responseModel = new ResponseModel(1203,"目标设备不存在");
        }else if( information.getValid()==1 ){
            responseModel = new ResponseModel(1208,"被删除的设备不能处于可用状态");
        }else {
            Integer result = equipmentInformationDao.removeEquipment(id);
            File file = new File(LOCAL_STORAGE+information.getPhotoSrc());
            if( result>0){
                responseModel = new ResponseModel();
            }else {
                responseModel = new ResponseModel(1209,"设备删除失败");
            }
        }
        return  responseModel ;
    }

    @Override
    public ResponseModel createEquipment(MultipartFile file, String name, String introduction, Integer dangerous) {
        ResponseModel model ;

        if( file.isEmpty() ){
            return new ResponseModel(1210,"照片文件上传失败");
        }
        else if( file.getSize() > MAX_PHOTO_SIZE ){
            return new ResponseModel(1211,"照片文件过大");
        }


        String fileName = file.getOriginalFilename() ;


        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        if( !suffix.equals("jpg") && !suffix.equals("png") ){
            model = new ResponseModel(1212,"非法的文件后缀名");
        }else {

            Date date = new Date();
            String currentMills = String.valueOf(date.getTime());
            fileName = currentMills+"."+suffix ; //根据当前毫秒数命名文件，防止重名
            try {
                File targetFile = new File(LOCAL_STORAGE+MODULE_URL+fileName);
                file.transferTo(targetFile);
                equipmentInformationDao.createEquipment(name,introduction,dangerous,MODULE_URL+fileName);
                model = new ResponseModel() ;
            }catch (IOException e){
                model = new ResponseModel(1213,"文件存储失败");
            }
        }

        return model ;
    }
}
