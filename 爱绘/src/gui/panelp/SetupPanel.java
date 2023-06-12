package gui.panelp;

import gui.dilogP.SetColorJDilog;
import gui.windowp.MainWindow;
import software.Software;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SetupPanel extends VariablePanel {

    private JLabel jLabelSkin;
    private JLabel jLabelBounds;
    private JLabel jLabelColor;
    private JButton jButton1;
    private JButton jButton2;
    private JComboBox jComboBox;
    private JLabel jLabelRevocation;
    private JLabel jLabelReform;
    private JTextField jTextRevocation;
    private JTextField jTextReform;
    private JButton jButtonEse;

    public SetupPanel(){
        jLabelSkin=new JLabel("皮肤");
        jLabelBounds=new JLabel("最佳布局");
        jLabelColor=new JLabel("调色板");
        jButton1=new JButton("颜色");
        jButton2=new JButton("格局");
        jLabelRevocation=new JLabel("撤销数");
        jLabelReform=new JLabel("重做数");
        jButtonEse=new JButton("返回");
        jComboBox =new JComboBox();
        jTextReform=new JTextField();
        jTextRevocation=new JTextField();
        setLayout(null);
        add(jLabelSkin);
        add(jLabelBounds);
        add(jLabelColor);
        add(jButton1);
        add(jButton2);
        add(jComboBox);
        add(jLabelRevocation);
        add(jLabelReform);
        add(jTextRevocation);
        add(jTextReform);
        add(jButtonEse);
        Listener listener=new Listener();
        jComboBox.addItem("--请选择--");
        jComboBox.addItem("系统调色盘");
        jComboBox.addItem("三原色调色盘");
        jButton1.addActionListener(listener);
        jButton2.addActionListener(listener);
        jComboBox.addActionListener(listener);
        jButtonEse.addActionListener(listener);
    }
    class Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() instanceof JButton) {
                JButton jButton = (JButton) e.getSource();
                if (jButton.getText().equals("颜色")) {
                    SetColorJDilog setColorJDilog = new SetColorJDilog(Software.mainWindow);
                    setColorJDilog.load();
                    Software.mainWindow.setColor(setColorJDilog.getColor());
                    Software.mainWindow.overLoadingPandel();
                } else if (jButton.getText().equals("格局")) {
                    JDialog jDialog=new JDialog(Software.mainWindow,"调整最佳格局",true);
                    jDialog.setBounds(Software.mainWindow.getX(),Software.mainWindow.getY(),Software.mainWindow.getWidth(),Software.mainWindow.getY());
                    jDialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            Point loca=new Point(1,1);
                            loca.x=jDialog.getX();
                            loca.y=jDialog.getY();
                            Software.mainWindow.setLocationtT(loca);
                            Software.mainWindow.setwT(jDialog.getWidth());
                            Software.mainWindow.sethT(jDialog.getHeight());
                        }
                    });
                    jDialog.setVisible(true);
                    Software.mainWindow.loadS();
                }else if(jButton.getText().equals("返回")){
                    try {
                        MainWindow.MainMenu.MenuAction.setEn(Integer.valueOf(jTextRevocation.getText()));
                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                    try {
                        MainWindow.MainMenu.MenuAction.setQn(Integer.valueOf(jTextReform.getText()));
                    }catch (Exception e1){
                        e1.printStackTrace();
                    }
                    MainWindow in= Software.mainWindow;
                    if(in!=null)in.updateStateT(MainWindow.MState.Initial);
                }
            }
            else if(e.getSource() instanceof JComboBox){
                JComboBox jComboBox=(JComboBox) e.getSource();
                if(((DrawingPanel)(Software.mainWindow.getPanelManager().getDrawingPanel())).getSetColorJDilog()==null)
                    ((DrawingPanel)(Software.mainWindow.getPanelManager().getDrawingPanel())).setSetColorJDilog(new SetColorJDilog(Software.mainWindow));
                if(jComboBox.getSelectedIndex()==1){
                    ((DrawingPanel)(Software.mainWindow.getPanelManager().getDrawingPanel())).getSetColorJDilog().setDialog(SetColorJDilog.SState.Chooser);
                }else if(jComboBox.getSelectedIndex()==2){
                    ((DrawingPanel)(Software.mainWindow.getPanelManager().getDrawingPanel())).getSetColorJDilog().setDialog(SetColorJDilog.SState.Three);}
            }
        }
    }
    @Override
    public void loadBounds() {
        int x=20;
        jButtonEse.setBounds(0,0,getWidth()/10,getHeight()/12);
        jLabelSkin.setFont(new Font(Font.DIALOG,Font.PLAIN,x));
        jLabelColor.setFont(new Font(Font.DIALOG,Font.PLAIN,x));
        jLabelBounds.setFont(new Font(Font.DIALOG,Font.PLAIN,x));
        jLabelRevocation.setFont(new Font(Font.DIALOG,Font.PLAIN,x));
        jLabelReform.setFont(new Font(Font.DIALOG,Font.PLAIN,x));
        jLabelSkin.setBounds(getWidth()/3,getHeight()/10,getWidth()/5,getHeight()/5/2);
        jLabelBounds.setBounds(getWidth()/3,getHeight()/10*3,getWidth()/10,getHeight()/10);
        jLabelColor.setBounds(getWidth()/3,getHeight()/10*5,getWidth()/10,getHeight()/10);
        jLabelRevocation.setBounds(getWidth()/3,getHeight()/10*7,getWidth()/10,getHeight()/10);
        jLabelReform.setBounds(getWidth()/3,getHeight()/10*9,getWidth()/10,getHeight()/10);
        jButton1.setBounds(getWidth()/3+getWidth()/10,getHeight()/10,getWidth()/10,getHeight()/10);
        jButton2.setBounds(getWidth()/3+getWidth()/10,getHeight()/10*3,getWidth()/10,getHeight()/10);
        jComboBox.setBounds(getWidth()/3+getWidth()/10,getHeight()/10*5,getWidth()/10,getHeight()/10);
        jTextRevocation.setBounds(getWidth()/3+getWidth()/10,getHeight()/10*7,getWidth()/10,getHeight()/10);
        jTextReform.setBounds(getWidth()/3+getWidth()/10,getHeight()/10*9,getWidth()/10,getHeight()/10);
        jTextRevocation.setText(""+MainWindow.MainMenu.MenuAction.en+"");
        jTextReform.setText(""+MainWindow.MainMenu.MenuAction.qn+"");
    }

    @Override
    public void loadSkin() {
        jButtonEse.setBackground(colorT);
        jLabelSkin.setBackground(colorT);
        jLabelBounds.setBackground(colorT);
        jLabelColor.setBackground(colorT);
        jButton1.setBackground(colorT);
        jButton2.setBackground(colorT);
        jComboBox.setBackground(colorT);
        jLabelRevocation.setBackground(colorT);
        jLabelReform.setBackground(colorT);
        jTextRevocation.setBackground(colorT);
        jTextReform.setBackground(colorT);
    }

}
