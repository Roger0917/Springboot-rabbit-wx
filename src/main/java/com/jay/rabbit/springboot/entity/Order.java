package com.jay.rabbit.springboot.entity;

import java.io.Serializable;

/**
 * 2 * @Author: Roger
 * 3 * @Date: 2018/9/11 0011 19:19
 * 4
 */
public class Order implements Serializable {

    private static final long serialVersionUID = -4289210520467088331L;
    private String id;

    private String name;

    private String messageId; //存储消息发送的唯一标识

    public Order(String id, String name, String messageId) {
        this.id = id;
        this.name = name;
        this.messageId = messageId;
    }

    public Order() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
