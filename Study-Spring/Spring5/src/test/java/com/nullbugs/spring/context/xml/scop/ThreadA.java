package com.nullbugs.spring.context.xml.scop;

public class ThreadA {


    public void process(){
        System.out.println(this.hashCode());
        System.out.println("process");
    }
}
