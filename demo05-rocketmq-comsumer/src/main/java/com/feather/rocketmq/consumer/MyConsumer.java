package com.feather.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.util.Set;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq.consumer
 * @className: MyConsumer
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/8/30 21:55
 * @version: 1.0
 */
public class MyConsumer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {
        //消息的拉取
        DefaultMQPullConsumer pullConsumer=new DefaultMQPullConsumer();

        Set<MessageQueue> messageQueues = pullConsumer.fetchSubscribeMessageQueues("tp_demo_05");

        for (MessageQueue messageQueue : messageQueues) {
            //指定消息队列，指定过滤消息的表达式，偏移量，单次最大拉取消息的个数
            pullConsumer.pull(messageQueue,"TagA||tagB..",0L,10);
        }

        //消息的推送
        DefaultMQPushConsumer pushConsumer=new DefaultMQPushConsumer();

        //广播
        pushConsumer.setMessageModel(MessageModel.BROADCASTING);
        //集群
        pushConsumer.setMessageModel(MessageModel.CLUSTERING);


        //设置消费者的线程数
        pushConsumer.setConsumeThreadMin(1);
        pushConsumer.setConsumeThreadMax(10);

        //订阅主题
        //subExpression 过滤消息
        //tagA ｜tagB ｜｜ tagC
        pushConsumer.subscribe("tp_demo_05","*");

        //设置消息批处理的一个批次中消息的最大个数
        pushConsumer.setConsumeMessageBatchMaxSize(10);

        pushConsumer.start();


    }
}
