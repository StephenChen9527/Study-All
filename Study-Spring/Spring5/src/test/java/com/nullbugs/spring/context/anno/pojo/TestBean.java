package com.nullbugs.spring.context.anno.pojo;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Service;

@Service
public class TestBean implements BeanPostProcessor , Ordered {

    private String file1;
    private String file2;

    public String getFile1() {
        return file1;
    }

    public void setFile1(String file1) {
        System.out.println("setFile1");
        this.file1 = file1;
    }

    public String getFile2() {
        return file2;
    }

    public void setFile2(String file2) {
        System.out.println("setFile2");
        this.file2 = file2;
    }

    /**
     * 初始化之前回调
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println(beanName+"---TestBean    postProcessBeforeInitialization");
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
        System.out.println(beanName+"---TestBean    postProcessAfterInitialization");
        return bean;
    }

    @Override
    public int getOrder() {
        return 2;
    }
}
