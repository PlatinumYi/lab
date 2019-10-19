package com.xuenan.lab.daily_information.service;

import com.xuenan.lab.daily_information.model.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

public interface LabInformationService {

    ResponseModel queryLabInformation() ;
    ResponseModel editLabInformation(String name,String introduction);
    ResponseModel changeFile(MultipartFile file);
}
