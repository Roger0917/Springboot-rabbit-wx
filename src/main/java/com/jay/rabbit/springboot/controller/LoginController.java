package com.jay.rabbit.springboot.controller;

import com.jay.rabbit.springboot.util.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 2 * @Author: Roger
 * 3 * @Date: 2018/9/16 0016 0:10
 * 4
 */
@Controller
@RequestMapping("/wxLogin")
@Slf4j
public class LoginController {

    @GetMapping("/login")
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String backUrl = "http://220h7k9237.51mypc.cn:14279/callBack/do";
        log.info(backUrl);
        log.info(URLEncoder.encode(backUrl));
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+ AuthUtil.APPID
                + "&redirect_uri="+ URLEncoder.encode(backUrl)
                + "&response_type=code"
                + "&scope=snsapi_userinfo"
                + "&state=STATE#wechat_redirect";
        response.sendRedirect(url);
    }
}
