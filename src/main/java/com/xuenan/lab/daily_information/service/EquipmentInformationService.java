package com.xuenan.lab.daily_information.service;

import com.xuenan.lab.daily_information.model.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

public interface EquipmentInformationService {

    ResponseModel queryValidEquipmentInformation() ;
    ResponseModel queryInvalidEquipmentInformation();
    ResponseModel enableEquipment(Integer id) ;
    ResponseModel banEquipment(Integer id );
    ResponseModel removeEquipment(Integer id);
    ResponseModel createEquipment(MultipartFile photo,String name,String introduction,Integer dangerous);

}
