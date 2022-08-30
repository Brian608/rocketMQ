package com.feather.rocketmq.demo.demo.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocket.demo.producer
 * @className: MyProducer
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/8/30 16:18
 * @version: 1.0
 */
public class MyProducer {
    public static void main(String[] args) throws UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException, MQClientException {

        // 在实例化生产者的同时，指定了生产组名称
        DefaultMQProducer mqProducer=new DefaultMQProducer("myproducer_group_01");

        // 指定NameServer的地址
        mqProducer.setNamesrvAddr("localhost:9876");
        // 对生产者进行初始化，
        mqProducer.start();

        // 创建消息，第一个参数是主题名称，第二个参数是消息内容
        Message message=new Message("tp_dem0_01","hello  rocketmq feather".getBytes(RemotingHelper.DEFAULT_CHARSET));

        SendResult result = mqProducer.send(message);

        System.out.println("发送消息结果："+result);
        //关闭生产者
        mqProducer.shutdown();


    }

}
