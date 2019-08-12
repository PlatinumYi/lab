package com.xuenan.lab.daily_information.controller;

import com.xuenan.lab.daily_information.model.ResponseModel;
import com.xuenan.lab.daily_information.service.EquipmentInformationService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/information/equipment")
public class EquipmentInformationController {

    @Autowired
    private EquipmentInformationService equipmentInformationService ;

    @GetMapping("/all/valid")
    @ResponseBody
    public ResponseModel queryValid(){
        return equipmentInformationService.queryValidEquipmentInformation();
    }

    @GetMapping("/all/invalid")
    @ResponseBody
    public ResponseModel queryInvalid(){
        return equipmentInformationService.queryInvalidEquipmentInformation();
    }

    @PutMapping("/ban/{id}")
    @ResponseBody
    public ResponseModel ban(@PathVariable Integer id ){
        return equipmentInformationService.banEquipment(id);
    }

    @PutMapping("/enable/{id}")
    @ResponseBody
    public ResponseModel enable(@PathVariable Integer id ){
        return equipmentInformationService.enableEquipment(id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseModel delete(@PathVariable Integer id ){
        return equipmentInformationService.removeEquipment(id);
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseModel create(@RequestParam("photo")MultipartFile file,
                                @RequestParam("name") String name,
                                @RequestParam("introduction") String introduction,
                                @RequestParam("dangerous") Integer dangerous)
    {
        return equipmentInformationService.createEquipment(file,name,introduction,dangerous);
    }
}
