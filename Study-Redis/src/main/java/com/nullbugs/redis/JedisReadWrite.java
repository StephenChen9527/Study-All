package com.nullbugs.redis;

import redis.clients.jedis.Jedis;

public class JedisReadWrite {

    Jedis write = new Jedis("127.0.0.1",6379);
    Jedis read = new Jedis("127.0.0.1",6480);

    public void set(String key,String value){
        write.set(key,value);
    }

    public String get(String key){
        return read.get(key);
    }

    public static void main(String[] args) {
        JedisReadWrite w = new JedisReadWrite();
        w.set("a","b");

        System.out.println(w.get("a"));
    }
}
