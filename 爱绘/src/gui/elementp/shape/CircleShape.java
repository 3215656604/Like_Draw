package gui.elementp.shape;

import gui.drawTool.Pen;
import instrument.Auxiliary;
import java.awt.*;

//类:圆形图
public class CircleShape extends gui.elementp.shape.Shape {

    //属性:特别的属性r
    private int r;
    public CircleShape(Point[] points,int r){
        super(points);
        this.r=r;
        sState=SState.CirCle;
    }

    //方法:绘制
    @Override
    public void drawT(Pen pen, Graphics g) {
        g.setColor(pen.getColor());
        g.setFont(pen.getFont());
        if(sState== Shape.SState.CirCle) {
            int i, j;
            for (i = (points[0].x - r); i <= points[0].x + r; i++) {
                for (j = (points[0].y - r); j <= points[0].y + r; j++) {
                    Point pN = new Point(i, j);
                    if (Math.abs(pN.distance(points[0])-r)<=0.4) {
                            pen.drawPoint(pN, g);
                    }
                }
                j = (points[0].y - r);
            }
        }
    }

}
