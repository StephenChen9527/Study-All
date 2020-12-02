package com.nullbugs.mybatis.pojo;

import java.io.Serializable;

public class Desci{
    private String phone_number;
    private String phone;
    private String des;

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "Desci{" +
                "phone_number='" + phone_number + '\'' +
                ", phone='" + phone + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
