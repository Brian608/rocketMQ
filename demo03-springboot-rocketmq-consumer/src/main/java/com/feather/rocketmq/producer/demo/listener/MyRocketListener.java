package com.feather.rocketmq.producer.demo.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq.demo.listener
 * @className: MyRocketListner
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/8/30 18:51
 * @version: 1.0
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "tp_springboot_03",consumerGroup = "consumer_grp_03")
public class MyRocketListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String message) {
        log.info("接受到的消息:{}",message);
    }
}
