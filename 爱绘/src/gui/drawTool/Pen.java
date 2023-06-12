package gui.drawTool;

import java.awt.*;
import java.awt.image.ImageObserver;

//抽象公开类:笔
public abstract class Pen{

    //属性:粗细 颜色 字体
    protected int size=-1;
    protected Color color;
    protected Font font;

    //方法:get
    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }

    public Font getFont() {
        return font;
    }

    //方法:set
    public void setPen(Color color,Font font,int size){
        if(color!=null)this.color=color;
        if(font!=null)this.font=font;
        if(size!=-1)this.size=size;
    }

    //构造方法:设置颜色字体
    public Pen(Color color,Font font){
        this.color=color;
        this.font=font;
    }

    //方法:画点
    public abstract void drawPoint(Point p,Graphics g);

    //方法:画字符串
    public void drawString(Point point,String str,Graphics g){
        g.drawString(str,point.x, point.y);
    }

    //方法:画图片
    public void drawImage(Point point,int widith,int height, Image image, ImageObserver imageObserver, Graphics g){
        g.drawImage(image,  point.x, point.y, widith, height, imageObserver);
    }

    //方法:画线调用方法
    public void drawLine(Point begin,Point end,Graphics g){
        Point[] point=new Point[2];
        point[0]=new Point();
        point[1]=new Point();
        point[0].x=begin.x;
        point[0].y=begin.y;
        point[1].x=end.x;
        point[1].y=end.y;
        drawLineT(point[0],point[1],g);
    }

    //方法:底层画线方法
    void drawLineT(Point begin,Point end,Graphics g){
        double k;
        Point start=new Point();
        if(begin.x>end.x){
            Point tmp=new Point();
            tmp.x=begin.x;
            tmp.y=begin.y;
            begin.x=end.x;
            begin.y=end.y;
            end.x=tmp.x;
            end.y=tmp.y;
        }
        start.x=begin.x;
        start.y=begin.y;

        k=(end.y-begin.y)*1.0/(end.x- begin.x);

        if(begin.equals(end))return;
        else if (Math.abs(k)==1.0/0) {
            if (begin.y > end.y) {
                int tmp = begin.y;
                begin.y = end.y;
                end.y = tmp;
            }
            for (; begin.y <= end.y; begin.y++) {
                drawPoint(begin, g);
            }
        } else if(k==0){
            for (; begin.x <= end.x; begin.x++) {
                drawPoint(begin, g);
            }
        }
        else if (k > 0) {
            for (; (begin.x-start.x) * k +start.y <= end.y; begin.x++) {
                Point nexe=new Point(begin.x,begin.y);
                begin.y = (int)((begin.x-start.x) * k +start.y);
                drawBu(begin,nexe,g);
                drawPoint(begin, g);
            }
        } else {
            for (; (begin.x-start.x) * k +start.y >= end.y; begin.x++) {
                Point nexe=new Point(begin.x,begin.y);
                begin.y = (int)((begin.x-start.x) * k +start.y);
                if(Math.abs(k)>6){
                    drawBu(begin,nexe,g);
                }
                drawPoint(begin, g);
            }
        }
    }
    public void drawBu(Point p,Point q,Graphics g){
        Point a,b;
        if(p.y<q.y) {
            a = new Point(p.x, p.y);
            b = new Point(q.x, q.y);
        }else {
            a = new Point(q.x, q.y);
            b = new Point(p.x, p.y);
        }
        while(a.y<b.y){
            a.y++;
            drawPoint(a,g);
        }
    }
    //方法:根据画笔对象构造新的对象返回
    public static Pen copyPen(Pen pen){
        Pen penT=null;
        if(pen instanceof DrawPen){
            penT=new DrawPen(pen.getColor(),pen.getFont());
            penT.size=pen.size;
        }else if(pen instanceof Eraser){
            penT=new Eraser(pen.getColor(),pen.getFont());
            penT.size=pen.size;
        }
        return penT;
    }

}
