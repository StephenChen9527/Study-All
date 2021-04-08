package com.nullbugs.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
// int[]
// 定值
//
public class KafkaTest {


    public static void main(String[] args) {
        //int[] arr = new int[]{1,2,3,4,5,6};
        //System.out.println(look(arr,12));
    }

    public static boolean look(int[] arr , int target){
        Arrays.sort(arr);
        int start = 0;
        int end = arr.length-1;
        while (start<end){
            int sum = arr[start] + arr[end];
            if(sum>target){
                end--;
            }else if(sum<target){
                start++;
            }else {
                return true;
            }
        }
        return false;
    }
    // 源码
    // 虚拟机 优化 高并发
    // 书
    // JVM书  编程思想
    // 其他框架--》深入思想

}
