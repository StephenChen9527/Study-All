package com.nullbugs.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class RedisClient {


    private String host = "49.232.122.106";
    private int port = 6379 ;
    private String password= "hello123";
    private Jedis jedis;
    @Before
    public void init(){
        jedis = new Jedis(host,port );
        jedis.auth(password);
    }

    @Test
    public void testClinet(){
        String key = "hello";
        String value = "world";
        String res = jedis.set(key, value);
        System.out.println(res);
    }
}
