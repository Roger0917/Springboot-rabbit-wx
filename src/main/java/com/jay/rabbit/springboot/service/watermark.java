package com.jay.rabbit.springboot.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.awt.*;
import java.io.File;

/**
 * 2 * @Author: Roger
 * 3 * @Date: 2018/9/18 0018 22:12
 * 4
 */
public interface watermark {

    public static final String MARK_TEXT = "小迪啊";

    public static final int FONT_STYLE = Font.BOLD;
    public static final String FONT_NAME = "微软雅黑";
    public static final int FONT_SIZE = 120;
    public static final Color FONT_COLOR = Color.BLACK;

    public static final int X = 10;
    public static final int Y = 10;

    public static float ALPHA = 0.3F;
    public static final String LOGO = "logo.png";

    public String watermark(File file,String filename, String uploadPath, String realUploadPath);
    public String imagemark(File file,String filename, String uploadPath, String realUploadPath);
}
