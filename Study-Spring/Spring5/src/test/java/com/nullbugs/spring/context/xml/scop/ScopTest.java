package com.nullbugs.spring.context.xml.scop;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.HashMap;
import java.util.Map;

public class ScopTest implements Scope {
    public static final ThreadLocal<Object>  map = new ThreadLocal<Object>() ;
    @Override
    public Object get(String s, ObjectFactory<?> objectFactory) {
        if(map.get()==null){
            System.out.println("生成对象");
            map.set(objectFactory.getObject());
        }
        return map.get();
    }

    @Override
    public Object remove(String s) {
        return null;
    }

    @Override
    public void registerDestructionCallback(String s, Runnable runnable) {

    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return null;
    }
}
