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
                System.out.println("process");
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


    public String create(String path, byte[] data, List<ACL> acl, CreateMode createMode){
        try {
            return zkClient.create(path, data, acl, createMode);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Stat exists(String path,boolean watch){
        try {
            return  zkClient.exists(path, watch);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getChild(String path,boolean watch){
        try {
            return zkClient.getChildren(path,watch );
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Stat update(String path,byte[] data,int version){
        try {
            return  zkClient.setData(path,data , version);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] getData(String path,boolean watch,Stat stat){
        try {
            return zkClient.getData(path,watch ,stat );
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
