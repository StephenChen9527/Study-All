package com.nullbugs.redis;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * 哨兵模式
 */
public class TestSentinels {

    @Test
    public void sentinels(){
        //jedis config
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(50);
        Set<String> hosts = new HashSet<>();
        //这里需要添加的是 哨兵节点的ip port
        hosts.add("172.20.10.3:26379");

        //哨兵模式连接池
        JedisSentinelPool sentinelPool = new JedisSentinelPool("mymaster",hosts,config);


        Jedis resource = sentinelPool.getResource();
        HostAndPort currentHostMaster = sentinelPool.getCurrentHostMaster();
        System.out.println(currentHostMaster.getHost());
        System.out.println(currentHostMaster.getPort());

    }

    @Test
    public void testRedis(){
        Jedis jedis = new Jedis("172.20.10.3",6379);
        jedis.get("a");
        System.out.println(jedis==null);
    }
}
