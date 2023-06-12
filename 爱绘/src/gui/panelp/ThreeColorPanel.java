package gui.panelp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

//类:三色面板
public class ThreeColorPanel extends JPanel {

    //属性:三色和坐标尺寸
    private int[] color;
    int x=0;
    int y=0;
    int r=0;

    public int[] getColor() {
        return color;
    }

    //类:鼠标监听器
    private class MousetLister extends MouseAdapter implements MouseWheelListener {

        //构造方法:初始化
        public MousetLister(){
            addMouseWheelListener(this);
        }

        //方法:处理鼠标点击事件
        @Override
        public void mouseClicked(MouseEvent e) {
            Point point=e.getPoint();
            Point c1=new Point(x,y);
            Point c2=new Point(x+r,y);
            Point c3=new Point(x+r/2,(int)(y-r/2*Math.sqrt(3)));
            c1.x+=r;c1.y+=r;c2.x+=r;c2.y+=r;c3.x+=r;c3.y+=r;
            int dert = 0;
            super.mouseClicked(e);
            if (e.getButton() == MouseEvent.BUTTON1) {
                dert = 1;
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                dert = -1;
            }
            if (isInTheCircle(point, c1, r)) {
                if (color[0] + dert >= 0 && color[0] + dert <= 250) {
                    color[0] += dert;
                }
            } else if (isInTheCircle(point, c2, r)) {
                if (color[1] + dert >= 0 && color[1] + dert <= 250) {
                    color[1] += dert;
                }
            } else if (isInTheCircle(point, c3, r)) {
                if (color[2] + dert >= 0 && color[2] + dert <= 250) {
                    color[2] += dert;
                }
            }
            repaint();
        }

        //处理鼠标滚轮滚动事件
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            Point point=e.getPoint();
            Point c1=new Point(x,y);
            Point c2=new Point(x+r,y);
            Point c3=new Point(x+r/2,(int)(y-r/2*Math.sqrt(3)));
            c1.x+=r;c1.y+=r;c2.x+=r;c2.y+=r;c3.x+=r;c3.y+=r;
            int dert = 0;
            super.mouseWheelMoved(e);
            if (e.getWheelRotation()==1) {
                dert = 10;
            } else if (e.getWheelRotation()==-1) {
                dert = -10;
            }
            if (isInTheCircle(point, c1, r)) {
                if (color[0] + dert >= 0 && color[0] + dert <= 250) {
                    color[0] += dert;
                }
            } else if (isInTheCircle(point, c2, r)) {
                if (color[1] + dert >= 0 && color[1] + dert <= 250) {
                    color[1] += dert;
                }
            } else if (isInTheCircle(point, c3, r)) {
                if (color[2] + dert >= 0 && color[2] + dert <= 250) {
                    color[2] += dert;
                }
            }
            repaint();
        }
    }

    //构造方法:初始化
    public ThreeColorPanel(){
        color = new int[]{250, 250, 250};
        addMouseListener(new MousetLister());
        setLayout(null);
    }

    //方法:绘制三色圆
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        x=getWidth()/3;
        y=getHeight()/3;
        r=getWidth()/5;
        Font font=new Font(Font.DIALOG,Font.BOLD,getWidth()/30);
        g.setFont(font);
        g.setColor(new Color(255,0,0));
        g.drawString("红："+ color[0],getX()+getWidth()/20,getY()+getHeight()/12);
        g.setColor(new Color(0,0,255));
        g.drawString("蓝："+ color[2],getX()+getWidth()/20,getY()+getHeight()/12*2);
        g.setColor(new Color(0,255,0));
        g.drawString("绿："+ color[1],getX()+getWidth()/20,getY()+getHeight()/12*3);
        Point c1=new Point(x,y);
        Point c2=new Point(x+r,y);
        Point c3=new Point(x+r/2,(int)(y-r/2*Math.sqrt(3)));
        g.setColor(new Color(color[0],0,0));
        g.fillOval(c1.x,c1.y,2*r,2*r);
        g.setColor(new Color(0, color[1], 0));
        g.fillOval(c2.x,c2.y,2*r,2*r);
        g.setColor(new Color(0,0, color[2]));
        g.fillOval(c3.x,c3.y,2*r,2*r);
        c1.x+=r;c1.y+=r;c2.x+=r;c2.y+=r;c3.x+=r;c3.y+=r;
        Point start=new Point(x,y-r);
        Point end=new Point(x+3*r,y+2*r);
        g.setColor(new Color(color[0], color[1],0));
        paintG(g,start,end,c1,c2,r);
        g.setColor(new Color(color[0],0, color[2]));
        paintG(g,start,end,c1,c3,r);
        g.setColor(new Color(0, color[1], color[2]));
        paintG(g,start,end,c2,c3,r);
        g.setColor(new Color(color[0], color[1], color[2]));
        paintG(g,start,end,c1,c2,c3,r);
    }

    //方法:绘画两圆重合的部分
    private void paintG(Graphics g,Point start,Point end,Point c1,Point c2,int r){
        Point point=new Point();
        int c=2;
        point.x=start.x;
        point.y=start.y;
        for(;point.x<=end.x;point.x+=1){
            for(;point.y<=end.y;point.y++){
                if(isInTheCircle(point,c1,r)&&isInTheCircle(point,c2,r)){
                    g.fillOval(point.x,point.y,c,c);
                }
            }
            point.y=start.y;
        }
    }

    //方法:绘画三圆重合的部分
    private void paintG(Graphics g,Point start,Point end,Point c1,Point c2,Point c3,int r){
        Point point=new Point();
        int c=2;
        point.x=start.x;
        point.y=start.y;
        for(;point.x<=end.x;point.x+=1){
            for(;point.y<=end.y;point.y++){
                if(isInTheCircle(point,c1,r)&&isInTheCircle(point,c2,r)&&isInTheCircle(point,c3,r)){
                    g.fillOval(point.x,point.y,c,c);
                }
            }
            point.y=start.y;
        }
    }

    //方法:判断点是否在圆内
    private boolean isInTheCircle(Point p,Point c,int r){
        return r>=(Math.sqrt(Math.pow(p.x-c.x,2)+Math.pow(p.y-c.y,2)));
    }

}
