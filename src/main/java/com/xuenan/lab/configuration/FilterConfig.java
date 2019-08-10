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
        bean.addUrlPatterns("/information/notice/");
        bean.addUrlPatterns("/information/notice/ban");
        bean.addUrlPatterns("/information/notice/enable");
        bean.addUrlPatterns("/information/notice/update");
        bean.addUrlPatterns("/information/notice/delete");
        return bean;
    }
}
