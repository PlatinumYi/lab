package com.xuenan.starter.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.beans.PropertyVetoException;

@Configuration
@MapperScan("com.xuenan.starter.dao")
public class DaoConfiguration {

    @Value("${jdbc.driver}")
    private String jdbcDriver ;
    @Value("${jdbc.url}")
    private String jdbcUrl ;
    @Value("${jdbc.username}")
    private String jdbcUserName ;
    @Value("${jdbc.password}")
    private String jdbcPassword ;

    @Bean(name="dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setDriverClass(jdbcDriver);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUser(jdbcUserName);
        dataSource.setPassword(jdbcPassword);

        //不自动提交数据库连接池
        dataSource.setAutoCommitOnClose(false);
        return dataSource ;
    }
}
