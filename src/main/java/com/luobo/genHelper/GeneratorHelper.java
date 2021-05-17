package com.luobo.genHelper;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author chenlingyu
 */
public class GeneratorHelper {
    public static AutoGenerator getAutoGenerator() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://42.192.153.6:3306/edu?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("edu");
        dsc.setPassword("edu");
        dsc.setTypeConvert(new MySqlTypeConvert(){
            @Override
            public DbColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                if ( fieldType.toLowerCase().contains( "datetime" ) ) {
                    return DbColumnType.TIMESTAMP;
                }
                return (DbColumnType) super.processTypeConvert(globalConfig, fieldType);
            }
        });
        mpg.setDataSource(dsc);

        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        mpg.setStrategy(strategy);

        PackageConfig pc = new PackageConfig();
        pc.setParent("com.shtf.edu");
        pc.setEntity("bean.entity");
        mpg.setPackageInfo(pc);

        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir("F://gen/");
        gc.setFileOverride(true);
        gc.setAuthor("luobo");
        gc.setOpen(false);
        mpg.setGlobalConfig(gc);

        TemplateConfig templateConfig = new TemplateConfig();

        templateConfig.setEntity("templates/entity.java.vm");
        templateConfig.setService("templates/service.java.vm");
        templateConfig.setServiceImpl("templates/serviceImpl.java.vm");
        templateConfig.setXml("templates/mapper.xml.vm");
        templateConfig.setMapper("templates/mapper.java.vm");
        mpg.setTemplate(templateConfig);

        mpg.execute();
        return mpg;
    }
}
