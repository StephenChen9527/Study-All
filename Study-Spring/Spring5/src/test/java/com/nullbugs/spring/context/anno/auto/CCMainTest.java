package com.nullbugs.spring.context.anno.auto;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@ComponentScan(basePackageClasses = CCMainTest.class)
@Configuration
public class CCMainTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                =  new AnnotationConfigApplicationContext(CCMainTest.class);

        CBean7 cBean7 = context.getBean(CBean7.class);
        System.out.println(cBean7==null);
        System.out.println(cBean7.getLoader()==null);
        System.out.println(cBean7.getResource()==null);
    }
}
