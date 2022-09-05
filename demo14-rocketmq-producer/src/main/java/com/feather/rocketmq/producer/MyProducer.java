package com.feather.rocketmq.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq
 * @className: producer
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/9/3 17:56
 * @version: 1.0
 */
public class MyProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer producer=new DefaultMQProducer("producer_grp_14");

        producer.setNamesrvAddr("localhost:9876");

        producer.start();

        Message message=new Message("tp_demo_14","hello rocketmq feather".getBytes(StandardCharsets.UTF_8));

        message.setTags("tag1");
        //keys用于建立索引的时候，hash取摸将消息的索引放到slotTable 的一个slot链表中
        message.setKeys("oid-2022-09-03_88888");

        producer.send(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

            }

            @Override
            public void onException(Throwable throwable) {

            }
        });
        producer.shutdown();
    }
}
