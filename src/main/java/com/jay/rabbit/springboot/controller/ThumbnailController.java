package com.jay.rabbit.springboot.controller;

import com.jay.rabbit.springboot.service.UploadService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * 2 * @Author: Roger
 * 3 * @Date: 2018/9/18 0018 0:11
 * 4
 */
@Slf4j
@Controller
@RequestMapping("/upload")
public class ThumbnailController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping("/upload")
    public ModelAndView upload(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload");
        return modelAndView;
    }

    @PostMapping("/thumb")
    public ModelAndView thumbnail(@RequestParam("img")CommonsMultipartFile file, HttpSession session){
        String uploadPath = "/img";
        String realuploadPath = "I:\\molong_luojie\\springboot-producer";
        log.info("realuploadPath"+realuploadPath);

        String imgUrl = uploadService.uploadImage(file,uploadPath,realuploadPath);
        String thumImageUrl = uploadService.uploadthumbnailImage(file,uploadPath,realuploadPath);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("imageURL",imgUrl);
        modelAndView.addObject("thumImageURL",thumImageUrl);
        modelAndView.setViewName("thumb");
        return modelAndView;
    }
}
