package com.jay.rabbit.springboot.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 2 * @Author: Roger
 * 3 * @Date: 2018/9/16 0016 23:58
 * 4
 */
@Getter
@Setter
public class User {

   private int id;

   private String account;

   private String password;

   private String nickname;

   private String openid;

    public User(int id, String account, String password, String nickname, String openid) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.nickname = nickname;
        this.openid = openid;
    }

    public User() {
    }
}
