package com.nullbugs.jdk;

import java.lang.reflect.Proxy;

public class JDKProxyTest {
    /**
     * jdk 动态代理，需要有  1个 接口
     *             一个 实现了 InvocationHandler 的实现类
     *             invoker 持有 接口实现类，
     *             然后通过Proxy，创建代理，再将 invoker再包裹一层，实现对应接口，
     *
     *             然后调用方法
     * @param args
     */

    public static void main(String[] args) {
        MyServiceImpl myService = new MyServiceImpl();
        JDKProxyMain main1 = new JDKProxyMain(myService);
        IMyService o = (IMyService) Proxy
                .newProxyInstance(myService.getClass().getClassLoader(), new Class[]{IMyService.class}, main1);
        o.sayHi("hhh");
    }
}
