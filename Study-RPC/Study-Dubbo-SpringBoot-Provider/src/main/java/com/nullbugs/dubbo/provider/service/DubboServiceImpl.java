package com.nullbugs.dubbo.provider.service;

import com.nullbugs.dubbo.Service.IDubboService;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.HashMap;
import java.util.Map;

@DubboService
public class DubboServiceImpl implements IDubboService {
    @Override
    public String getInfo(String id) {
        System.out.println("id:"+id);
        Map<String,String> map = new HashMap<>();
        map.put("1",String.valueOf(System.currentTimeMillis()));
        map.put("2",String.valueOf(System.currentTimeMillis()));
        map.put("3",String.valueOf(System.currentTimeMillis()));
        map.put("4",String.valueOf(System.currentTimeMillis()));
        return map.get(id);
    }
}
