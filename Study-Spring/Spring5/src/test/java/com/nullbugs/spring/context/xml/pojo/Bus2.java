package com.nullbugs.spring.context.xml.pojo;

public class Bus2 {

    private Bus1 bus1;

    public void setBus1(Bus1 bus1) {
        this.bus1 = bus1;
    }

    public void sayMessage(String message){
        System.out.println(message);
    }

    public void testBus1(){
        bus1.say();
    }
}
