package gui.drawTool;

import java.awt.*;

public class Eraser extends Pen{
    public Eraser(Color color,Font font){
        super(color,font);
        size=10;
    }
    @Override
    public void drawPoint(Point p, Graphics g) {
        g.fillOval(p.x,p.y,size,size);
    }
}
