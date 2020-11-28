package com.nullbugs.nospring;

import com.nullbugs.nospring.service.MyService;
import com.nullbugs.nospring.service.MyServiceImpl;
import org.apache.dubbo.config.*;

import java.util.concurrent.TimeUnit;

/**
 * 采用API方式，服务提供者
 */
public class MyProvider {

    public static void main(String[] args) throws Exception{
        //服务提供的service
        MyService service = new MyServiceImpl();

        ApplicationConfig config = new ApplicationConfig("provider");
        //使用直连的方式
        //config.setRegistry(null);

        RegistryConfig registryConfig = new RegistryConfig();
        //registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        registryConfig.setAddress("N/A");
        //通信协议
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20880);
        protocolConfig.setThreads(200);

        ServiceConfig<MyService> serviceConfig = new ServiceConfig<>();
        serviceConfig.setApplication(config);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setInterface(MyService.class);
        serviceConfig.setRef(service);
        serviceConfig.setVersion("1.0.0");

        serviceConfig.export();

        TimeUnit.DAYS.sleep(1);
    }
}
