package com.feather.rocketmq.producer.demo.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq.demo.producer
 * @className: MyProducer1
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/8/31 15:33
 * @version: 1.0
 */
public class MyProducer1 {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer=new DefaultMQProducer("producer_grp_06_01");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        Message message=new Message("tp_demo_07","hello rocketmq feather".getBytes(StandardCharsets.UTF_8));

        SendResult sendResult = producer.send(message, new MessageQueue("tp_demo_07", "localhost", 5));

        System.out.println(sendResult);
        producer.shutdown();

    }
}
