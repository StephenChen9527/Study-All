package com.nullbugs.jdk;

public class MyServiceImpl implements IMyService {
    @Override
    public void sayHi(String name) {
        System.out.println("Hi,"+ name );
    }
}
