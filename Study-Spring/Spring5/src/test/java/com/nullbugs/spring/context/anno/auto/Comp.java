package com.nullbugs.spring.context.anno.auto;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class Comp {

    @Bean
    public CBean3 cBean3(){
        return new CBean3();
    }
    @Bean
    public CBean4 cBean4(){
        return new CBean4(cBean3());
    }
}
