package com.nullbugs.spring.context.xml.pojo;

import org.springframework.beans.factory.annotation.Autowired;

public class SetterTest {


    private int x;
    private String y;

    public void print(){
        System.out.println(x);
        System.out.println(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        System.out.println("setter x "+x);
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        System.out.println("setter y "+ y);
        this.y = y;
    }
}
