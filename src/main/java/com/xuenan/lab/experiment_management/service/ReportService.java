package com.xuenan.lab.experiment_management.service;

import com.xuenan.lab.experiment_management.model.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

public interface ReportService {

    ResponseModel createReport(Integer userId,Integer experimentId);
    ResponseModel queryReportByStudentId(Integer currentUser);
    ResponseModel queryReportByExperimentId(Integer currentUser,Integer id);
    ResponseModel changeReportFile(Integer currentUser,MultipartFile file,Integer reportId);
    ResponseModel removeReport(Integer currentUser,Integer id);
    ResponseModel markReport(Integer currentUser,Integer id,Integer mark);
}
