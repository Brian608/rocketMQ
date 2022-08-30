package com.feather.rocketmq.demo.demo.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq.demo.consumber
 * @className: MyPullConsumer
 * @author: feather(杜雪松)
 * @description: 拉取消息的消费者
 * @since: 2022/8/30 17:20
 * @version: 1.0
 */
public class MyPullConsumer {

    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException, UnsupportedEncodingException {
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("consumer_grp_01");

        consumer.setNamesrvAddr("localhost:9876");

        consumer.start();

        Set<MessageQueue> messageQueueSet = consumer.fetchSubscribeMessageQueues("tp_dem0_01");

        for (MessageQueue  messageQueue:
           messageQueueSet  ) {
            // 第一个参数是MessageQueue对象，代表了当前主题的一个消息队列
            // 第二个参数是一个表达式，对接收的消息按照tag进行过滤
            // 支持"tag1 || tag2 || tag3"或者 "*"类型的写法；null或者"*"表示不对 消息进行tag过滤
            // 第三个参数是消息的偏移量，从这里开始消费
            // 第四个参数表示每次最多拉取多少条消息
            PullResult pullResult = consumer.pull(messageQueue, "*", 0, 10);
            System.out.println("messageQueue:"+messageQueue);
            List<MessageExt> msgFoundList = pullResult.getMsgFoundList();
            if (msgFoundList==null) {
                continue;
            }
            for (MessageExt  messageExt: msgFoundList) {
                System.out.println("消息："+messageExt);
                System.out.println(new String(messageExt.getBody(), StandardCharsets.UTF_8));
            }

        }

        consumer.shutdown();
    }

}
