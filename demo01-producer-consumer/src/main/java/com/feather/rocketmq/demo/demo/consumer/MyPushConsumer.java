package com.feather.rocketmq.demo.demo.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq.demo.consumer
 * @className: MyPushConsumer
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/8/30 17:42
 * @version: 1.0
 */
public class MyPushConsumer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("tp_dem0_02");

        consumer.setNamesrvAddr("localhost:9876");

        //订阅主题
        consumer.subscribe("tp_dem0_02","*");

        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                MessageQueue messageQueue = consumeConcurrentlyContext.getMessageQueue();
                System.out.println(messageQueue);
                for (MessageExt msg : msgs) {
                    System.out.println(new String(msg.getBody(), StandardCharsets.UTF_8));
                }
                //消费成功
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();

        Thread.sleep(30000);

        consumer.shutdown();


    }
}
