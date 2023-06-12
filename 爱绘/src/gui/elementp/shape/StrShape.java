package gui.elementp.shape;

import gui.drawTool.Pen;
import java.awt.*;

//类:字符串图形
public class StrShape extends Shape {

    //属性:字符串
    private String str;

    //构造方法:
    public StrShape(Point[] points,String str){
        super(points);
        this.str=str;
        sState=SState.Str;
    }

    //方法:绘制
    @Override
    public void drawT(Pen pen, Graphics g) {
        g.setColor(pen.getColor());
        g.setFont(pen.getFont());
        pen.drawString(points[0],str,g);
    }

}
