package com.luobo;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.luobo.genHelper.GeneratorHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        AutoGenerator generator= GeneratorHelper.getAutoGenerator();
        generator.execute();
    }

}
