package com.nullbugs.zk;

import com.nullbugs.zk.util.ZKClientUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author StephenChen
 * @date 2020年10月29日17:31:51
 * @version 1.0
 * @lastupdate 2020年10月29日17:31:51
 *
 */
public class ZKClinetDemo {

    public static final String zkHost = "localhost:2181";
    public static final String zkHosts = "localhost:2181,localhost:2182,localhost:2183";
    ZKClientUtils client;

    @Before
    public void init(){
        client = new ZKClientUtils(zkHost);
    }

    /**
     * 测试create方法
     * @param path：指定ZNode路径， 如果是多级路径 eg: /servers/s1 在这种情况下
     *            如果path /servers 不存在会报错 NoNodeException: KeeperErrorCode = NoNode for /server/s1
     * @param data：指定节点数据
     * @param ACL:权限：共有3种：
     *                 1、ZooDefs.Ids.OPEN_ACL_UNSAFE   无权限 一般使用这个
     *                 2、ZooDefs.Ids.READ_ACL_UNSAFE  只读权限
     *                 3、ZooDefs.Ids.CREATOR_ALL_ACL  创建着所有权限
     * @param CreateModel:创建模式，共有4种
     *                   1、EPHEMERAL 临时  会话结束后消失
     *                   2、EPHEMERAL_SEQUENTIAL 临时+序号 会话结束后消失
     *                   3、PERSISTENT  持久
     *                   4、PERSISTENT_SEQUENTIAL  持久+序号
     */
    @Test
    public void testCreate()throws Exception{
        //String path = "/server/S1";   // error path
        String path = "/server";
        String data = "/server";
        String s = client.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(s);
        TimeUnit.DAYS.sleep(1);
    }

    /**
     * 测试 exists 方法
     * @path path 需要查看节点的 path，如果路径错误，不会抛出异常
     * @return Stat 返回节点的详细信息，stat结构
     */
    @Test
    public void testExists(){
        String path = "/servers";
        Stat stat = client.exists(path, false);
        if(stat!=null){
            System.out.println(stat.toString());
        }else{
            System.out.println("not exists");
        }

    }

    /**
     * 测试getdata方法
     * @param path  不存在仍然会报错
     * @param watch 是否添加监控
     * @param stat Stat 如果需要节点stat信息，则传一个stat对象即可
     */
    @Test
    public void testGetData(){
        String path = "/servers";
        //byte[] data = client.getData(path, false, null);
        //Stat stat = new Stat(1L,1L,1L,1L,1,1,1,1L,1,1,1L);
        Stat stat = new Stat();
        byte[] data = client.getData(path, false, stat);
        System.out.println(new String(data));
        System.out.println(stat.toString());
    }

}
