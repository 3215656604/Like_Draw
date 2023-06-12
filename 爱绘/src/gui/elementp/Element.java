package gui.elementp;

import gui.elementp.shape.Shape;
import gui.drawTool.Pen;

import java.awt.*;

//公开类:界面元素
public class Element {

    //属性:绘笔和图形
    private Pen pen;
    private Shape shape;
    private boolean visiable;

    //方法:get
    public boolean isVisiable() {
        return visiable;
    }

    //方法:set
    public void setVisiable(boolean visiable) {
        this.visiable = visiable;
    }

    //构造方法
    public Element(Pen pen, Shape shape){
        this.pen= pen.copyPen(pen);
        this.shape=shape;
        this.visiable=true;
    }

    //方法:绘制
    public void show(Graphics g){
        if(visiable)shape.draw(pen,g);
    }

}
