package com.jay.rabbit.springboot.config.mvc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * 2 * @Author: Roger
 * 3 * @Date: 2018/9/17 0017 23:36
 * 4
 */
@Configuration
public class MvcConfig {

    @Bean
    public CommonsMultipartResolver createBean(){
        CommonsMultipartResolver cmt = new CommonsMultipartResolver();
        cmt.setDefaultEncoding("utf-8");
        cmt.setMaxUploadSize(10485760000L);
        cmt.setMaxInMemorySize(40960);
        return cmt;
    }
}
