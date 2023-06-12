package gui.panelp;

import gui.windowp.MainWindow;
import instrument.Auxiliary;
import software.Software;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//类：初始面板-可变的
public class InitialPanel extends VariablePanel {

    //属性：面板的属性
    private JLabel themes;
    private JButton drawing;
    private JButton setup;
    private JButton help;
    private JButton about;

    //构造方法:初始化面板属性并将其添加于面板
    public InitialPanel() {
        themes = new JLabel();
        themes.setToolTipText("艺术是人类的天性🎈");
        themes.setText("爱绘");

        drawing = new JButton("绘图");
        drawing.setToolTipText("🎨");
        drawing.setBorderPainted(false);

        help = new JButton("文档");
        help.setToolTipText("📖");
        help.setBorderPainted(false);

        setup = new JButton("设置");
        setup.setToolTipText("⚙️");
        setup.setBorderPainted(false);

        about = new JButton("关于");
        about.setToolTipText("🎖️");
        about.setBorderPainted(false);

        ButtonListener buttonListener = new ButtonListener();
        drawing.addActionListener(buttonListener);
        about.addActionListener(buttonListener);
        help.addActionListener(buttonListener);
        setup.addActionListener(buttonListener);

        setLayout(null);
        add(themes);
        add(drawing);
        add(setup);
        add(help);
        add(about);
    }

    //方法:根据面板大小配置组件大小等
    @Override
    public void loadBounds() {
        int widtht=getWidth();
        int height=getHeight();
        themes.setFont(new Font(Font.DIALOG,Font.BOLD,widtht/12));
        themes.setBounds(widtht/2-getWidth()/12,height/10,widtht/5,height/5);
        drawing.setBounds(widtht/2-getWidth()/20,height/9*3,widtht/10,height/12);
        drawing.setFont(new Font(Font.DIALOG,Font.PLAIN,widtht/40));
        help.setBounds(widtht/2-getWidth()/20,height/9*4,widtht/10,height/12);
        help.setFont(new Font(Font.DIALOG,Font.PLAIN,widtht/40));
        setup.setBounds(widtht/2-getWidth()/20,height/9*5,widtht/10,height/12);
        setup.setFont(new Font(Font.DIALOG,Font.PLAIN,widtht/40));
        about.setBounds(widtht/2-getWidth()/20,height/9*6,widtht/10,height/12);
        about.setFont(new Font(Font.DIALOG,Font.PLAIN,widtht/40));
    }

    //方法:根据颜色配置组件颜色
    @Override
    public void loadSkin() {
        themes.setForeground(colorT);
        drawing.setBackground(colorT);
        help.setBackground(colorT);
        setup.setBackground(colorT);
        about.setBackground(colorT);
    }

    //类:按钮监听器
    private class ButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            MainWindow in= Software.mainWindow;
            if(in==null){ System.out.println("Error:InitialPanel-106"); }
            else if(e.getSource()==drawing){
                in.updateStateT(MainWindow.MState.Drawing);
            }else if(e.getSource()==about) {
                in.updateStateT(MainWindow.MState.About);
            }else if(e.getSource()==setup){
                in.updateStateT(MainWindow.MState.Setup);
            }else if(e.getSource()==help){
                in.updateStateT(MainWindow.MState.Help);
            }
        }
    }

}
