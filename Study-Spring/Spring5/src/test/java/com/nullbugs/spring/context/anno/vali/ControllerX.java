package com.nullbugs.spring.context.anno.vali;

import com.sun.istack.internal.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class ControllerX {


    public void say(@Validated @NotNull PersonV p){
        System.out.println("say");
    }
}
