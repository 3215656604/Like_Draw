package gui.panelp;

import gui.windowp.MainWindow;
import instrument.Auxiliary;
import software.Software;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//类:关于面板
public class AboutPanel extends VariablePanel {

    //属性:组件和颜色
    private JLabel version;
    private JLabel developer;
    private JLabel feedbackEmail;
    private JButton esc;

    //构造方法：初始化
    public AboutPanel(){
        version=new JLabel("版本: 1.0");
        developer=new JLabel("开发人员: DuGan");
        feedbackEmail=new JLabel("反馈邮箱: 3215656604@qq.com");
        esc = new JButton("返回");
        setLayout(null);
        add(version);
        add(developer);
        add(feedbackEmail);
        add(esc);
        esc.addActionListener(new ButtonListener());
    }

    //类:About面板的按钮监听器
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainWindow in= Software.mainWindow;
            if(in!=null)in.updateStateT(MainWindow.MState.Initial);
        }
    }

    //方法:加载面板布局
    @Override
    public void loadBounds() {
        int widtht=getWidth();
        int height=getHeight();
        esc.setBounds(0,0,widtht/10,height/12);
        version.setFont(new Font(Font.DIALOG,Font.BOLD,widtht/50));
        version.setBounds(widtht/2-getWidth()/15,height/5,widtht,height/15);
        developer.setFont(new Font(Font.DIALOG,Font.BOLD,widtht/50));
        developer.setBounds(widtht/2-getWidth()/15,height/5*2,widtht,height/15);
        feedbackEmail.setFont(new Font(Font.DIALOG,Font.BOLD,widtht/50));
        feedbackEmail.setBounds(widtht/2-getWidth()/15,height/5*3,widtht,height/15);
        repaint();
    }

    //方法:加载颜色
    @Override
    public void loadSkin() {
        esc.setBackground(colorT);
        version.setForeground(colorT);
        developer.setForeground(colorT);
        feedbackEmail.setForeground(colorT);
    }

}
