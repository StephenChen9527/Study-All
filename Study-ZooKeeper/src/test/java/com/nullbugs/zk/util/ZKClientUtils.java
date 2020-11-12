package com.nullbugs.zk.util;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ZKClientUtils {

    private ZooKeeper zkClient ;
    private String host;
    private int sessionTimeOut=2000;
    private Watcher watcher;
    public ZKClientUtils(String host) {
        this.host = host;
        getZkClient();
    }

    public ZKClientUtils(String host, int sessionTimeOut) {
        this(host);
        this.sessionTimeOut = sessionTimeOut;
    }

    public ZKClientUtils(String host, int sessionTimeOut, Watcher watcher) {
        this(host,sessionTimeOut);
        this.watcher = watcher;
    }

    public ZooKeeper getZkClient() {
        if(zkClient!=null){
            return zkClient;
        }
        try {
            zkClient = new ZooKeeper(host,sessionTimeOut,watcher==null?(watchedEvent)->{
                System.out.println("监控器被触发!触发的路径为："+watchedEvent.getPath());
            }:watcher);
            //由于连接为异步请求，部分版本存在连接初始化较为缓慢，
            //因此需要等待
            while (!zkClient.getState().isConnected()){
                TimeUnit.SECONDS.sleep(1);
            }
            return zkClient;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }




}
