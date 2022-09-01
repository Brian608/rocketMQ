package consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq.demo.producer.com.feather.rocketmq.demo.consumer
 * @className: MyConsumer
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/8/31 15:38
 * @version: 1.0
 */
public class MyConsumer {
    public static void main(String[] args) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        DefaultMQPullConsumer consumer=new DefaultMQPullConsumer("consumer_grp_06_01");
        consumer.setNamesrvAddr("localhost:9876");
        consumer.start();

        PullResult pullResult = consumer.pull(new MessageQueue("tp_demo_06", "localhost", 5), "*", 0L, 10);
        System.out.println(pullResult);
        consumer.shutdown();
    }
}
