package com.xuenan.lab.daily_information.service;

import com.xuenan.lab.daily_information.model.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

public interface LabPhotoService {

    ResponseModel queryAllPhotos();
    ResponseModel changePhotoName(Integer id,String name);
    ResponseModel createPhoto(MultipartFile file,String name);
    ResponseModel deletePhoto(Integer id);

}
