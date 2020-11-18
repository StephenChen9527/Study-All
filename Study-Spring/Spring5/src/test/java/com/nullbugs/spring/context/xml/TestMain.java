package com.nullbugs.spring.context.xml;

import com.nullbugs.spring.context.xml.pojo.*;
import com.nullbugs.spring.context.xml.scop.Controller1;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.*;

public class TestMain {

    @Test
    public void testXMLBean(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com.nullbugs.spring.context.xml\\application.xml");
        Bus1 bean = context.getBean(Bus1.class);
        bean.say();
    }


    @Test
    public void testTowXMLBean(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com.nullbugs.spring.context.xml\\application.xml","com.nullbugs.spring.context.xml\\application-2.xml");
        Bus1 bus1 = context.getBean(Bus1.class);
        bus1.say();
        Bus2 bus2 = context.getBean(Bus2.class);
        bus2.sayMessage("test2");
    }


    @Test
    public void testRef(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com.nullbugs.spring.context.xml\\application.xml","com.nullbugs.spring.context.xml\\application-2.xml");
        Bus2 bus2 = context.getBean(Bus2.class);
        bus2.testBus1();
    }

    @Test
    public void testImport(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com.nullbugs.spring.context.xml\\application-3.xml");
        Bus2 bus2 = context.getBean(Bus2.class);
        bus2.testBus1();
    }

    @Test
    public void testRegister(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com.nullbugs.spring.context.xml\\application-3.xml");

        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();

        ConfigurableListableBeanFactory beanFactory1 = context.getBeanFactory();

        //
        //beanFactory.registerBeanDefinition();
        //beanFactory.registerSingleton();
    }


    @Test
    public void testAlias(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com.nullbugs.spring.context.xml\\application-4.xml");
        Object busid = context.getBean("busid");
        System.out.println(busid==null);
        busid = context.getBean("busname");
        System.out.println(busid==null);
        busid = context.getBean("busid1");
        System.out.println(busid==null);
        busid = context.getBean("busname1");
        System.out.println(busid==null);
    }

    @Bean()
    public Object testbean(){
        return null;
    }

    @Test
    public void testMyFactory(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com.nullbugs.spring.context.xml\\application-factory.xml");
        Bus1 bus1 = (Bus1) context.getBean("bus1");
        bus1.say();
        Bus1 bean = context.getBean(Bus1.class);
        bean.say();
        System.out.println(bus1==bean);
    }


    @Test
    public void testC(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com.nullbugs.spring.context.xml\\application-construct.xml");
        ConstructTest bean = context.getBean(ConstructTest.class);
        bean.print();
        System.out.println(bean.getX());
        System.out.println(bean.getY());
    }

    @Test
    public void testSetter(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com.nullbugs.spring.context.xml\\application-construct.xml");
        SetterTest bean = context.getBean(SetterTest.class);
        bean.print();
        System.out.println(bean.getX());
        System.out.println(bean.getY());
    }


    @Test
    public void testAutowire(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com.nullbugs.spring.context.xml\\application-autowire.xml");
        Auto1 bean = context.getBean(Auto1.class);
        bean.say();
    }

    @Test
    public void testScope(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com.nullbugs.spring.context.xml\\application-scope.xml");
        Controller1 c = context.getBean(Controller1.class);
        c.process();

        c = context.getBean(Controller1.class);
        c.process();

    }


    @Test
    public void testScope2()throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("com.nullbugs.spring.context.xml\\application-scope.xml");
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 50; i++) {
            executor.execute(() -> {
                Controller1 co = context.getBean(Controller1.class);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                co.process();
            });
        }


        TimeUnit.DAYS.sleep(1);

    }
}
