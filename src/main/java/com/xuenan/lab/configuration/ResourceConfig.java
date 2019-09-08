package com.xuenan.lab.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class ResourceConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /**
         *  1. 实验指导文件
         *  2. 学生提交的报告
         *  3. 公开的资料
         *  4. 实验室照片
         *  5. 教师照片
         *  6. 设备照片
         */
        registry.addResourceHandler("/experiment/book/**").addResourceLocations("/root/LabFiles/experiment/book/");
        registry.addResourceHandler("/experiment/homework/**").addResourceLocations("/root/LabFiles/experiment/homework/");
        registry.addResourceHandler("/data/**").addResourceLocations("/root/LabFiles/data/");
        registry.addResourceHandler("/picture/lab/**").addResourceLocations("/root/LabFiles/picture/lab/");
        registry.addResourceHandler("/picture/teacher/**").addResourceLocations("/root/LabFiles/Lab/picture/teacher/");
        registry.addResourceHandler("/picture/equipment/**").addResourceLocations("/root/LabFiles/picture/equipment/");


    }
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8")) ;
        converters.add(converter) ;
   }
}
