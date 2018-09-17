package com.jay.rabbit.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.jay.rabbit.springboot.entity.User;
import com.jay.rabbit.springboot.util.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Action;
import java.io.IOException;
import java.util.List;

/**
 * 2 * @Author: Roger
 * 3 * @Date: 2018/9/16 0016 0:50
 * 4
 */
@Slf4j
@Controller
@RequestMapping("/callBack")
public class CallBackController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/do")
    public ModelAndView doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        String code = req.getParameter("code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + AuthUtil.APPID
                + "&secret=" + AuthUtil.APPSECRET
                + "&code=" + code
                + "&grant_type=authorization_code";
        JSONObject jsonObject = AuthUtil.doGetJson(url);
        String openid = jsonObject.getString("openid");
        String token = jsonObject.getString("access_token");
        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token
                + "&openid=" + openid
                + "&lang=zh_CN";
        JSONObject userInfo = AuthUtil.doGetJson(infoUrl);
        System.out.println(userInfo);
        log.info(userInfo.getString("nickname"));
        log.info(userInfo.getString("headimgurl"));

        /*List<User> list = jdbcTemplate.query("select * from user WHERE openid=?", new Object[]{10}, new BeanPropertyRowMapper<>(User.class));
        if (list != null && list.size() > 0) {
            for (User user : list) {
                log.info(user.getNickname());
            }
        }*/
       /* String sql = "update user set openid=? where account=? and password=?";
        log.info(jdbcTemplate.update(sql,30,"test2",321)+"");*/

        //1.使用微信用户信息直接登录,无需注册和绑定
        /*ModelAndView mad = new ModelAndView("index");
        mad.addObject("info",userInfo);
        return mad;*/
        //2.将微信与当前系统的账号进行绑定
        String nickname = getNickName(openid);
        if(!"".equals(nickname)){
            //绑定成功
            ModelAndView mad2 = new ModelAndView("index2");
            mad2.addObject("nickname",nickname);
            return mad2;
        }else {
            ModelAndView mad3 = new ModelAndView("login");
            mad3.addObject("openid",openid);
            return mad3;
        }
    }


    public String getNickName(String openid) {
        String sql = "select * from user WHERE openid=?";
        List<User> list = jdbcTemplate.query(sql, new Object[]{openid}, new BeanPropertyRowMapper<>(User.class));
        if (list != null && list.size() > 0) {
            for (User user : list) {
                log.info(user.getNickname());
                return user.getNickname();
            }
        }
        return "暂无昵称";
    }

    public void upUser(String openid,String account,String password){
        String sql = "update user set openid=? where account=? and password=?";
        log.info(jdbcTemplate.update(sql,openid,account,password)+"");
    }
}

