package instrument;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class TranslucentImage {

    public static boolean Translucent(File image, File save, int alpha) {
        BufferedImage imageOpen = null, imageSave = null;// 原始图像，保存图像
        int width, height, rgb;// 图片的宽度,高度，临时存放像素点的颜色值
        Color color;// 用于计算颜色值的Color对象
        try {
            imageOpen = ImageIO.read(image);// 打开图像
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        width = imageOpen.getWidth();// 获得图像宽度
        height = imageOpen.getHeight();// 获得图像高度
        imageSave = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        // 新建一个空白的图像
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                rgb = imageOpen.getRGB(x, y);// 获得颜色值
                color = new Color(rgb);// 构建一个Color对象
                color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
                // 重新构建一次Color对象(有透明值的)
                imageSave.setRGB(x, y, color.getRGB());
                // 设置颜色值
            }
        }
        try {
            ImageIO.write(imageSave, "png", save);// 保存图像
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}