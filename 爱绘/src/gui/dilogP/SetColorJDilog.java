package gui.dilogP;

import gui.windowp.MainWindow;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//类:设置颜色的对话框
public class SetColorJDilog {

    //枚举类：状态
    public enum SState{
        Three,Chooser
    }

    //属性
    private SState sState;
    private JDialog jDialog;
    private PenColorThreeDilog penColorThreeDilog;
    private JDialog jDialogChoose;
    private JColorChooser jColorChooser;
    private static Color colorT;

    //构造方法
    public SetColorJDilog(MainWindow mainWindow){
        sState=SState.Chooser;
        ActionListener actionListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        };
        penColorThreeDilog=new PenColorThreeDilog(mainWindow,"颜色选择器",true);
        jColorChooser=new JColorChooser();
        jDialogChoose=JColorChooser.createDialog(mainWindow,"颜色选择器",true,jColorChooser,actionListener,actionListener );
    }

    //方法:设置
    public void setDialog(SState sState){
        this.sState=sState;
        if(sState==SState.Chooser){
            jDialog=jDialogChoose;
        }else if(sState==SState.Three){
            jDialog=penColorThreeDilog;
        }
    }

    //方法：加载对话框
    public void load(){
        setDialog(sState);
        jDialog.setVisible(true);
    }

    //方法：内部获取颜色
    private void setColor(){
        if(sState==SState.Chooser){
            colorT=jColorChooser.getColor();
        }else if(sState==SState.Three){
            colorT=PenColorThreeDilog.colorT;
        }
    }

    //方法：外部获取颜色
    public Color getColor(){
        setColor();
        return colorT;
    }

}
