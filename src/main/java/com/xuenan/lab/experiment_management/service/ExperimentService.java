package com.xuenan.lab.experiment_management.service;

import com.xuenan.lab.experiment_management.model.ResponseModel;

import java.util.Date;

public interface ExperimentService {

    ResponseModel createExperiment(String name,String instruction,Integer starterId,String teacherName,Date accessibleUntil,Date reportUntil,Integer maxStudentNumber);
    ResponseModel changeExperiment(Integer user_id ,Integer id,String name,String instruction,String teacherName,Date accessibleUntil,Date reportUntil,Integer maxStudentNumber);
    ResponseModel querySelfExperiment(Integer id);
    ResponseModel deleteExperiment(Integer user_id,Integer id);
    ResponseModel queryAccessibleExperiment();
    ResponseModel startSignIn( Integer user_id , Integer id , Double longitude , Double latitude );
}
