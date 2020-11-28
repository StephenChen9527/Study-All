package com.nullbugs.springxml.consumer;

import com.nullbugs.springxml.provider.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ConsumerMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context
                = new ClassPathXmlApplicationContext("application-consumer.xml");
        UserService userService = (UserService) context.getBean("userService");
        List<String> user = userService.getUser();
        System.out.println(user);
    }
}
