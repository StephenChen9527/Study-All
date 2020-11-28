package com.nullbugs.nospring.service;

public class MyServiceImpl implements MyService{

    @Override
    public String process(String str) {
        System.out.println("进行了调用"+str);
        return "success";
    }


}
