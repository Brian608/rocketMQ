package com.feather.rocketmq.demo.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq.demo.consumer
 * @className: MyConsumer
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/9/1 19:07
 * @version: 1.0
 */
public class MyConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("consumer_grp_09_01");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.setMaxReconsumeTimes(1);
        consumer.subscribe("tp_demo_09","*");
        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt msg : msgs) {
                    System.out.println(msg);
                }
                return null;
            }
        });
        consumer.start();
    }
}
