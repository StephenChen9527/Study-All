package com.nullbugs.dubbo.provider;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
        scanBasePackageClasses = DubboApplication.class
)
@EnableDubbo
public class DubboApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboApplication.class,args);
    }
}
