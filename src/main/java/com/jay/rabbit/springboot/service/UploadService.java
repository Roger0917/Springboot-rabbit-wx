package com.jay.rabbit.springboot.service;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 2 * @Author: Roger
 * 3 * @Date: 2018/9/18 0018 0:24
 * 4
 */
@Service
@Slf4j
public class UploadService {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;

    public String uploadImage(CommonsMultipartFile file,String uploadPath,String realUploadPath){
        InputStream in = null;
        OutputStream out = null;

        try{
            in = file.getInputStream();
            String des = realUploadPath+"\\"+file.getOriginalFilename();
            log.info(realUploadPath+file.getOriginalFilename());
            log.info(des);
            out = new FileOutputStream(des);

            byte[]buffer = new byte[1024];
            int len = 0;

            while ((len = in.read(buffer))>0){
                out.write(buffer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(in!=null){
                try {
                    in.close();
                }catch (Exception e2){
                    e2.printStackTrace();
                }
            }
            if (out!=null){
                try {
                    out.close();
                }catch (Exception e3){
                    e3.printStackTrace();
                }
            }
        }

        return uploadPath+"\\"+file.getOriginalFilename();
    }

    public String uploadthumbnailImage(CommonsMultipartFile file,String uploadPath,String realUploadPath){
        try {
            String des = realUploadPath+"\\thum_"+file.getOriginalFilename();
            log.info(realUploadPath+file.getOriginalFilename());
            log.info(des);
            Thumbnails.of(file.getInputStream()).size(WIDTH,HEIGHT).toFile(des);
        }catch (Exception e){
            e.printStackTrace();
        }

        return uploadPath+"\\thum_"+ file.getOriginalFilename();
    }
}
