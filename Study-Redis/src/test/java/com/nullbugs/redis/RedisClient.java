package com.nullbugs.redis;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.*;

import java.util.HashMap;
import java.util.Map;

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
        //String res = jedis.set(key, value);
        jedis.set(key,value,"XX","ex",1);
        //System.out.println(res);
    }

    @Test
    public void hmSetTest(){
        jedis.hset("user","name","hello");
        jedis.hset("user","pwd","1");
        jedis.hset("user","age","1");
        jedis.hset("user", "f1", "v1");
    }


    @Test
    public void hgetTest(){
        String name = jedis.hget("user", "name");
        System.out.println(name);
        String name1 = jedis.hget("user", "name1");
        System.out.println(name1);
    }

    @Test
    public void hmgetTest(){
        Map<String,String> map = new HashMap<>();
        map.put("sno","1");
        map.put("sage","18");
        jedis.hmset("stu", map);
    }

    @Test
    public void hgetallTest(){
        Map<String, String> user = jedis.hgetAll("user");
        System.out.println(user);
    }

    @Test
    public void testGoe(){
        jedis.geoadd("zhengzhou",1.1,121,"zhongyuanqu");

    }


    @Test
    public void testMulit(){
        jedis.watch("a");
        Transaction multi = jedis.multi();
        try {
            Response<String> set = multi.set("a", "b");
            multi.set("b","c");
            multi.exec();
        } catch (Exception e) {
            jedis.unwatch();
            multi.discard();
            e.printStackTrace();
        }finally {

        }
    }
}
