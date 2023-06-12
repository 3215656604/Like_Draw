package gui.panelp;

import gui.windowp.MainWindow;
import instrument.Data;
import software.Software;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//类:帮助面板
public class HelpPanel extends VariablePanel {

    //属性:面板组件
    private TextArea[] problem_answer;
    private int n;
    private JButton esc;
    private Data data;

    //构造方法:初始化
    public HelpPanel(){
        data=new Data();
        n=7;
        problem_answer=new TextArea[7];
        for(int i=0;i<n;i++){
            problem_answer[i]=new TextArea();
            problem_answer[i].setEditable(false);
            add(problem_answer[i]);
        }
        problem_answer[0].setText("问题：爱绘是什么?\n答案：爱绘是一款简洁绘画软件。");
        problem_answer[1].setText("问题：爱绘如何绘画?\n答案：爱绘是通过绘笔进行绘画。");
        problem_answer[2].setText("问题：爱绘有什么特色功能?\n答案：爱绘支持多图层绘图，可以进行图层操作与处理。");
        problem_answer[3].setText("问题：爱绘有什么特色功能?\n答案：爱绘可以进行图片透明化处理，支持用户临摹等需求。");
        problem_answer[4].setText("问题：爱绘在撤销与重做时卡顿怎么办?\n答案：请在设置中增加单体撤销重做操作的单元数目，以减少快捷键的高频率使用。");
        problem_answer[5].setText("问题：爱绘为什么清空无法重做?\n答案：因为清空会不可恢复的删除元素。");
        problem_answer[6].setText("问题：爱绘如何设置最佳界面格局?\n答案：通过设置中点击格局按钮弹出调试窗口，更改窗口的大小和位置即可设置最佳格局。");
        esc=new JButton("返回");
        esc.addActionListener(new ButtonListener());
        setLayout(null);
        add(esc);
    }

    //类:面板按钮监听器
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainWindow in= Software.mainWindow;
            if(in!=null)in.updateStateT(MainWindow.MState.Initial);
        }
    }

    //方法:加载大小
    @Override
    public void loadBounds() {
        int weight=getWidth();
        int height=getHeight();
        for(int i=0;i<n;i++){
            problem_answer[i].setBounds(weight/40,height/10*(i+1),weight-weight/20,height/10);
        }
        esc.setBounds(0,0,weight/10,height/12);
    }

    //方法:加载颜色
    @Override
    public void loadSkin() {
        for(int i=0;i<n;i++){
            problem_answer[i].setFont(new Font(Font.DIALOG,Font.BOLD,getWidth()/50));
            problem_answer[i].setBackground(colorT);
        }
        esc.setBackground(colorT);
        esc.setFont(new Font(Font.DIALOG,Font.BOLD,getWidth()/50));
    }

}
