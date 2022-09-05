package com.feather.rocketmq.producer.demo.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq.demo.producer
 * @className: MyProducer
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/9/1 22:11
 * @version: 1.0
 */
public class MyProducer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer=new DefaultMQProducer("producer_grp_10_01");

        producer.setNamesrvAddr("localhost:9876");

        producer.start();

        Message message=null;

        for (int i = 0; i <20 ; i++) {
            message=new Message("tp_demo_10",("hello rocketmq feather"+i).getBytes(StandardCharsets.UTF_8));
            message.setDelayTimeLevel(i);
            producer.send(message);
        }

        producer.shutdown();
    }
}
