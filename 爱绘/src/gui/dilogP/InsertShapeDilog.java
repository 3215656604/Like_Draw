package gui.dilogP;

import gui.drawTool.Pen;
import gui.elementp.Element;
import gui.elementp.shape.CircleShape;
import gui.elementp.shape.StrShape;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InsertShapeDilog extends JDialog {
    private JButton[] jButtons;
    private Element element;
    private Pen pen;
    private Point p;
    private int n;
    private int c;
    private JTextField jTextField;

    public Element getElement() {
        return element;
    }

    public int getN() {
        return n;
    }

    public InsertShapeDilog(JFrame mainWindow, Pen pen, Point p){
        super(mainWindow,"图层设置",true);
        setLocation(mainWindow.getLocation().x+p.x,mainWindow.getLocation().y+p.y);
        this.p=p;
        this.pen=pen;
        jButtons=new JButton[3];
        jTextField=new JTextField();
        jButtons[0]=new JButton("圆");
        jButtons[1]=new JButton("文本");
        jButtons[2]=new JButton("多边形");
        getContentPane().setLayout(new GridLayout(3,1));
        getContentPane().add(jButtons[0]);
        getContentPane().add(jButtons[1]);
        getContentPane().add(jButtons[2]);
        Listener listener=new Listener();
        jButtons[0].addActionListener(listener);
        jButtons[1].addActionListener(listener);
        jButtons[2].addActionListener(listener);
        addWindowListener(new WidowList());
        pack();
        setVisible(true);
    }

    private class WidowList extends WindowAdapter{
        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println(p.x+":"+p.y);
            super.windowClosing(e);
            if(c==1){
                Point points[]=new Point[1];
                points[0]=new Point(p.x,p.y);
                int r=10;
                try {
                    r=Integer.valueOf(jTextField.getText());
                }catch (Exception e1){
                }
                element=new Element(pen,new CircleShape(points,r));
            }else if(c==2){
                Point points[]=new Point[1];
                points[0]=new Point(p.x,p.y);
                element=new Element(pen,new StrShape(points,jTextField.getText()));
            }else if(c==3){
                element=null;
                n=0;
                try {
                    n=Integer.valueOf(jTextField.getText());
                }catch (Exception e1){
                }
            }
        }
    }
    private class Listener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            c=0;
            if(e.getSource() instanceof JButton){
                c=1;
                JButton button=(JButton) e.getSource();
                getContentPane().removeAll();
                getContentPane().setLayout(null);
                if(button.getText().equals("圆")){
                    InsertShapeDilog.this.setSize(100,70);
                    JLabel jLabel=new JLabel("半径");
                    getContentPane().add(jLabel);
                    getContentPane().add(jTextField);
                    jLabel.setBounds(0,0,InsertShapeDilog.this.getWidth()/3,InsertShapeDilog.this.getWidth()/5);
                    jTextField.setBounds(InsertShapeDilog.this.getWidth()/3,0,InsertShapeDilog.this.getWidth()/3*2-20,InsertShapeDilog.this.getWidth()/5);
                }else if(button.getText().equals("文本")){
                    c=2;
                    InsertShapeDilog.this.setSize(100,70);
                    JLabel jLabel=new JLabel("文本");
                    getContentPane().add(jLabel);
                    getContentPane().add(jTextField);
                    jLabel.setBounds(0,0,InsertShapeDilog.this.getWidth()/3,InsertShapeDilog.this.getWidth()/5);
                    jTextField.setBounds(InsertShapeDilog.this.getWidth()/3,0,InsertShapeDilog.this.getWidth()/3*2-20,InsertShapeDilog.this.getWidth()/5);
                }else if(button.getText().equals("多边形")){
                    c=3;
                    InsertShapeDilog.this.setSize(100,70);
                    JLabel jLabel=new JLabel("顶点数");
                    getContentPane().add(jLabel);
                    getContentPane().add(jTextField);
                    jLabel.setBounds(0,0,InsertShapeDilog.this.getWidth()/3,InsertShapeDilog.this.getWidth()/5);
                    jTextField.setBounds(InsertShapeDilog.this.getWidth()/3,0,InsertShapeDilog.this.getWidth()/3*2-20,InsertShapeDilog.this.getWidth()/5);
                }
            }
        }
    }
}
