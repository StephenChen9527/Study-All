package com.nullbugs.spring.context.anno.vali;

import com.nullbugs.spring.context.xml.scop.Controller1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;

@Configuration
@ComponentScan(basePackageClasses = ValiTest.class)
public class ValiTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context
                = new AnnotationConfigApplicationContext(ValiTest.class);
        ControllerX bean = context.getBean(ControllerX.class);
        bean.say(null);
    }

    @Bean
    public PersonV personV(){
        return new PersonV();
    }
}
