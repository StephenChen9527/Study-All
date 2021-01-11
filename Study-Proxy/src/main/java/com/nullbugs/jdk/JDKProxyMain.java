package com.nullbugs.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JDKProxyMain implements InvocationHandler {

    private Object target;

    public JDKProxyMain(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("method before");
        Object res = method.invoke(target, args);
        System.out.println("method after");
        return res;
    }
}
