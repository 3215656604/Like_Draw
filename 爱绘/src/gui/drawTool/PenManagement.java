package gui.drawTool;

import java.awt.*;

//公开类:笔管理器
public class PenManagement {

    //公开类:枚举状态值
    public enum PState{
        DrawPenT,Eraser
    }

    //属性:状态，各式各样的笔
    private PState pState;
    private Pen pen;
    private DrawPen drawPen;
    private Eraser eraser;

    //方法:get
    public Pen getPen() {
        return pen;
    }

    //方法:set
    public void setPenState(PState pState) {
        this.pState=pState;
        if(pState==PState.DrawPenT)
            pen=drawPen;
        else if(pState==PState.Eraser)
            pen=eraser;
    }

    //构造方法:初始化类对象
    public PenManagement(){
        drawPen=new DrawPen(new Color(1, 1, 1),new Font(Font.DIALOG,Font.PLAIN,24));
        eraser=new Eraser(new Color(255,255,255),new Font(Font.DIALOG,Font.PLAIN,24));
        pState=PState.DrawPenT;
        setPenState(pState);
    }

}

