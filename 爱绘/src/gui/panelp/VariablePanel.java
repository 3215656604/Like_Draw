package gui.panelp;

import javax.swing.*;
import java.awt.*;

//接口:动态面板
public abstract class VariablePanel extends JPanel {

    protected Color colorT;

    public void setColorT(Color colorT){
        this.colorT=colorT;
    }

    //方法:设置面板布局
    public abstract void loadBounds();

    //方法:设置面板颜色
    public abstract void loadSkin();

    //方法:加载面板
    public final void loadPanel(){
        repaint();
        loadBounds();
        loadSkin();
    }

}
