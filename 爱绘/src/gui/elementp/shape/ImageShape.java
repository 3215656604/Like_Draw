package gui.elementp.shape;

import gui.drawTool.Pen;
import java.awt.*;
import java.awt.image.ImageObserver;

//类:图片图像
public class ImageShape extends Shape {

    //属性:特别的属性 图片与显示于对象
    private Image image;
    private ImageObserver imageObserver;

    //构造方法
    public ImageShape(Point[] points,Image image,ImageObserver imageObserver){
        super(points);
        this.image=image;
        this.imageObserver=imageObserver;
        sState=SState.Iamg;
    }

    //绘制方法
    @Override
    public void drawT(Pen pen,Graphics g) {
        g.setColor(pen.getColor());
        g.setFont(pen.getFont());
        pen.drawImage(points[0],points[1].x,points[1].y,image,imageObserver,g);
    }

}
