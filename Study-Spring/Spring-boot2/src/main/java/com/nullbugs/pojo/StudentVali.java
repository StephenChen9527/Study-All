package com.nullbugs.pojo;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class StudentVali implements Validator {
    public boolean supports(Class<?> clazz) {
        return Student.class.equals(clazz);
    }

    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors,"name","name empty");
        ValidationUtils.rejectIfEmpty(errors,"age","name empty");
    }
}
