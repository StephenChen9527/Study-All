package com.nullbugs.spring.context.anno.auto;

public class CBean4 {

    private CBean3 cBean3;

    public CBean4(CBean3 cBean3) {
        this.cBean3 = cBean3;
    }

    public CBean3 getcBean3() {
        return cBean3;
    }

    public void setcBean3(CBean3 cBean3) {
        this.cBean3 = cBean3;
    }
}
