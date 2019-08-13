package com.xuenan.lab.experiment_management.service.impl;

import com.xuenan.lab.entity.Experiment;
import com.xuenan.lab.experiment_management.dao.ExperimentDao;
import com.xuenan.lab.experiment_management.model.ResponseModel;
import com.xuenan.lab.experiment_management.service.ExperimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ExperimentServiceImpl implements ExperimentService {

    @Autowired
    private ExperimentDao experimentDao ;

    @Override
    public ResponseModel createExperiment(String name, String instruction, Integer starterId, String teacherName, Date accessibleUntil, Date reportUntil, Integer maxStudentNumber) {

        ResponseModel model ;
        if( accessibleUntil.after(reportUntil)){
            model = new ResponseModel(4001,"完结时间不能早于报名截止时间");
        }else if( experimentDao.createExperiment(name, instruction, starterId, teacherName, accessibleUntil, reportUntil, maxStudentNumber) == 0 ){
            model = new ResponseModel(4002,"创建实验失败");
        }else{
            model = new ResponseModel();
        }
        return model;
    }

    @Override
    public ResponseModel changeExperiment(Integer user_id ,Integer id, String name, String instruction,String teacherName, Date accessibleUntil, Date reportUntil, Integer maxStudentNumber) {

        ResponseModel model ;
        Experiment experiment = experimentDao.queryExperimentById(id);
        if( accessibleUntil.after(reportUntil)){
            model = new ResponseModel(4001,"完结时间不能早于报名截止时间");
        }else if( experiment == null ){
            model = new ResponseModel(4003,"目标实验不存在");
        } else if( maxStudentNumber<experiment.getCurrentStudentNumber()) {
            model = new ResponseModel(4004,"不能设定最大容量小于已选课人数");
        } else if( user_id != experiment.getStarterId()){
            model = new ResponseModel(4005,"不能设定非本人发起的实验");
        }else {
            Integer result = experimentDao.changeExperiment(id, name, instruction, teacherName, accessibleUntil, reportUntil, maxStudentNumber);
            if(result == 0){
                model = new ResponseModel(4006,"修改实验失败");
            }else {
                model = new ResponseModel();
            }
        }
        return model ;
    }

    @Override
    public ResponseModel querySelfExperiment(Integer id) {

        List<Experiment> experiments = experimentDao.queryExperimentByStarterId(id);
        ResponseModel model = new ResponseModel();
        model.setData(experiments);
        return model ;
    }

    @Override
    public ResponseModel queryAccessibleExperiment() {

        Date LondonToday = new Date();
        Long BeijingMills = LondonToday.getTime() + 3600*1000*8 ;
        Date BeijingToday = new Date(BeijingMills);

        List<Experiment> experiments = experimentDao.queryAccessibleExperiment(BeijingToday);
        ResponseModel model = new ResponseModel();
        model.setData(experiments);
        return model ;
    }
}