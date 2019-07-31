package com.xuenan.starter.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class SessionFactoryConfiguration {

    @Value("${mybatis.config.file}")
    private String mybatisConfigFilePath;
    @Value("${mybatis.path}")
    private String mapperPath;
    @Value("${entity.packge}")
    private String entityPackge;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;


    @Bean(name="sqlSessionFactory")
    public SqlSessionFactoryBean createSqlFactoryBean() throws IOException {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();

        bean.setConfigLocation(new ClassPathResource(mybatisConfigFilePath));

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String packageSearchPath = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath ;
        bean.setMapperLocations(resolver.getResources(packageSearchPath));
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage(entityPackge);
        return bean ;
    }

}
