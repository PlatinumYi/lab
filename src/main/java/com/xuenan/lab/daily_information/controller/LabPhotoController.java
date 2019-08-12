package com.xuenan.lab.daily_information.controller;

import com.xuenan.lab.daily_information.model.ResponseModel;
import com.xuenan.lab.daily_information.service.LabPhotoService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/information/photo")
public class LabPhotoController {

    @Autowired
    private LabPhotoService labPhotoService ;

    @GetMapping("/")
    @ResponseBody
    public ResponseModel queryPhotos(){
        return labPhotoService.queryAllPhotos();
    }

    @PutMapping("/name/{id}")
    @ResponseBody
    public ResponseModel changeName(@PathVariable Integer id,@RequestParam("name") String name){
        return labPhotoService.changePhotoName(id,name);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseModel deletePhoto(@PathVariable Integer id){
        return labPhotoService.deletePhoto(id);
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseModel createPhoto(@RequestParam("photo") MultipartFile photo,@RequestParam("name") String name){
        return  labPhotoService.createPhoto(photo,name);
    }
}
