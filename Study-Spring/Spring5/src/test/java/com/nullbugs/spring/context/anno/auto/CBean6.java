package com.nullbugs.spring.context.anno.auto;

public class CBean6 {

    private CBean5 cBean5;

    public CBean6() {
    }

    public CBean6(CBean5 cBean5) {
        this.cBean5 = cBean5;
    }

    public CBean5 getcBean5() {
        return cBean5;
    }

    public void setcBean5(CBean5 cBean5) {
        this.cBean5 = cBean5;
    }
}
