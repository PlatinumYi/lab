package com.xuenan.lab.daily_information.controller;

import com.xuenan.lab.daily_information.model.ResponseModel;
import com.xuenan.lab.daily_information.service.LabInformationService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/information/lab")
public class LabInformationController {

    @Autowired
    private LabInformationService labInformationService ;

    @GetMapping("/")
    @ResponseBody
    public ResponseModel query(){
        return labInformationService.queryLabInformation() ;
    }

    @PostMapping("/edit")
    @ResponseBody
    public ResponseModel edit(@Param("name") String name ,@Param("introduction") String introduction ){
        return labInformationService.editLabInformation(name,introduction);
    }

    @PutMapping("/file")
    @ResponseBody
    public ResponseModel file(@Param("name") MultipartFile file){
        return labInformationService.changeFile(file);
    }

}
