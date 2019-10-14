package top.ryan1h.springcloud.template.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.ryan1h.springcloud.template.common.util.string.RandomUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

public class CaptchaFacotry {
    // 定义图片的width
    private static final int WIDTH = 100;
    // 定义图片的height
    private static final int HEIGHT = 42;
    // 字体高度
    private static final int FONT_HEIGHT = HEIGHT / 3 + HEIGHT / 4;
    // 干扰线所在的区域个数
    private static final int AREA_COUNT = 5;
    // 每个区域内干扰线的条数
    private static final int LINE_COUNT = 5;
    private static final Logger logger = LoggerFactory.getLogger(CaptchaFacotry.class);

    /**
     * 生成一个验证码图片
     *
     * @param codeCount   验证码个数
     * @param captchaPath 图片路径
     * @param captchaName 图片名
     * @return 随机生成的codeCount位验证码
     */
    public static StringBuffer generateCodeAndPic(int codeCount, String captchaPath, String captchaName) throws IOException {
        // 定义BufferedImage对象
        BufferedImage buffImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics gd = buffImg.getGraphics();
        // 将图像背景填充为白色
        gd.setColor(Color.WHITE);
        // 画一个矩形
        gd.fillRect(0, 0, WIDTH, HEIGHT);
        // 设置边框颜色
        gd.setColor(Color.BLACK);
        // 画矩形边框
        gd.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
        // 创建字体,字体的大小由图片的高度来定。
        Font font = new Font("Microsoft JhengHei", Font.BOLD, FONT_HEIGHT);
        // 设置生成的验证码使用的字体。
        gd.setFont(font);
        // 创建一个随机数生成器类
        Random random = new Random();
        // 随机产生干扰线
        int x0, y0;
        int x1, y1;
        for (int i = 0; i < AREA_COUNT; i++) {
            for (int j = 0; j < LINE_COUNT; j++) {
                x0 = WIDTH / AREA_COUNT * i + random.nextInt(WIDTH / AREA_COUNT);
                y0 = random.nextInt(HEIGHT / 2);
                x1 = WIDTH / AREA_COUNT * i + random.nextInt(WIDTH / AREA_COUNT);
                y1 = HEIGHT / 2 + random.nextInt(HEIGHT / 2);
                gd.drawLine(x0, y0, x1, y1);
            }
        }
        StringBuffer captchaCode = new StringBuffer();
        // 随机产生codeCount位验证码。
        for (int i = 0; i < codeCount; i++) {
            // 随机产生每一位验证码的颜色(R,G,B)
            gd.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            //随机生成数字或字母
            if (new Random().nextBoolean()) {
                char charCode = RandomUtils.generateChar();
                gd.drawString(String.valueOf(charCode), i * WIDTH / 4 + new Random().nextInt(WIDTH / 8), HEIGHT / 2 + new Random().nextInt(HEIGHT / 3));
                captchaCode.append(charCode);
            } else {
                int numCode = new Random().nextInt(9);
                gd.drawString(String.valueOf(numCode), i * WIDTH / 4 + new Random().nextInt(WIDTH / 8), HEIGHT / 2 + new Random().nextInt(HEIGHT / 3));
                captchaCode.append(numCode);
            }
        }
        // 创建文件输出流对象
        try (OutputStream out = new FileOutputStream(captchaPath + captchaName);) {
            // 生成验证码文件
            ImageIO.write((RenderedImage) buffImg, "jpeg", out);
        } catch (IOException e) {
            logger.error("生成验证码文件失败=>" + e.getMessage());
            throw e;
        }
        return captchaCode;
    }

    public static void main(String[] args) {
       /* //验证码目录
        String captchaPath = PathUtils.getWebappPath() + CommonUtils.SEPARATOR + "resources" + CommonUtils.SEPARATOR + "static" + CommonUtils.SEPARATOR + "images" + CommonUtils.SEPARATOR + CaptchaConstant.CAPTCHA_DIR_NAME + CommonUtils.SEPARATOR;
        //验证码图片名字
        String captchaName = RandomUtils.generateLengthString(11) + ".jpg";
        try {
            generateCodeAndPic(4, captchaPath, captchaName);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }*/
    }
}