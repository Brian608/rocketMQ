package com.feather.rocketmq;

import com.feather.rocketmq.demo.RocketMqProducerApplication;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @projectName: rocketMQ
 * @package: com.feather.rocketmq
 * @className: MyRocketMqProducerTest
 * @author: feather(杜雪松)
 * @description: TODO
 * @since: 2022/8/30 18:38
 * @version: 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RocketMqProducerApplication.class})
public class MyRocketMqProducerTest {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public  void  testSendMessage(){
        this.rocketMQTemplate.convertAndSend("tp_springboot_01","springboot :hello rocketmq feather");

    }
}
