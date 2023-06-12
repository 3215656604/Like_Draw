package gui.elementp.shape;

import gui.drawTool.Pen;
import java.awt.*;

//抽象类:图像
public abstract class Shape{

    //枚举:图像类别枚举值
    public enum SState{
        Array,CirCle,Str,Iamg,PointT
    }

    //属性:图像的特征点和类别标识
    Point[] points;
    SState sState;

    //构造方法:初始化特征点数组
    public Shape(Point[] points){
        this.points=points;
    }

    //方法:绘制(依赖Pen和Graphice类)
    public void draw(Pen pen,Graphics g){
        unified(pen,g);
        drawT(pen,g);
    }

    //方法:统一画笔和pen
    private void unified(Pen pen,Graphics g){
        g.setFont(pen.getFont());
        g.setColor(pen.getColor());
    }

    //方法:具体绘制
    protected abstract void drawT(Pen pen,Graphics g);

}
