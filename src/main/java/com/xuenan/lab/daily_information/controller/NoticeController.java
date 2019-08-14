package com.xuenan.lab.daily_information.controller;

import com.xuenan.lab.daily_information.model.ResponseModel;
import com.xuenan.lab.daily_information.service.NoticeService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/information/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService ;

    @GetMapping("/all/valid")
    @ResponseBody
    public ResponseModel queryAllValidNoticeOrderByDate(){
        return noticeService.queryAllValidNoticeOrderByDate();
    }

    @GetMapping("/all/invalid")
    @ResponseBody
    public ResponseModel queryAllInvalidNoticeOrderByDate(){
        return noticeService.queryAllInvalidNoticeOrderByDate();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseModel queryNoticeById(@PathVariable Integer id ){
        return noticeService.queryNoticeById(id);
    }

    @PostMapping("/new")
    @ResponseBody
    public ResponseModel createNotice( @RequestParam("title") String title,
                                       @RequestParam("content") String content,
                                       @RequestParam("author_name") String authorName ){
        return noticeService.createNotice(title,content,authorName);
    }

    @PutMapping("/ban/{id}")
    @ResponseBody
    public ResponseModel banNotice( @PathVariable Integer id ){
        return noticeService.banNotice(id);
    }

    @PutMapping("/enable/{id}")
    @ResponseBody
    public ResponseModel enableNotice( @PathVariable Integer id ){
        return noticeService.enableNotice(id);
    }

    //这个地方不符合RESTful规范，但Filter不区分方法，只区分URL，因此必须做出区分
    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseModel updateNotice( @PathVariable Integer id,
                                       @RequestParam("title") String title,
                                       @RequestParam("content") String content,
                                       @RequestParam("author_name") String authorName){
        return noticeService.updateNotice(id,title,content,authorName);
    }

    //这个地方不符合RESTful规范，但Filter不区分方法，只区分URL，因此必须做出区分
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ResponseModel deleteNotice( @PathVariable Integer id ){
        return noticeService.deleteNotice(id);
    }
}
