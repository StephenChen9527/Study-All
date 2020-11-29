package com.nullbugs.dubbo.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nullbugs.dubbo.Service.IDubboService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Method;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableDubbo
@RestController
@EnableHystrix
public class DubboConsumerApplication {

    @DubboReference(
            methods = {
                    @Method(name="getInfo",retries = 3,timeout = 2000)
            }
            )
    private IDubboService service;

    public static void main(String[] args) {
        SpringApplication.run(DubboConsumerApplication.class,args);
    }

    @RequestMapping(value = "/dubbo")
    @HystrixCommand(fallbackMethod = "mock")
    public String test(@RequestParam("id") String id)throws Exception{
        String info = service.getInfo(id);
        System.out.println(info);
        return "success";
    }

    public String mock(String id){
        return "error";
    }
}
