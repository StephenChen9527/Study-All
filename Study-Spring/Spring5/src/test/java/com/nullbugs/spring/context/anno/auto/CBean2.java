package com.nullbugs.spring.context.anno.auto;

public class CBean2 {

    private CBean1 cBean1;

    public CBean2(CBean1 cBean1) {
        this.cBean1 = cBean1;
    }

    public CBean1 getcBean1() {
        return cBean1;
    }

    public void setcBean1(CBean1 cBean1) {
        this.cBean1 = cBean1;
    }
}
