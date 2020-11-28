package com.nullbugs.dubbo.provider.service;

import com.nullbugs.dubbo.Service.IDubboService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@DubboService(
        methods = {
                @Method(name = "getInfo",timeout = 2000)
        }
)
public class DubboServiceImpl implements IDubboService {
    @Override
    public String getInfo(String id) throws Exception{
        System.out.println("id:"+id);
        int i = 1/0;
        //TimeUnit.SECONDS.sleep(3);
        Map<String,String> map = new HashMap<>();
        map.put("1",String.valueOf(System.currentTimeMillis()));
        map.put("2",String.valueOf(System.currentTimeMillis()));
        map.put("3",String.valueOf(System.currentTimeMillis()));
        map.put("4",String.valueOf(System.currentTimeMillis()));
        return map.get(id);
    }
}
