package com.xuenan.lab.daily_information.service.impl;

import com.xuenan.lab.daily_information.dao.LabPhotoDao;
import com.xuenan.lab.daily_information.model.ResponseModel;
import com.xuenan.lab.daily_information.service.LabPhotoService;
import com.xuenan.lab.entity.LabPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class LabPhotoServiceImpl implements LabPhotoService {

    @Autowired
    private LabPhotoDao labPhotoDao ;

    //图片文件最大不能高于5M
    private static final long MAX_PHOTO_SIZE = 5*1024*1024 ;

    @Override
    public ResponseModel queryAllPhotos() {

        ResponseModel model = new ResponseModel() ;
        List<LabPhoto> photos = labPhotoDao.queryAllPhotos() ;
        model.setData(photos);
        return model ;

    }

    @Override
    public ResponseModel changePhotoName(Integer id, String name) {

        ResponseModel model ;
        LabPhoto labPhoto = labPhotoDao.queryPhotoById(id);
        if( labPhoto == null ){
            model = new ResponseModel(1401,"照片不存在");
        }else {
            Integer result = labPhotoDao.changePhotoName(id,name);
            if(result>0){
                model = new ResponseModel();
            }else {
                model = new ResponseModel(1402,"修改名字失败");
            }
        }
        return model ;
    }

    @Override
    public ResponseModel createPhoto(MultipartFile file, String name) {

        ResponseModel model ;

        if( file.isEmpty() ){
            return new ResponseModel(1405,"文件上传失败");
        }
        else if( file.getSize() > MAX_PHOTO_SIZE ){
            return new ResponseModel(1406,"文件过大");
        }
        String src = "/picture/lab/" ;
        String fileName = file.getOriginalFilename() ;


        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);
        if( !suffix.equals("jpg") || !suffix.equals("png") ){
            model = new ResponseModel(1403,"非法的文件后缀名");
        }else {

            Date date = new Date();
            String currentMills = String.valueOf(date.getTime());
            fileName = currentMills+suffix ; //根据当前毫秒数命名文件，防止重名
            try {
                File targetFile = new File(src+fileName);
                file.transferTo(targetFile);
                labPhotoDao.createPhoto(name,src+fileName);
                model = new ResponseModel() ;
            }catch (IOException e){
                model = new ResponseModel(1404,"文件存储失败");
            }
        }

        return model ;
    }

    @Override
    public ResponseModel deletePhoto(Integer id) {

        ResponseModel model ;
        LabPhoto labPhoto = labPhotoDao.queryPhotoById(id);
        if( labPhoto == null ){
            model = new ResponseModel(1401,"照片不存在");
        }else {
            Integer result = labPhotoDao.deletePhotoById(id);
            if(result>0){
                model = new ResponseModel();
            }else {
                model = new ResponseModel(1407,"删除照片失败");
            }
        }
        return model ;

    }
}
