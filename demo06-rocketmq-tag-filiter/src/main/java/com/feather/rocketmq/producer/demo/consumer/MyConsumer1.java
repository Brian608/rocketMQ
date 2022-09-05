package com.feather.rocketmq.producer.demo.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq.demo.consumer
 * @className: MyConsumer1
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/8/31 10:00
 * @version: 1.0
 */
public class MyConsumer1 {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("consumer_grp_06_01");

        consumer.setNamesrvAddr("localhost:9876");

        consumer.subscribe("tp_demo_06","tag-1||tag-0");

        consumer.setMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                MessageQueue messageQueue = consumeConcurrentlyContext.getMessageQueue();
                int queueId = messageQueue.getQueueId();
                String brokerName = messageQueue.getBrokerName();
                String topic = messageQueue.getTopic();
                System.out.println("topic"+topic+"\t"+"queueId:"+queueId+"\t"+"brokerName:"+brokerName);
                msgs.forEach(System.out::println);
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();

    }
}
