package com.nullbugs.spring.context.anno.auto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Configuration
public class C56Cofig  {


    @Bean
    public CBean7 cBean7(){
        return new CBean7();
    }
}
