package com.jay.rabbit.springboot.service.impl;

import com.jay.rabbit.springboot.service.watermark;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * 2 * @Author: Roger
 * 3 * @Date: 2018/9/18 0018 0:24
 * 4
 */
@Service
@Slf4j
public class UploadService implements watermark {

    public static final int WIDTH = 100;
    public static final int HEIGHT = 100;

    public String uploadImage(File image,String imageFileName,String uploadPath,String realUploadPath){

        InputStream is = null;
        OutputStream os = null;

        try{
            is = new FileInputStream(image);
            os = new FileOutputStream(realUploadPath+"\\"+imageFileName);

            byte[]buffer = new byte[1024];
            int len = 0;

            while ((len = is.read(buffer))>0){
                os.write(buffer);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(is!=null){
                try {
                    is.close();
                }catch (Exception e2){
                    e2.printStackTrace();
                }
            }
            if (os!=null){
                try {
                    os.close();
                }catch (Exception e3){
                    e3.printStackTrace();
                }
            }
        }

        return uploadPath+"\\"+imageFileName;
    }

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

    @Override
    public String watermark(File file,String filename, String uploadPath, String realUploadPath){

        String logoFileName = "logo_"+ filename;
        OutputStream out = null;

        try{
            //1.创建图片缓存对象
            Image image = ImageIO.read(file);

            int width = image.getWidth(null);
            int height = image.getHeight(null);

            BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

            //2.创建绘图工具对象
            Graphics2D g = bufferedImage.createGraphics();
            //3.使用绘图工具对象将原图绘制到缓存图片对象
            g.drawImage(image,0,0,width,height,null);

            g.setFont(new Font(FONT_NAME,FONT_STYLE,FONT_SIZE));
            g.setColor(FONT_COLOR);

            //计算文字水印的宽高
            int width1 = FONT_SIZE*getTextLength(MARK_TEXT);
            int height1 = FONT_SIZE;

            int widthDiff = width - width1;
            int heightDiff = height - height1;

            int x = X;
            int y= Y;

            if(x>widthDiff){
                x = widthDiff;
            }
            if (y>heightDiff){
                y = heightDiff;
            }
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,ALPHA));

            g.drawString(MARK_TEXT,x,y+FONT_SIZE);
            g.dispose();

            out = new FileOutputStream(realUploadPath+"\\"+logoFileName);
            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(out);
            en.encode(bufferedImage);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out!=null){
                try {
                    out.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return uploadPath+"\\"+logoFileName;
    }

    @Override
    public String imagemark(File file, String filename, String uploadPath, String realUploadPath) {
        String logoFileName = "logo_"+ filename;
        String logoPath = realUploadPath+"\\logo\\"+LOGO;
        log.info(logoPath);
        OutputStream out = null;

        try{
            //1.创建图片缓存对象
            Image image = ImageIO.read(file);

            int width = image.getWidth(null);
            int height = image.getHeight(null);

            BufferedImage bufferedImage = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);

            //2.创建绘图工具对象
            Graphics2D g = bufferedImage.createGraphics();
            //3.使用绘图工具对象将原图绘制到缓存图片对象
            g.drawImage(image,0,0,width,height,null);

            File logo = new File(logoPath);
            Image imageLogo =  ImageIO.read(logo);
            int width1 = imageLogo.getWidth(null);
            int height1 = imageLogo.getHeight(null);

            int widthDiff = width - width1;
            int heightDiff = height - height1;

            int x = X;
            int y= Y;

            if(x>widthDiff){
                x = widthDiff;
            }
            if (y>heightDiff){
                y = heightDiff;
            }
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,ALPHA));

            g.drawImage(imageLogo,x,y,null);
            g.dispose();

            out = new FileOutputStream(realUploadPath+"\\"+logoFileName);
            JPEGImageEncoder en = JPEGCodec.createJPEGEncoder(out);
            en.encode(bufferedImage);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out!=null){
                try {
                    out.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return uploadPath+"\\"+logoFileName;
    }

    public int getTextLength(String text){
        int length = text.length();

        for (int i=0;i<text.length();i++){
            String s = String.valueOf(text.charAt(i));
            if (s.getBytes().length>1){
                length++;
            }
        }

        length = length%2==0?length/2:length/2+1;
        return length;
    }
}
