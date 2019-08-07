package com.xuenan.lab.configuration;

import com.xuenan.lab.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean loginFilterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        //bean.setFilter(new LoginFilter());
        //bean.addUrlPatterns("/information/equipment/");
        return bean;
    }
}
