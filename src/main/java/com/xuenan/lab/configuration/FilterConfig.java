package com.xuenan.lab.configuration;

import com.xuenan.lab.filter.LoginFilter;
import com.xuenan.lab.filter.ManagerFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean loginFilterRegistrationBean(){
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
}
