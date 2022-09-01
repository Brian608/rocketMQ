package com.feather.rocketmq.demo.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq.demo.producer
 * @className: MyProducer
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/8/31 17:21
 * @version: 1.0
 */
public class MyProducer {
    public static void main(String[] args) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        DefaultMQProducer producer=new DefaultMQProducer("grp_producer_08");

        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        Message message=null;

        for (int i = 0; i <10 ; i++) {
            message=new Message("tp_demo_08",("hello rocketmq feather"+i).getBytes(StandardCharsets.UTF_8));
            SendResult sendResult = producer.send(message);

            System.out.println(sendResult.getSendStatus());

        }
        producer.shutdown();
    }
}
