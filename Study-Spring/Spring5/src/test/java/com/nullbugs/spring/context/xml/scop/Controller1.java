package com.nullbugs.spring.context.xml.scop;

import org.springframework.context.annotation.Bean;

public class Controller1 {

    private ThreadA threadA;

    
    public ThreadA getThreadA() {
        return threadA;
    }

    public void setThreadA(ThreadA threadA) {
        this.threadA = threadA;
    }

    public void process(){
        threadA.process();
    }
}
