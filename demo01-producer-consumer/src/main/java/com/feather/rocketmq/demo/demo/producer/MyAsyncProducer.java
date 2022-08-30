package com.feather.rocketmq.demo.demo.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocket.demo.producer
 * @className: MyAsyncProducer
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/8/30 16:45
 * @version: 1.0
 */
public class MyAsyncProducer {
    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException {
        // 在实例化生产者的同时，指定了生产组名称
        DefaultMQProducer mqProducer=new DefaultMQProducer("myproducer_group_01");

        // 指定NameServer的地址
        mqProducer.setNamesrvAddr("localhost:9876");
        // 对生产者进行初始化，
        mqProducer.start();


       for (int i=0;i<10;i++){
           Message message=new Message("tp_dem0_02",("hello  rocketmq  feather"+i).getBytes(StandardCharsets.UTF_8));
         //消息的异步发送
           mqProducer.send(message, new SendCallback() {
               @Override
               public void onSuccess(SendResult sendResult) {
                   System.out.println("发送成功"+sendResult);
               }

               @Override
               public void onException(Throwable throwable) {
                   System.out.println("发送失败"+throwable.getMessage());
               }
           });
       }
        // 由于是异步发送消息，上面循环结束之后，消息可能还没收到broker的响应
        // 如果不sleep一会儿，就报错
       Thread.sleep(10000);
        mqProducer.shutdown();
    }
}
