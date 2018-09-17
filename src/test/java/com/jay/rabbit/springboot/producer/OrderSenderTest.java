package com.jay.rabbit.springboot.producer;

import com.jay.rabbit.springboot.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.Assert.*;

/**
 * 2 * @Author: Roger
 * 3 * @Date: 2018/9/11 0011 19:52
 * 4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderSenderTest {

    @Autowired
    private OrderSender orderSender;

    @Test
    public void sendOrder() throws Exception {
        Order order = new Order("2018091100001","测试订单1",System.currentTimeMillis()+"$"+ UUID.randomUUID().toString());
        orderSender.sendOrder(order);
    }

}