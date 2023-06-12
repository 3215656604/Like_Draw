package gui.elementp.shape;

import gui.drawTool.Pen;
import java.awt.*;

//类:点图形
public class PointShape extends Shape {

    //构造方法
    public PointShape(Point[] points){
        super(points);
        sState=SState.PointT;
    }

    //方法:绘制
    @Override
    public void drawT(Pen pen,Graphics g) {
        g.setColor(pen.getColor());
        g.setFont(pen.getFont());
        pen.drawPoint(points[0],g);
    }

}
