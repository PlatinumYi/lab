package com.xuenan.lab.experiment_management.service.impl;

import com.xuenan.lab.entity.Experiment;
import com.xuenan.lab.entity.Report;
import com.xuenan.lab.entity.User;
import com.xuenan.lab.experiment_management.dao.ExperimentDao;
import com.xuenan.lab.experiment_management.dao.ReportDao;
import com.xuenan.lab.experiment_management.model.ResponseModel;
import com.xuenan.lab.experiment_management.service.ReportService;
import com.xuenan.lab.user_management.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.jdo.annotations.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {


    @Autowired
    private ReportDao reportDao ;

    @Autowired
    private ExperimentDao experimentDao ;

    @Autowired
    private UserDao userDao ;

    private static final long MAX_REPORT_SIZE = 1024*1024*10;

    private static final String LOCAL_STORAGE ="E:/SpringBootProjectResources/Lab" ;

    private static final String MODULE_URL = "/experiment/homework/" ;
    @Override
    @Transactional
    public ResponseModel createReport(Integer userId,Integer experimentId) {


        ResponseModel model ;

        Experiment experiment = experimentDao.queryExperimentById(experimentId);
        User user = userDao.queryUserById(userId);
        if( experiment == null ){
            model = new ResponseModel(5001,"实验不存在");
        }else if( user == null || user.getValid()==0 ){
            model = new ResponseModel(5002,"用户不存在");
        }else if( experiment.getAccessibleUntil().before(new Date())){
            model = new ResponseModel(5003,"实验报名已经截止");
        }else if( reportDao.checkSelectedStudent(userId,experimentId) > 0 ){
            model = new ResponseModel(5015,"不允许重复选课");
        } else {

            Integer result = reportDao.createReport(experimentId,userId);
            if( result == 0 ){
                model = new ResponseModel(5004,"添加实验失败");
            }else {
                model = new ResponseModel();
            }
        }
        experimentDao.setCurrentStudentNumber(experiment.getCurrentStudentNumber()+1,experimentId);
        return model ;
    }

    @Override
    public ResponseModel queryReportByStudentId(Integer currentUser) {

        ResponseModel model = new ResponseModel();
        List<Report> reports = reportDao.queryReportByStudentId(currentUser);
        model.setData(reports);
        return model ;
    }

    @Override
    public ResponseModel queryReportByExperimentId(Integer currentUser,Integer id) {

        ResponseModel model = new ResponseModel();
        Experiment experiment = experimentDao.queryExperimentById(id);
        if( experiment == null || !currentUser.equals(experiment.getStarterId())){
            model = new ResponseModel(5014,"用户不是实验的预约者或发起者");
            return model ;
        }
        List<Report> reports = reportDao.queryReportByExperimentId(id);
        model.setData(reports);
        return model ;
    }

    @Override
    public ResponseModel changeReportFile(Integer currentUser,MultipartFile file, Integer reportId) {

        ResponseModel model ;

        if( file.isEmpty() ){
            return new ResponseModel(5005,"报告上传失败");
        }
        else if( file.getSize() > MAX_REPORT_SIZE ){
            return new ResponseModel(5006,"报告文件过大");
        }

        String fileName = file.getOriginalFilename() ;


        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);

        Report report = reportDao.queryReportById(reportId);
        User user = report.getStudent();
        Experiment experiment = report.getExperiment();

        if( !currentUser.equals(user.getId())){
            model = new ResponseModel(5014,"用户不是实验的预约者或发起者");
            return model ;
        }

        fileName = user.getSchoolNumber().toString()+"_"+experiment.getId()+"_"+experiment.getName()+"."+suffix ;
        //根据学号，实验内容上传文档防止重名
        try {
                File targetFile = new File(LOCAL_STORAGE+MODULE_URL+fileName);
                if(targetFile.exists()){
                    targetFile.delete();
                }
                file.transferTo(targetFile);
                reportDao.changeFileSrc(reportId,MODULE_URL+fileName);
                model = new ResponseModel() ;
            }catch (IOException e){
                model = new ResponseModel(5007,"报告存储失败");
        }

        return model ;
    }

    @Override
    public ResponseModel removeReport(Integer currentUser,Integer id) {

        ResponseModel model ;

        Report report = reportDao.queryReportById(id);
        if( report==null ){
            model = new ResponseModel(5008, "报告不存在");
        } else if( !currentUser.equals(report.getStudent().getId())){
            model = new ResponseModel(5014,"用户不是实验的预约者或发起者");
        }else {
            Experiment experiment = report.getExperiment();
            if (experiment.getAccessibleUntil().before(new Date())) {
                model = new ResponseModel(5009, "实验报名已经截止，不能取消预约");
            }  else {
                Integer result = reportDao.removeReportById(id);
                experimentDao.setCurrentStudentNumber(experiment.getCurrentStudentNumber()-1,experiment.getId());
                if (result == 0) {
                    model = new ResponseModel(5010, "取消预约实验失败");
                } else {
                    if( report.getReportFileSrc()!=null){
                        File file = new File(LOCAL_STORAGE+report.getReportFileSrc());
                        file.delete() ;
                    }
                    model = new ResponseModel();
                }
            }
        }
        return model ;
    }

    @Override
    public ResponseModel markReport(Integer currentUser, Integer id, Integer mark) {
        ResponseModel model ;

        Report report = reportDao.queryReportById(id);
        if( report==null ){
            model = new ResponseModel(5008, "报告不存在");
        }else if( !currentUser.equals(report.getExperiment().getStarterId())){
            model = new ResponseModel(5014,"用户不是实验的预约者或发起者");
        }else {
            Experiment experiment = report.getExperiment();
            if (experiment.getReportUntil().after(new Date())) {
                model = new ResponseModel(5011, "报告提交尚未截止，不能评分");
            } else if( mark>100 || mark<0){
                model = new ResponseModel(5012, "评分必须大于等于0且小于等于100");
            } else {
                Integer result = reportDao.markReport(id, mark);
                if (result == 0) {
                    model = new ResponseModel(5013, "报告评分失败");
                } else {
                    model = new ResponseModel();
                }
            }
        }
        return model ;
    }
}
