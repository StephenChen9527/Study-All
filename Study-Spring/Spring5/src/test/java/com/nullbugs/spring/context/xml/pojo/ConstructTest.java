package com.nullbugs.spring.context.xml.pojo;

import java.beans.ConstructorProperties;

public class ConstructTest {


    private int x;
    private String y;

    //@ConstructorProperties()
    public ConstructTest(int x, String y) {
        this.x = x;
        this.y = y;
    }

    public void print(){
        System.out.println(x);
        System.out.println(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
}
