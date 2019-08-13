package com.xuenan.lab.configuration;

import com.xuenan.lab.filter.LoginFilter;
import com.xuenan.lab.filter.ManagerFilter;
import com.xuenan.lab.filter.TeacherFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean managerFilterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new ManagerFilter());
        bean.addUrlPatterns("/user/all/*");
        bean.addUrlPatterns("/user/ban");
        bean.addUrlPatterns("/user/enable");
        bean.addUrlPatterns("/user/delete");
        bean.addUrlPatterns("/user/type");
        bean.addUrlPatterns("/information/photo/delete/*");
        bean.addUrlPatterns("/information/photo/name/*");
        bean.addUrlPatterns("/information/photo/new");
        bean.addUrlPatterns("/information/lab/edit");
        bean.addUrlPatterns("/information/humanity/new");
        bean.addUrlPatterns("/information/humanity/delete/*");
        bean.addUrlPatterns("/information/equipment/delete/*");
        bean.addUrlPatterns("/information/equipment/ban/*");
        bean.addUrlPatterns("/information/equipment/enable/*");
        bean.addUrlPatterns("/information/equipment/new");
        bean.addUrlPatterns("/information/equipment/all/invalid");
        bean.addUrlPatterns("/information/notice/new");
        bean.addUrlPatterns("/information/notice/ban/*");
        bean.addUrlPatterns("/information/notice/enable/*");
        bean.addUrlPatterns("/information/notice/update/*");
        bean.addUrlPatterns("/information/notice/delete/*");
        bean.addUrlPatterns("/information/notice/all/invalid");
        return bean;
    }

    @Bean
    public FilterRegistrationBean teahcerFilterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new TeacherFilter());
        bean.addUrlPatterns("/experiment/self");
        bean.addUrlPatterns("/experiment/new");
        bean.addUrlPatterns("/experiment/update/*");
        bean.addUrlPatterns("/report/experiment/*");
        bean.addUrlPatterns("/report/mark/*");
        return bean;
    }

    @Bean
    public FilterRegistrationBean studentFilterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new LoginFilter());
        bean.addUrlPatterns("/report/student/*");
        bean.addUrlPatterns("/report/remove/*");
        bean.addUrlPatterns("/report/new");
        bean.addUrlPatterns("/report/upload/*");
        return bean;
    }
}
