package com.nullbugs.nospring;

import com.nullbugs.nospring.service.MyService;
import org.apache.dubbo.config.*;

/**
 * 服务消费者
 * 消费者只需要设置协议
 * 与消费地址即可
 */
public class MyConsumer {

    public static void main(String[] args) {
        ApplicationConfig config = new ApplicationConfig("consumer");

        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");

        RegistryConfig registryConfig = new RegistryConfig();
        //registryConfig.setAddress("zookeeper://127.0.0.1:2181");
        registryConfig.setAddress("N/A");

        ReferenceConfig<MyService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(config);
        referenceConfig.setTimeout(3000);
        referenceConfig.setUrl("127.0.0.1:20880");
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setInterface(MyService.class);
        referenceConfig.setVersion("1.0.0");
        MyService service = referenceConfig.get();

        String res = service.process("1");
        System.out.println(res);
    }
}
