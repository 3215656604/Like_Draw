package instrument;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class MyTrans {

    public static void main(String[] args) {
        //System.out.println("Please Enter alpha and d:");
        int alpha=255,d=30;
        //alpha=new Scanner(System.in).nextInt();
        //d=new Scanner(System.in).nextInt();
        Translucent(new File("C:\\Users\\32156\\爱绘\\src\\resource\\flower.png"),new File("C:\\Users\\32156\\爱绘\\src\\resource\\flowerR.png"),alpha,d);
    }
    public static int add(int a,int b){
        if(b>=0){
            if(a+b<=255)return a+b;
            else return a;
        }
        else {
            if(a+b>=0)return a+b;
            else return a;
        }
    }
    public static boolean Translucent(File image, File save, int alpha,int d) {

        int r=7,c=7;
        int a=1,b=0;
        //System.out.println("Please Enter r and c:");
        //r=new Scanner(System.in).nextInt();
        //c=new Scanner(System.in).nextInt();
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
        int maxa=0,maxb=0,maxc=0;
        int mina=0,minb=0,minc=0;
        for (int x = 0; x < width; x+=r) {
            for (int y = 0; y < height; y+=c) {
                rgb = imageOpen.getRGB(x, y);// 获得颜色值
                color = new Color(rgb);// 构建一个Color对象
                maxa=Math.max(color.getRed(),maxa);
                mina=Math.min(color.getRed(),mina);
                maxb=Math.max(color.getGreen(),maxb);
                mina=Math.min(color.getGreen(),minb);
                maxc=Math.max(color.getBlue(),maxc);
                mina=Math.min(color.getBlue(),minc);
            }
        }
        int max=255;
        int z=0;
        for (int x = 0; x < width; x+=r) {
            for (int y = 0; y < height; y+=c) {
                rgb = imageOpen.getRGB(x, y);// 获得颜色值
                color = new Color(rgb);// 构建一个Color对象
                color = new Color( (int)((color.getRed()-mina)*1.0/(maxa-mina)*max)+z, (int)((color.getGreen()-minb)*1.0/(maxb-minb)*max)+z, (int)((color.getBlue()-minc)*1.0/(maxc-minc)*max)+z, alpha);
                // 重新构建一次Color对象(有透明值的)
                imageSave.setRGB(x, y, color.getRGB());
                //设置颜色值
                for (int i=x;i<x+r&&i<width;i++){
                    for (int j=y;j<y+c&&j<height;j++){
                        imageSave.setRGB(i, j, color.getRGB());
                    }
                }
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
