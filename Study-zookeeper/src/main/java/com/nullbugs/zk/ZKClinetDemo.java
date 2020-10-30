package com.nullbugs.zk;

import com.nullbugs.zk.util.LoopWatcher;
import com.nullbugs.zk.util.ZKClientUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author StephenChen
 * @date 2020年10月29日17:31:51
 * @version 1.0
 * @lastupdate 2020年10月30日09:58:53
 *
 */
public class ZKClinetDemo {

    public static final String zkHost = "localhost:2181";
    public static final String zkHosts = "localhost:2181,localhost:2182,localhost:2183";
    ZooKeeper client;

    @Before
    public void init(){
        client = new ZKClientUtils(zkHost).getZkClient();
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
        String path = "/servers/create";
        String data = "/server3";
        String s = client.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        System.out.println(s);
        //TimeUnit.DAYS.sleep(1);
    }

    /**
     * zk 官方API文档上标注为 create 的异步方式
     * @param StringCallback 创建完成的回调
     * @ctx object 可以将任意参数传入，并且在回调的时候，将次参数进行回传
     *   ctx = 上下文
     */
    @Test
    public void testCreateAsynchronous ()throws Exception{
        String path = "/servers/create";
        String data = "/server";
        Object ctx = new Object();
        System.out.println("create before"+ctx);


        /**
         * StringCallback 返回释义
         * @param rc   返回状态码
         * @param path path路径
         * @param ctx  上下文
         * @param name The name of the Znode that was created.
         * On success, <i>name</i> and <i>path</i> are usually
         * equal, unless a sequential node has been created.
         */
        client.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, new AsyncCallback.StringCallback() {
            @Override
            public void processResult(int rc, String path, Object ctx, String name) {
                System.out.println("processResult + rc："+rc);
                System.out.println("processResult + path："+path);
                System.out.println("processResult + ctx："+ctx);
                System.out.println("processResult + name："+name);
            }
        }, ctx);
        System.out.println(ctx.toString());
        TimeUnit.DAYS.sleep(1);
    }


    /**
     * create 方法创建成功会 触发 exists 和getData 时候的设置的监控器 ，不会触发getChildren的？
     * 临时节点无法创建子节点
     */
    @Test
    public void testCreateTiggerExists()throws Exception{
        String path = "/servers/create";
        /**
         * 这里的 true 会将 触发时间发送到 zk初始化的 process方法里面
         */
        //Stat exists = client.exists(,path true);
        //

        client.exists(path,new LoopWatcher(
         (w)->{
            //业务代码
             try {
                 Stat ex = client.exists(path, false);
                 if(ex!=null){
                     byte[] data = client.getData(path, false, null);
                     System.out.println("获取数据为data:"+new String(data));
                 }else{
                     System.out.println("数据节点被删除！");
                 }
             } catch (KeeperException e) {
                 e.printStackTrace();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         },(watcher)->{
             //循环添加 watcher 对象
            try {
                client.exists(path,watcher);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
        //System.out.println(exists==null);
        TimeUnit.DAYS.sleep(1);
    }


    /**
     * 测试 exists 方法
     * @path path 需要查看节点的 path，如果路径错误，不会抛出异常
     * @return Stat 返回节点的详细信息，stat结构
     */
    @Test
    public void testExists()throws Exception{
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
    public void testGetData()throws Exception{
        String path = "/servers";
        //byte[] data = client.getData(path, false, null);
        //Stat stat = new Stat(1L,1L,1L,1L,1,1,1,1L,1,1,1L);
        Stat stat = new Stat();
        byte[] data = client.getData(path, false, stat);
        System.out.println(new String(data));
        System.out.println(stat.toString());
    }

    /**
     * @param path  如果路径不存在，则 KeeperErrorCode = NoNode for /servers
     * @param watch 是否监控
     * @return child 该路径下的所有子节点
     *
     * eg  /servers/s1/s1
     *     /servers/s2
     *     /servers/s3
     *
     *     child 只有 s1 s2 s3 没有
     *     只能打印直接子路径  /s1/s1则没有
     */
    @Test
    public void testGetChild()throws Exception{
        String path = "/servers";
        List<String> child = client.getChildren(path,false);
        child.forEach(System.out::println);
    }


    /**
     * @param path 路径
     * @deprecated data 数据
     * @param version 版本号，对应zk stat中的dataVersion，
     *                只有相等才会更新，否则 KeeperErrorCode = BadVersion for /servers/s1
     *
     *                这里需要先获取对应节点信息，然后进行更新，可以与原值进行比较，
     *                在这里使用了CAS的思想，使用较版本号比较，可以完成原子性，防止获取数据号，数据又被其他节点更新。
     */
    @Test
    public void testSetData()throws Exception{
        String path="/servers/s1";
        String data="data2";
        int version = 0;
        Stat stat = new Stat();
        client.getData(path,false,stat);
        Stat update = client.setData(path, data.getBytes(), stat.getVersion());
        System.out.println(update.toString());
    }

    @Test
    public void testDelete()throws Exception{
        String path ="/servers/s3";
        Stat stat = new Stat();
        client.getData(path,false,stat);
        client.delete(path,stat.getVersion());
    }



}
