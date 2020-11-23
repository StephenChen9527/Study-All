package com.nullbugs.spring.context.anno.vali;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PersionValidation implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        System.out.println("supports");
        return PersonV.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        System.out.println("validate");
        ValidationUtils.rejectIfEmpty(errors,"name","name.empty");
    }
}
