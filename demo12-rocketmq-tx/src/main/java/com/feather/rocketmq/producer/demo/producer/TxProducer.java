package com.feather.rocketmq.producer.demo.producer;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.nio.charset.StandardCharsets;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq.demo
 * @className: TxProducer
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/9/2 09:14
 * @version: 1.0
 */
public class TxProducer {
    public static void main(String[] args) throws MQClientException {
        TransactionListener listener=new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                //当发送事务消息prepare（half） 成功后，调用该方法执行本地事务
                System.out.println("执行本地事务，参数为："+o);
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return LocalTransactionState.COMMIT_MESSAGE;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                //如果没有收到生产者发送的half message的响应，broker发送请求到生产者回查生产者本地事务状态
                //该方法用于获取本地事务执行的状态
                System.out.println("检查本地事务状态："+messageExt);
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        };

        TransactionMQProducer producer=new TransactionMQProducer("tx_producer_grp_12");
        producer.setTransactionListener(listener);
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        Message message=null;
        message=new Message("tp_demo_12",("hello rocketmq tx feather").getBytes(StandardCharsets.UTF_8));
        producer.sendMessageInTransaction(message,"{\"name\":\"feather\"}");
    }
}
