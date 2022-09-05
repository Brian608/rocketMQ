package com.feather.rocketmq.producer.demo.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq.demo.producer
 * @className: MyProducer
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/9/2 07:34
 * @version: 1.0
 */
public class MyProducer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        DefaultMQProducer producer=new DefaultMQProducer("producer_grp_11_01");

        producer.setNamesrvAddr("localhost:9876");

        producer.start();
        //获取指定主题的MQ列表
        List<MessageQueue> messageQueues = producer.fetchPublishMessageQueues("tp_demo_11");

        Message message=null;
        MessageQueue messageQueue=null;

        for (int i = 0; i <10 ; i++) {
            //采用轮训的方式指定MQ，发送订单消息，保证同一个订单的消息按顺序发送到同一个MQ
            messageQueue=messageQueues.get(i%8);
                message =new Message("tp_demo_11",("hello rocketmq order create -"+i).getBytes(StandardCharsets.UTF_8));
                producer.send(message,messageQueue);

            message =new Message("tp_demo_11",("hello rocketmq order pay -"+i).getBytes(StandardCharsets.UTF_8));
            producer.send(message,messageQueue);

            message =new Message("tp_demo_11",("hello rocketmq order ship -"+i).getBytes(StandardCharsets.UTF_8));
            producer.send(message,messageQueue);

        }

        producer.shutdown();

    }
}
