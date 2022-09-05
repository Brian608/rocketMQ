package com.feather.rocketmq.producer.demo.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq.demo.consumer
 * @className: MyConsumerOrderly
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/8/31 17:07
 * @version: 1.0
 */
public class MyConsumerOrderly {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("consumer_08_01");

        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe("tp_demo_08","*");

        //一次获取一条消息
        consumer.setConsumeMessageBatchMaxSize(1);

        consumer.setMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt msg : msgs) {
                    System.out.println(msg.getMsgId()+"\t"+msg.getQueueId()+"\t"+new String(msg.getBody()));
                }
                //引发重试
                return null;
            }
        });
    }
}
