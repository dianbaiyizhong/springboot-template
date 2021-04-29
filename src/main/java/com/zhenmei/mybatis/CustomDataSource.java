package com.zhenmei.mybatis;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//@Configuration
public class CustomDataSource {
    @Bean("dataSource")
    @ConfigurationProperties("custom.datasource")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

//    @Bean
//    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource adsDataSource) {
//        return new DataSourceTransactionManager(dataSource());
//    }


    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        String mapperLocations = "classpath:com.zhenmei.mybatis.generate.dao/*.xml";
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));
        return sqlSessionFactory.getObject();
    }
}
