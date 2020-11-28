package com.nullbugs.dubbo.consumer;

import com.nullbugs.dubbo.Service.IDubboService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDubbo
@RestController
public class DubboApplication {

    @DubboReference
    private IDubboService service;

    public static void main(String[] args) {
        SpringApplication.run(DubboApplication.class,args);
    }

    @RequestMapping(value = "/dubbo")
    public String test(@RequestParam("id") String id){
        String info = service.getInfo(id);
        System.out.println(info);
        return "success";
    }
}
