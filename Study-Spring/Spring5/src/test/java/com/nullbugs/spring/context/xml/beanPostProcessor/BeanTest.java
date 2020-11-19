package com.nullbugs.spring.context.xml.beanPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

public class BeanTest implements BeanPostProcessor {

    /**
     * 初始化之前回调
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName+"---postProcessBeforeInitialization");
        return bean;
    }
    /**
     * 初始化之后回调
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName+"---postProcessAfterInitialization");
        return bean;
    }


}
