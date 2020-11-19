package com.nullbugs.spring.context.anno.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Resource;

public class RequireTest {
    private String name;

    public String getName() {
        return name;
    }

    @Qualifier
    public void setName(String name) {
        this.name = name;
    }
}
