package gui.dilogP;

import gui.panelp.ThreeColorPanel;
import gui.windowp.MainWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//类:画笔风格面板
public class PenColorThreeDilog extends JDialog {

    //属性:合成颜色,绘图板,值
    public static Color colorT;
    private ThreeColorPanel threeColorPanel;
    private int sum=0;

    //类:监听器
    private class WindowListenT extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            int[] c=threeColorPanel.getColor();
            colorT=new Color(c[0], c[1], c[2]);
        }
    }

    //构造方法:初始化
    public PenColorThreeDilog(Frame owner, String title, boolean modal){
        super(owner,title,modal);
        threeColorPanel =new ThreeColorPanel();
        Container container=getContentPane();
        container.add(threeColorPanel);
        setSize(500,400);
        MainWindow mainWindow=(MainWindow)owner;
        setLocation(mainWindow.getLocationtT().x,mainWindow.getLocationtT().y+mainWindow.getHeight()/10);
        addWindowListener(new WindowListenT());
    }

    //方法:延迟绘制
    @Override
    public void paint(Graphics g) {
        sum++;
        if(sum>20) threeColorPanel.paint(g);
        super.paint(g);
    }

}
