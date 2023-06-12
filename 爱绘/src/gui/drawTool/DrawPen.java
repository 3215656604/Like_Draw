package gui.drawTool;

import java.awt.*;

//包中类:画笔
public class DrawPen extends Pen {

    //构造方法:设置画笔
    public DrawPen(Color color, Font font){
        super(color,font);
        size=10;
    }

    //方法:实现画点
    @Override
    public void drawPoint(Point p,Graphics g) {
        g.setColor(color);
        g.setFont(font);
        g.fillOval(p.x-size,p.y-size,size,size);
    }

}