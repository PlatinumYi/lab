package com.xuenan.lab.daily_information.dao;

import com.xuenan.lab.entity.Notice;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface NoticeDao {

    @Select("SELECT * FROM notice WHERE valid=1 ORDER BY UPDATED_AT DESC")
    List<Notice> queryAllValidNoticeOrderByDate();

    @Select("SELECT * FROM notice WHERE valid=0 ORDER BY UPDATED_AT DESC")
    List<Notice> queryAllInvalidNoticeOrderByDate();

    @Select("SELECT * FROM notice WHERE ID=#{id}")
    Notice queryNoticeById(@Param("id") Integer id );

    @Insert("INSERT INTO notice(TITLE,CONTENT,AUTHOR_NAME,CREATED_AT,UPDATED_AT) VALUES( #{title},#{content},#{authorName},#{createdAt},#{createdAt})")
    Integer createNotice(@Param("title") String title,
                         @Param("content") String content,
                         @Param("authorName") String authorName,
                         @Param("createdAt") Date createdAt);

    @Update("UPDATE notice SET valid=0 WHERE ID=#{id}")
    Integer banNotice(@Param("id") Integer id );

    @Update("UPDATE notice SET valid=1 WHERE ID=#{id}")
    Integer enableNotice(@Param("id") Integer id );

    @Delete("DELETE FROM notice WHERE ID=#{id} AND valid=0")
    Integer deleteNotice(@Param("id") Integer id );

    @Update("UPDATE notice SET TITLE=#{title},CONTENT=#{content},AUTHOR_NAME=#{authorName},UPDATED_AT=#{updatedAt} WHERE ID=#{id}")
    Integer updateNotice(@Param("id") Integer id,
                         @Param("title") String title,
                         @Param("content") String content,
                         @Param("authorName") String authorName,
                         @Param("updatedAt") Date createdAt);
}
