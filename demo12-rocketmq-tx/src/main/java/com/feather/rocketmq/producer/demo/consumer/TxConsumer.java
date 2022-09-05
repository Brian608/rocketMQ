package com.feather.rocketmq.producer.demo.consumer;

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
 * @className: TxConsumer
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/9/2 09:28
 * @version: 1.0
 */
public class TxConsumer {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer=new DefaultMQPushConsumer("Txconsumer_grp_12_01");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.subscribe("tp_demo_12","*");

        consumer.setMessageListener((MessageListenerConcurrently) (msgs, consumeConcurrentlyContext) -> {
            for (MessageExt msg : msgs) {
                System.out.println(new String(msg.getBody()));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
    }
}
