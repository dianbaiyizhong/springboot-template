package com.zhenmei.mybatis.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 自定义数据源，并指定对应的mapper
 * 使用自定义的数据源后，分页插件等都要重新配置加载，例如会出现查询分页数量为0的情况，都是因此产生
 */
@Configuration
@MapperScan(basePackages = {"com.zhenmei.mybatis.custom.dao"}, sqlSessionTemplateRef = "customSqlSessionTemplate")
public class CustomDataSourceConfiguration {
    @Bean("customDataSource")
    @ConfigurationProperties(prefix = "custom.datasource")
    public DataSource customDataSource() {
        return DruidDataSourceBuilder.create().build();
    }


    @Autowired
    private MybatisPlusInterceptor mybatisPlusInterceptor;

    @Bean(name = "customSqlSessionFactory")
    public SqlSessionFactory customSqlSessionFactory(@Qualifier("customDataSource") DataSource customDataSource) throws Exception {
        String mapperLocations = "classpath:com/zhenmei/mybatis/custom/mapper/*.xml";
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(customDataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactory.setMapperLocations(resolver.getResources(mapperLocations));


        sqlSessionFactory.setPlugins(new Interceptor[]{mybatisPlusInterceptor});

        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        // 日志输出
        mybatisConfiguration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        sqlSessionFactory.setConfiguration(mybatisConfiguration);

        return sqlSessionFactory.getObject();
    }

    @Bean(name = "customSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("customSqlSessionFactory") SqlSessionFactory sqlSessionFactory)
            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}
