package com.zhenmei.mybatis;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;


public class MyBatisGenerator {


    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        String projectPath = System.getProperty("user.dir") + "/";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("nntk");
        gc.setOpen(false);
        gc.setFileOverride(true);
        gc.setEntityName("%sEntity");
        gc.setMapperName("%sMapper");


        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://150.158.186.188:3306/mamba_view?useUnicode=true&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("Mm5590026");
        mpg.setDataSource(dsc);


        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("mybatis.generate");
        pc.setParent("com.zhenmei");
        pc.setXml("xml");
        pc.setMapper("mapper");

        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);

        //逻辑删除字段
        strategy.setLogicDeleteFieldName("is_deleted");

        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);


        TemplateConfig tc = new TemplateConfig();
        tc.setController(null);
        tc.setService(null);
        tc.setServiceImpl(null);

        mpg.setTemplate(tc);


        mpg.execute();
    }

}
