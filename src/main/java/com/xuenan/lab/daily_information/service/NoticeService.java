package com.xuenan.lab.daily_information.service;

import com.xuenan.lab.daily_information.model.ResponseModel;

public interface NoticeService {

    ResponseModel queryAllValidNoticeOrderByDate();
    ResponseModel queryAllInvalidNoticeOrderByDate();
    ResponseModel queryNoticeById(Integer id );
    ResponseModel createNotice(String title,String content, String authorName);
    ResponseModel banNotice(Integer id );
    ResponseModel enableNotice(Integer id );
    ResponseModel deleteNotice(Integer id );
    ResponseModel updateNotice(Integer id,String title,String content,String authorName);
}
