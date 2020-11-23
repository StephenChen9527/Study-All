package com.nullbugs.spring.context.anno.name1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean(name = "")
    public NameBean1 beanXYZ(){
        return new NameBean1();
    }
}
