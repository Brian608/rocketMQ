package com.feather.rocketmq.producer.demo.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq.demo.producer
 * @className: Myproducer
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/8/31 09:50
 * @version: 1.0
 */
public class MyProducer2 {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        DefaultMQProducer producer=new DefaultMQProducer("producer_grp_06");

        producer.setNamesrvAddr("localhost:9876");

        producer.start();

        Message message=null;
        for (int i = 0; i <100 ; i++) {
            message=new Message("tp_demo_06_02",("hello rocketmq feather-"+i).getBytes(StandardCharsets.UTF_8));

            String value="";
            switch (i%3){
                case 0:
                    value="v0";
                    break;
                case 1:
                    value="v1";
                    break;
                default:
                    value="v2";
                    break;
            }
            //给消息设置用户属性
            message.putUserProperty("myKey",value);
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("成功："+sendResult);
                }

                @Override
                public void onException(Throwable throwable) {
                    System.out.println("失败："+throwable.getMessage());
                }
            });
        }

        Thread.sleep(3000);
        producer.shutdown();


    }
}
