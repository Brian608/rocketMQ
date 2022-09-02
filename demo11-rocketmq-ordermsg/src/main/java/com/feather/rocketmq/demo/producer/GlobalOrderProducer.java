package com.feather.rocketmq.demo.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq.demo.producer
 * @className: GlobalOrderProducer
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/9/2 08:14
 * @version: 1.0
 */
public class GlobalOrderProducer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer=new DefaultMQProducer("producer_grp_11_02");
        producer.setNamesrvAddr("localhost:9876");

        producer.start();

        Message message=null;
        for (int i = 0; i <100 ; i++) {
            message=new Message("tp_demo_11_02",("hello rocketmq feather"+i).getBytes(StandardCharsets.UTF_8));
            producer.send(message);
            
        }
        producer.shutdown();
    }
}
