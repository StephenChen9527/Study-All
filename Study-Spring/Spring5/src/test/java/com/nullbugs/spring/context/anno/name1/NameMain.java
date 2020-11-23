package com.nullbugs.spring.context.anno.name1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = NameMain.class)
public class NameMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                 = new AnnotationConfigApplicationContext(NameMain.class);
        Object beanXYZ = context.getBean("beanXYZ");
        System.out.println(beanXYZ==null);
    }
}
