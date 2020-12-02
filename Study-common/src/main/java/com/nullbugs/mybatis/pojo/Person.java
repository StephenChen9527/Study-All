package com.nullbugs.mybatis.pojo;

public class Person {
    private int id;
    private String name;
    private int age;
    private String sex;
    private Desci desci;
    private String pwd;
    private String my;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Desci getDesci() {
        return desci;
    }

    public void setDesci(Desci desci) {
        this.desci = desci;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMy() {
        return my;
    }

    public void setMy(String my) {
        this.my = my;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", desci=" + desci +
                ", pwd='" + pwd + '\'' +
                ", my='" + my + '\'' +
                '}';
    }
}
