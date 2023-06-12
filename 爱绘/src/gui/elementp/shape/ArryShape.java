package gui.elementp.shape;

import gui.drawTool.Pen;
import java.awt.*;

//类:数组图像
public class ArryShape extends Shape {

    //构造方法:设置特征点和标识
    public ArryShape(Point[] points){
        super(points);
        sState=SState.Array;
    }

    //方法:绘制多边形，顺序连接
    @Override
    public void drawT(Pen pen, Graphics g) {
        g.setColor(pen.getColor());
        g.setFont(pen.getFont());
        for (int i = 0; i < points.length - 1; i++) {
            pen.drawLine(points[i], points[i + 1], g);
        }
        pen.drawLine(points[0], points[points.length-1], g);
    }

}
