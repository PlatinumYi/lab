package com.xuenan.lab.experiment_management.service;

import com.xuenan.lab.experiment_management.model.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public interface ExperimentService {

    ResponseModel createExperiment(String name,String instruction,Integer starterId,String teacherName,Date accessibleUntil,Date reportUntil,Integer maxStudentNumber,Integer beginTime,Integer stopTime,Integer roomId);
    ResponseModel changeExperiment(Integer user_id ,Integer id,String name,String instruction,String teacherName,Date accessibleUntil,Date reportUntil,Integer maxStudentNumber,Integer beginTime,Integer stopTime,Integer roomId);
    ResponseModel changeExperimentBook(Integer user_id , Integer id, MultipartFile file);
    ResponseModel querySelfExperiment(Integer id);
    ResponseModel deleteExperiment(Integer user_id,Integer id);
    ResponseModel queryAccessibleExperiment();
    ResponseModel startSignIn( Integer user_id , Integer id , Double longitude , Double latitude );
    ResponseModel getFile(Integer user_id,Integer id);
    ResponseModel getRecord();
}
