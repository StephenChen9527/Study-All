package com.nullbugs.springxml.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

public class ProviderMain {

    public static void main(String[] args)throws Exception {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("application-provider.xml");

        TimeUnit.DAYS.sleep(2);
    }
}
