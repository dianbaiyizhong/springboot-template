package com.zhenmei.wsc.security.mybatis;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.TemplateType;

public class MybatisGeneratorAdmin {

    public void generate() {

        String projectPath = System.getProperty("user.dir") + "/wsc-spring-boot-security-starter" + "/src/main/java";
        FastAutoGenerator.create("jdbc:mysql://150.158.186.188/p7i_admin?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT%2B8&useTimezone=true", "root", "Mm5590026")
                .globalConfig(builder -> {
                    builder.author("nntk") // 设置作者
                            .outputDir(projectPath)
                            .fileOverride();
                })
                .packageConfig(builder -> {
                    builder.parent("com.zhenmei.wsc.security") // 设置父包名
                            .moduleName("mybatis.generate")
                            .controller("")
                    ;
                })
                .strategyConfig(builder -> {
                    builder.addInclude("t_admin_user", "t_permission", "t_role", "t_role_permission", "t_user_role")
                    ;
                }).templateConfig(builder -> {
                    builder.disable(TemplateType.CONTROLLER, TemplateType.SERVICE, TemplateType.SERVICEIMPL)

                    ;
                }).execute();
    }


    public static void main(String[] args) {

        new MybatisGeneratorAdmin().generate();

    }


}
