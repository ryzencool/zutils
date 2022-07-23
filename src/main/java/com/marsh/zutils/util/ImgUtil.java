package com.marsh.zutils.util;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验证码图片工具类
 *
 * @author lucky.zhou
 */
@Slf4j
public class ImgUtil {

    public static final int WIDTH = 120;
    public static final int HEIGHT = 30;

    private ImgUtil() {

    }

    public static BufferedImage generateCodeImg(String imgCode) {
        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.WHITE);
        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
        g.setColor(Color.GREEN);
        for (int i = 0; i < 5; i++) {
            int x1 = new Random().nextInt(WIDTH);
            int y1 = new Random().nextInt(HEIGHT);
            int x2 = new Random().nextInt(WIDTH);
            int y2 = new Random().nextInt(HEIGHT);
            g.drawLine(x1, y1, x2, y2);
        }
        drawImgCode((Graphics2D) g, imgCode);
        return bi;
    }

    private static void drawImgCode(Graphics2D g, String imgCode) {
        int x = 5;
        String ch = "";
        for (int i = 0; i < imgCode.length(); i++) {
            int degree = new Random().nextInt() % 30;
            ch = imgCode.charAt(i) + "";
            g.rotate(degree * Math.PI / 180, x, 20);
            g.setFont(new Font(ch, 10, 20));
            g.drawString(ch, x, 20);
            g.rotate(-degree * Math.PI / 180, x, 20);
            x += 30;
        }
    }

    public static void thumbnailWH(File img, int width, int height, String outPath) {
        try {
            BufferedImage bi = ImageIO.read(img);
            double srcWidth = bi.getWidth(); // 源图宽度
            double srcHeight = bi.getHeight(); // 源图高度
            double scale = 1;
            if (width < srcWidth) {
                if (width > 0) {
                    scale = width / srcWidth;
                }
                if (height > 0) {
                    scale = height / srcHeight;
                }
                if (width > 0 && height > 0) {
                    scale = height / srcHeight < width / srcWidth ? height / srcHeight
                            : width / srcWidth;
                }
            }

            thumbnail(img, (int) (srcWidth * scale), (int) (srcHeight * scale), outPath);
        } catch (IOException e) {
            log.error("宽高比例压缩图片失败，异常：{}", e.getMessage(), e);
        }
    }


    public static void thumbnail(File img, int width, int height, String outPath) {
        OutputStream out = null;
        try {
            BufferedImage BI = ImageIO.read(img);
            Image image = BI.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height,
                    BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            File targetFile = new File(outPath);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(outPath);
            g.setColor(Color.RED);
            g.drawImage(image, 0, 0, null);
            g.dispose();
            ImageIO.write(tag, "JPEG", out);
        } catch (IOException e) {
            log.error("压缩图片失败，异常：{}", e.getMessage(), e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    log.error("压缩图片关闭流失败，异常：{}", e.getMessage(), e);
                }
            }
        }
    }
}
