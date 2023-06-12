package gui.dilogP;

import gui.windowp.MainWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PenSizeDilog extends JDialog {

    public static int penSize;
    private JTextField jTextField;

    //类:监听器
    private class WindowListenT extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            penSize=10;
            try {
                penSize=Integer.valueOf(jTextField.getText());
            }catch (Exception e1){
                System.out.println("信息:"+e1.getMessage());
            }
        }
    }

    //构造方法:初始化
    public PenSizeDilog(Frame owner, String title, boolean modal,Point p){
        super(owner,title,modal);
        jTextField=new JTextField();
        jTextField.setFont(new Font(Font.DIALOG,Font.PLAIN,24));
        Container container=getContentPane();
        container.setLayout(new BorderLayout());
        container.add(jTextField);
        MainWindow mainWindow=(MainWindow)owner;
        setSize(120,100);
        setLocation(mainWindow.getLocationtT().x+p.x,mainWindow.getLocationtT().y+mainWindow.getHeight()/10+p.y);
        addWindowListener(new WindowListenT());
        setVisible(true);
    }

}
