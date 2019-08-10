package com.xuenan.lab.daily_information.service;

import com.xuenan.lab.daily_information.model.ResponseModel;
import org.springframework.web.multipart.MultipartFile;


public interface HumanityInformationService {

    ResponseModel queryHumanityInformation() ;
    ResponseModel createHumanityInformation(MultipartFile photo,String name,String introduction) ;
    ResponseModel removeHumanityInformation(Integer id);
}
