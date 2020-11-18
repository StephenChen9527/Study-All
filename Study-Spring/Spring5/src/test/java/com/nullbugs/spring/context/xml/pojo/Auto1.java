package com.nullbugs.spring.context.xml.pojo;

public class Auto1 {


    private Auto2 auto2;

    public Auto1() {
        System.out.println("无参构造器");
    }
    public Auto1(Auto2 auto2) {
        System.out.println("有参构造器");
        this.auto2 = auto2;
    }

    public Auto2 getAuto2() {
        return auto2;
    }

    public void setAuto2(Auto2 auto2) {
        System.out.println("setter 方法");
        this.auto2 = auto2;
    }

    public void say(){
        auto2.sayHi();
    }
}
