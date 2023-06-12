package gui.windowp;

import gui.panelp.*;
import java.awt.*;

//类:面板管理员
public class PanelManager {

    //属性:管理的所有面板
    private VariablePanel initialPanel;
    private VariablePanel drawingPanel;
    private VariablePanel setupPanel;
    private VariablePanel helpPanel;
    private VariablePanel aboutPanel;
    private Color colorT;

    public Color getColorT() {
        return colorT;
    }


    public VariablePanel getDrawingPanel() {
        return drawingPanel;
    }

    public void setColorT(Color colorT) {
        this.colorT = colorT;
        initialPanel.setColorT(colorT);
        drawingPanel.setColorT(colorT);
        setupPanel.setColorT(colorT);
        helpPanel.setColorT(colorT);
        aboutPanel.setColorT(colorT);
    }

    //构造方法:初始化所有面板
    public PanelManager(){
        initialPanel=new InitialPanel();
        drawingPanel=new DrawingPanel();
        setupPanel=new SetupPanel();
        helpPanel=new HelpPanel();
        aboutPanel=new AboutPanel();
    }

    //方法:根据状态获取面板
    public VariablePanel getMainPanel(MainWindow.MState mState){
        if(mState == MainWindow.MState.Initial){
            return initialPanel;
        }else if(mState == MainWindow.MState.Help){
            return helpPanel;
        }else if(mState == MainWindow.MState.Drawing){
            return drawingPanel;
        }else if(mState == MainWindow.MState.Setup){
            return setupPanel;
        }else if(mState == MainWindow.MState.About){
            return aboutPanel;
        }
        return null;
    }

    //方法:加载当前面板
    public void loadPanel(MainWindow.MState MState){
        getMainPanel(MState).loadPanel();
        getMainPanel(MState).repaint();
    }

}
