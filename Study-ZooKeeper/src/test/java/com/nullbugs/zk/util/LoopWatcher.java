package com.nullbugs.zk.util;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.function.Consumer;

public class LoopWatcher implements Watcher {


    Consumer<WatchedEvent> busCode;
    Consumer<LoopWatcher> watcherCode;

    public LoopWatcher(Consumer<WatchedEvent> busCode) {
        this.busCode = busCode;
    }

    public LoopWatcher(Consumer<WatchedEvent> busCode, Consumer<LoopWatcher> watcherCode) {
        this.busCode = busCode;
        this.watcherCode = watcherCode;
    }

    @Override
    public void process(WatchedEvent event) {
        if(watcherCode!=null){
            //补充具体的监控内容
            watcherCode.accept(this);
        }
        busCode.accept(event);
    }
}
