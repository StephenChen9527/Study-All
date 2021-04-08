package com.nullbugs.mq.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TestProducter {

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);

        factory.setVirtualHost("/");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //String queue, 队列名称
        // boolean durable, 是否持久化
        // boolean exclusive,  是否 独占 当关闭connection 的时候，是否删除队列
        // boolean autoDelete,  没有消费者时，是否自动删除
        // Map<String, Object> arguments
        channel.queueDeclare();

        //String exchange,  名称
        //        BuiltinExchangeType type,  类型
                //


        //        boolean durable,   是否持久化
        //        boolean autoDelete, 自动删除
        //        boolean internal, 一般 false  内部使用  插件开发？？
        //        Map<String, Object> arguments)
        //channel.exchangeDeclare();


        
        //String exchange,  交换机名称， 简单模式  使用  空字符串 ""
        // String routingKey,  路由key   简单模式就是 队列名称
        // boolean mandatory,
        // boolean immediate,
        // BasicProperties props,
        // byte[] body
        //channel.basicPublish("");

    }
}
