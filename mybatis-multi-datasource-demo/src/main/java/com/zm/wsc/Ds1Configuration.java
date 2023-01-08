package com.zm.wsc;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 自定义数据源，并指定对应的mapper
 * 使用自定义的数据源后，分页插件等都要重新配置加载，例如会出现查询分页数量为0的情况，都是因此产生
 */
@Configuration
@Slf4j
@MapperScan(basePackages = {"com.zm.wsc.ds1.generate.mapper"}, sqlSessionTemplateRef = "ds1SqlSessionTemplate")
public class Ds1Configuration {
    @Autowired
    private MybatisPlusInterceptor mybatisPlusInterceptor;


    @Bean("ds1DataSource")
    @ConfigurationProperties(prefix = "wsc.ds1")
    public DataSource defaultDataSource() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean(name = "ds1SqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("ds1DataSource") DataSource customDataSource) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(customDataSource);
        sqlSessionFactory.setMapperLocations(resolveMapperLocations());

        sqlSessionFactory.setPlugins(new Interceptor[]{mybatisPlusInterceptor});

        MybatisConfiguration mybatisConfiguration = new MybatisConfiguration();
        // 日志输出
        // mybatisConfiguration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        sqlSessionFactory.setConfiguration(mybatisConfiguration);

        return sqlSessionFactory.getObject();
    }


    public Resource[] resolveMapperLocations() {
        ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
        List<String> mapperLocations = new ArrayList<>();

        mapperLocations.add("classpath:com/zm/wsc/ds1/generate/mapper/xml/*.xml");
//        mapperLocations.add("classpath:com/zm/wsc/ds1/custom/mapper/xml/*.xml");

        List<Resource> resources = new ArrayList();
        if (!CollectionUtils.isEmpty(mapperLocations)) {
            for (String mapperLocation : mapperLocations) {
                try {
                    Resource[] mappers = resourceResolver.getResources(mapperLocation);
                    resources.addAll(Arrays.asList(mappers));
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("Get myBatis resources happened exception", e);
                }
            }
        }

        return resources.toArray(new Resource[resources.size()]);
    }

    @Bean(name = "ds1SqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("ds1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


}