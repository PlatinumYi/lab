package com.xuenan.lab.daily_information.controller;

import com.xuenan.lab.daily_information.model.ResponseModel;
import com.xuenan.lab.daily_information.service.HumanityInformationService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/information/humanity")
public class HumanityInformationController {

    @Autowired
    private HumanityInformationService humanityInformationService ;

    @GetMapping("/")
    @ResponseBody
    public ResponseModel query(){
        return humanityInformationService.queryHumanityInformation() ;
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseModel insert(@RequestParam("photo") MultipartFile photo, @RequestParam("name") String name, @RequestParam("introduction") String introduction){
        return  humanityInformationService.createHumanityInformation(photo,name,introduction);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseModel delete(@PathVariable Integer id){
        return  humanityInformationService.removeHumanityInformation(id);
    }
}
