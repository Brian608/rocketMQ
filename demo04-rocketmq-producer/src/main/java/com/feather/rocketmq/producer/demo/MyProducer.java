package com.feather.rocketmq.producer.demo;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq.demo
 * @className: MyProducer
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/8/30 20:40
 * @version: 1.0
 */
public class MyProducer {
    public static void main(String[] args) throws MQClientException, MQBrokerException, RemotingException, InterruptedException {

        //该producer是线程安全的，可以多线程使用
        DefaultMQProducer producer=new DefaultMQProducer("producer_grp_04");

        producer.setInstanceName("producer_grp_04_01");

        //设置同步发送重试的次数
        producer.setRetryTimesWhenSendFailed(2);
        //设置异步发送重试的次数
        producer.setRetryTimesWhenSendAsyncFailed(2);

        producer.setNamesrvAddr("localhost:9876");

        producer.start();

        Message message =new Message("tp_demo_04","hello rocketMQ feather".getBytes(StandardCharsets.UTF_8));

        //同步发送消息，如果消息发送失败，则按照setRetryTimesWhenSendFailed设置的次数进行重试
        //broker中可能会有重复的消息，由运用的开发者处理
        SendResult sendResult = producer.send(message);

        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

                //发送成功
            }

            @Override
            public void onException(Throwable throwable) {

                //发送失败的处理逻辑
                //重试次数耗尽，发生的异常

            }
        });
        SendStatus sendStatus = sendResult.getSendStatus();


    }
}
