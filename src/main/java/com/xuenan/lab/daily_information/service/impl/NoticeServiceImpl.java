package com.xuenan.lab.daily_information.service.impl;

import com.xuenan.lab.daily_information.dao.NoticeDao;
import com.xuenan.lab.daily_information.model.ResponseModel;
import com.xuenan.lab.daily_information.service.NoticeService;
import com.xuenan.lab.entity.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {


    @Autowired
    private NoticeDao noticeDao ;

    @Override
    public ResponseModel queryAllValidNoticeOrderByDate() {

        List<Notice> notice = noticeDao.queryAllValidNoticeOrderByDate();
        ResponseModel model = new ResponseModel() ;
        model.setData(notice);
        return model ;
    }

    @Override
    public ResponseModel queryAllInvalidNoticeOrderByDate() {

        List<Notice> notice = noticeDao.queryAllInvalidNoticeOrderByDate();
        ResponseModel model = new ResponseModel() ;
        model.setData(notice);
        return model ;
    }
    @Override
    public ResponseModel queryNoticeById(Integer id) {

        ResponseModel model ;
        Notice notice = noticeDao.queryNoticeById(id);
        if( notice==null ){
            model = new ResponseModel(1301,"指定的通知不存在");
        }else {
            model = new ResponseModel();
            model.setData(notice);
        }
        return model ;
    }

    @Override
    public ResponseModel createNotice(String title, String content, String authorName) {

        ResponseModel model ;

        Date LondonToday = new Date();
        Long BeijingMills = LondonToday.getTime() + 8*3600*1000 ;
        Date BeijingToday = new Date(BeijingMills);

        Integer result = noticeDao.createNotice(title,content,authorName,BeijingToday);
        if( result>0 ){
            model = new ResponseModel();
        }else {
            model = new ResponseModel(1302,"发布通知失败");
        }
        return model ;
    }

    @Override
    public ResponseModel banNotice(Integer id) {

        ResponseModel model ;
        Notice notice = noticeDao.queryNoticeById(id) ;
        if( notice == null ){
            model = new ResponseModel(1301,"指定的通知不存在");
        }else if( notice.getValid() == 0 ){
            model = new ResponseModel(1303,"该通知已经是不可用状态");
        }else {
            Integer result = noticeDao.banNotice(id) ;
            if( result>0 ){
                model = new ResponseModel();
            }else {
                model = new ResponseModel(1304,"禁用通知失败");
            }
        }
        return model ;
    }

    @Override
    public ResponseModel enableNotice(Integer id) {
        ResponseModel model ;
        Notice notice = noticeDao.queryNoticeById(id) ;
        if( notice == null ){
            model = new ResponseModel(1301,"指定的通知不存在");
        }else if( notice.getValid() == 1 ){
            model = new ResponseModel(1305,"该通知已经是可用状态");
        }else {
            Integer result = noticeDao.enableNotice(id) ;
            if( result>0 ){
                model = new ResponseModel();
            }else {
                model = new ResponseModel(1306,"启用通知失败");
            }
        }
        return model ;
    }

    @Override
    public ResponseModel deleteNotice(Integer id) {
        ResponseModel model ;
        Notice notice = noticeDao.queryNoticeById(id) ;
        if( notice == null ){
            model = new ResponseModel(1301,"指定的通知不存在");
        }else if( notice.getValid() == 1 ){
            model = new ResponseModel(1307,"该通知在删除前需要被禁用");
        }else {
            Integer result = noticeDao.deleteNotice(id);
            if( result>0 ){
                model = new ResponseModel();
            }else {
                model = new ResponseModel(1308,"删除通知失败");
            }
        }
        return model ;
    }

    @Override
    public ResponseModel updateNotice(Integer id, String title, String content, String authorName) {


        Date LondonToday = new Date();
        Long BeijingMills = LondonToday.getTime() + 8*3600*1000 ;
        Date BeijingToday = new Date(BeijingMills);

        ResponseModel model ;

        Notice notice = noticeDao.queryNoticeById(id) ;
        if( notice == null ){
            model = new ResponseModel(1301,"指定的通知不存在");
        }else {
            Integer result = noticeDao.updateNotice(id,title,content,authorName,BeijingToday);
            if( result>0 ){
                model = new ResponseModel();
            }else {
                model = new ResponseModel(1309,"更新通知失败");
            }
        }
        return model ;
    }
}
