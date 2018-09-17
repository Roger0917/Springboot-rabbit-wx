package com.jay.rabbit.springboot.producer;

import com.jay.rabbit.springboot.entity.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.Correlation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 2 * @Author: Roger
 * 3 * @Date: 2018/9/11 0011 19:34
 * 4
 */
@Component
public class OrderSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendOrder(Order order) throws Exception{

        CorrelationData data = new CorrelationData(order.getMessageId());

        rabbitTemplate.convertAndSend(
                "order-exchange",
                "order.abcd",
                order,  //消息体内容
                data);

    }
}
