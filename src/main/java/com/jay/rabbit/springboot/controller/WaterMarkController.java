package com.jay.rabbit.springboot.controller;

import com.jay.rabbit.springboot.service.impl.UploadService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;

/**
 * 2 * @Author: Roger
 * 3 * @Date: 2018/9/18 0018 18:30
 * 4
 */
@Slf4j
@Controller
@RequestMapping("/upload")
public class WaterMarkController {

    public static final String uploadPath = "\\img";
    public static final String realuploadPath = "I:\\molong_luojie\\springboot-producer"+uploadPath;

    @Autowired
    private UploadService uploadService;

    @RequestMapping("/water")
    public ModelAndView water(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("upload2");
        return modelAndView;
    }

    @RequestMapping("/water_text")
    public ModelAndView waterText(@RequestParam("img")CommonsMultipartFile file){
        log.info("realuploadPath"+realuploadPath);

        File file2 = getFile(file);
        String imgUrl = uploadService.uploadImage(file2,file.getOriginalFilename(),uploadPath,realuploadPath);
        String watertextUrl = uploadService.watermark(file2,file.getOriginalFilename(),uploadPath,realuploadPath);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("imageURL",imgUrl);
        modelAndView.addObject("waterTextURL",watertextUrl);
        modelAndView.setViewName("water_text");
        return modelAndView;
    }

    @RequestMapping("/water_img")
    public ModelAndView waterImg(@RequestParam("img")CommonsMultipartFile file){
        log.info("realuploadPath"+realuploadPath);

        File file2 = getFile(file);
        log.info("file2.name"+file2.getName()+"file.namegetOriginalFilename"+file.getOriginalFilename());
        String imgUrl = uploadService.uploadImage(file2,file.getOriginalFilename(),uploadPath,realuploadPath);
        String watertextUrl = uploadService.imagemark(file2,file.getOriginalFilename(),uploadPath,realuploadPath);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("imageURL",imgUrl);
        modelAndView.addObject("waterImgURL",watertextUrl);
        modelAndView.setViewName("water_img");
        return modelAndView;
    }


    public File getFile(@RequestParam("cmfFile") CommonsMultipartFile cmfFile) {
        CommonsMultipartFile commonsMultipartFile = (CommonsMultipartFile) cmfFile;
        DiskFileItem diskFileItem = (DiskFileItem) commonsMultipartFile.getFileItem();
        File file = diskFileItem.getStoreLocation();
        return file;
    }


}
