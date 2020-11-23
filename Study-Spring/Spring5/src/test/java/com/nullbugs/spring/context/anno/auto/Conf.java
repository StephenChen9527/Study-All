package com.nullbugs.spring.context.anno.auto;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


public class Conf {

    @Bean
    public CBean1 cBean1(){
        return new CBean1();
    }
    @Bean
    public CBean2 cBean2(){
        return new CBean2(cBean1());
    }
}
