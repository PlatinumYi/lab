package com.xuenan.lab.experiment_management.service.impl;

import com.xuenan.lab.entity.Experiment;
import com.xuenan.lab.entity.Report;
import com.xuenan.lab.experiment_management.dao.ExperimentDao;
import com.xuenan.lab.experiment_management.dao.ReportDao;
import com.xuenan.lab.experiment_management.model.ResponseModel;
import com.xuenan.lab.experiment_management.service.ExperimentService;
import com.xuenan.lab.tool.BeijingTime;
import com.xuenan.lab.tool.RandomSessionKey;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ExperimentServiceImpl implements ExperimentService {

    @Autowired
    private ExperimentDao experimentDao ;

    @Autowired
    private ReportDao reportDao ;

    private static final long MAX_GUIDE_BOOK_SIZE = 1024*1024*20;

    private static final String LOCAL_STORAGE ="/root/LabFiles" ;

    private static final String MODULE_URL = "/experiment/guide/" ;

    private static final String DATA_URL = "/data/" ;
    @Override
    public ResponseModel createExperiment(String name, String instruction, Integer starterId, String teacherName, Date accessibleUntil, Date reportUntil, Integer maxStudentNumber,Integer beginTime,Integer stopTime,Integer roomId) {

        ResponseModel model ;
        if( accessibleUntil.after(reportUntil) || stopTime<beginTime ){
            model = new ResponseModel(4001,"完结时间不能早于报名截止时间");
        }else if( experimentDao.createExperiment(name, instruction, starterId, teacherName, BeijingTime.getBeijingTime(accessibleUntil), BeijingTime.getBeijingTime(reportUntil), maxStudentNumber,beginTime,stopTime,roomId) == 0 ){
            model = new ResponseModel(4002,"创建实验失败");
        }else{
            model = new ResponseModel();
        }
        return model;
    }

    @Override
    public ResponseModel changeExperiment(Integer user_id ,Integer id, String name, String instruction,String teacherName, Date accessibleUntil, Date reportUntil, Integer maxStudentNumber,Integer beginTime,Integer stopTime,Integer roomId) {

        ResponseModel model ;
        Experiment experiment = experimentDao.queryExperimentById(id);
        if( accessibleUntil.after(reportUntil)|| stopTime<beginTime){
            model = new ResponseModel(4001,"完结时间不能早于报名截止时间");
        } else if( experiment == null ){
            model = new ResponseModel(4003,"目标实验不存在");
        } else if( experiment.getAccessibleUntil().before(new Date())) {
            model = new ResponseModel(4007,"不能修改已经可以提交报告的实验");
        } else if( maxStudentNumber<experiment.getCurrentStudentNumber()) {
            model = new ResponseModel(4004,"不能设定最大容量小于已选课人数");
        } else if( user_id != experiment.getStarterId()){
            model = new ResponseModel(4005,"不能设定非本人发起的实验");
        }else {
            Integer result = experimentDao.changeExperiment(id, name, instruction, teacherName,BeijingTime.getBeijingTime(accessibleUntil), BeijingTime.getBeijingTime(reportUntil), maxStudentNumber,beginTime,stopTime,roomId);
            if(result == 0){
                model = new ResponseModel(4006,"修改实验失败");
            }else {
                model = new ResponseModel();
            }
        }
        return model ;
    }

    @Override
    public ResponseModel changeExperimentBook(Integer user_id, Integer id, MultipartFile file) {
        ResponseModel model ;
        Experiment experiment = experimentDao.queryExperimentById(id);
        if( experiment == null ){
            model = new ResponseModel(4003,"目标实验不存在");
        } else if( experiment.getAccessibleUntil().before(new Date())) {
            model = new ResponseModel(4007,"不能修改已经可以提交报告的实验");
        } else if( user_id != experiment.getStarterId()){
            model = new ResponseModel(4005,"不能设定非本人发起的实验");
        } if( file.isEmpty() ){
            model = new ResponseModel(4013,"指导书上传失败");
        } else if( file.getSize() > MAX_GUIDE_BOOK_SIZE ){
            model = new ResponseModel(5006,"报告文件过大");
        }else {

            String fileName = file.getOriginalFilename() ;
            String suffix = fileName.substring(fileName.lastIndexOf(".")+1);

            fileName = experiment.getId()+"_"+experiment.getName()+"."+suffix ;
            //根据学号，实验内容上传文档防止重名
            try {
                File targetFile = new File(LOCAL_STORAGE+MODULE_URL+fileName);
                if(targetFile.exists()){
                    targetFile.delete();
                }
                file.transferTo(targetFile);
                experimentDao.changeExperimentBook(id,MODULE_URL+fileName);
                model = new ResponseModel() ;
            }catch (IOException e){
                model = new ResponseModel(5007,"报告存储失败");
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
    public ResponseModel deleteExperiment(Integer user_id ,Integer id) {

        ResponseModel model ;
        Experiment experiment = experimentDao.queryExperimentById(id);
        if( experiment == null ){
            model = new ResponseModel(4003,"目标实验不存在");
        } else if( experiment.getAccessibleUntil().before(new Date())) {
                model = new ResponseModel(4007,"不能取消已经可以提交报告的实验");
        } else if( user_id != experiment.getStarterId()){
            model = new ResponseModel(4008,"不能取消非本人发起的实验");
        }else {
            Integer result = experimentDao.deleteExperimentById(id);
            if(result == 0){
                model = new ResponseModel(4009,"删除实验失败");
            }else {
                reportDao.removeReportByExperimentId(id);
                model = new ResponseModel();
            }
        }
        return model ;
    }

    @Override
    public ResponseModel queryAccessibleExperiment() {

        Date LondonToday = new Date();
        Date BeijingToday = BeijingTime.getBeijingTime(LondonToday);

        List<Experiment> experiments = experimentDao.queryAccessibleExperiment(BeijingToday);
        ResponseModel model = new ResponseModel();
        model.setData(experiments);
        return model ;
    }

    @Override
    public ResponseModel startSignIn(Integer user_id, Integer id, Double longitude, Double latitude) {

        ResponseModel model ;
        Experiment experiment = experimentDao.queryExperimentById(id);
        if( experiment == null ){
            model = new ResponseModel(4003,"目标实验不存在");
        } else if( experiment.getStartSignIn() == 1){
            model = new ResponseModel(4010,"实验签到已经开始");
        }else if( user_id != experiment.getStarterId()){
            model = new ResponseModel(4011,"不能开始非本人发起的实验的签到");
        }else {
            Integer result = experimentDao.startSignIn(id,longitude,latitude);
            if(result == 0){
                model = new ResponseModel(4012,"实验签到开始失败");
            }else {
                reportDao.removeReportByExperimentId(id);
                model = new ResponseModel();
            }
        }
        return model ;
    }

    @Override
    public ResponseModel getFile(Integer user_id, Integer id) {
        ResponseModel model ;
        Experiment experiment = experimentDao.queryExperimentById(id);
        if( experiment == null ){
            model = new ResponseModel(4003,"目标实验不存在");
        } else if( user_id != experiment.getStarterId()){
            model = new ResponseModel(4011,"不能开始非本人发起的实验的签到");
        }else {
            List<Report> reports = reportDao.queryReportByExperimentId(id);
            HSSFWorkbook workbook = new HSSFWorkbook();
            String[] title = {"姓名","学号","年级"} ;
            HSSFSheet sheet = workbook.createSheet("选课情况");
            HSSFRow row = sheet.createRow(0);
            for( int i=0 ; i<title.length ; i++ ){
                row.createCell(i).setCellValue(title[i]);
            }
            for( int i=1 ; i<=reports.size() ; i++ ){
                row = sheet.createRow(i);
                row.createCell(0).setCellValue(reports.get(i-1).getStudent().getName());
                row.createCell(1).setCellValue(reports.get(i-1).getStudent().getSchoolNumber());
                row.createCell(2).setCellValue(reports.get(i-1).getStudent().getGrade());
            }

            String fileName = experiment.getId()+".xls" ;
            File file = new File(LOCAL_STORAGE+DATA_URL+fileName);
            if( file.exists() ){
                file.delete() ;
            }

            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                workbook.write(outputStream);
                outputStream.close();
            } catch (IOException e) {
                model = new ResponseModel(4013,"导出文选课记录失败");
                return model ;
            }

            model = new ResponseModel();
            model.setData(DATA_URL+fileName);

        }
        return model ;
    }

    @Override
    public ResponseModel getRecord() {
        ResponseModel model ;
        List<Experiment> experiments = experimentDao.queryExperiment();
        if( experiments == null ){
            model = new ResponseModel(4003,"目标实验不存在");
        }else {
            HSSFWorkbook workbook = new HSSFWorkbook();
            String[] title = {"课程名称","课程说明","任课教师","上课时间","上课地点","选课人数"} ;
            HSSFSheet sheet = workbook.createSheet("选课情况");
            HSSFRow row = sheet.createRow(0);
            for( int i=0 ; i<title.length ; i++ ){
                row.createCell(i).setCellValue(title[i]);
            }
            for( int i=1 ; i<=experiments.size() ; i++ ){
                row = sheet.createRow(i);
                row.createCell(0).setCellValue(experiments.get(i-1).getName());
                row.createCell(1).setCellValue(experiments.get(i-1).getInstruction());
                row.createCell(2).setCellValue(experiments.get(i-1).getTeacherName());
                row.createCell(3).setCellValue(experiments.get(i-1).getReportUntil());
                row.createCell(4).setCellValue(experiments.get(i-1).getRoom().getName());
                row.createCell(5).setCellValue(experiments.get(i-1).getCurrentStudentNumber());
            }

            String fileName = RandomSessionKey.getRandomChar(35)+".xls" ;
            File file = new File(LOCAL_STORAGE+DATA_URL+fileName);
            if( file.exists() ){
                file.delete() ;
            }

            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                workbook.write(outputStream);
                outputStream.close();
            } catch (IOException e) {
                model = new ResponseModel(4014,"导出开课记录失败");
                return model ;
            }

            model = new ResponseModel();
            model.setData(DATA_URL+fileName);

        }
        return model ;
    }
}
