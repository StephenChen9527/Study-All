package com.nullbugs.spring.context.anno.val;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@PropertySource("classpath:jdbc2.properties")
@ComponentScan(basePackageClasses = AppMain.class)
public class AppMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppMain.class);
        DataSource1 da = context.getBean(DataSource1.class);
        System.out.println(da.getUrl());
        System.out.println(da.getUsername());
        System.out.println(da.getPassword());
    }
}
