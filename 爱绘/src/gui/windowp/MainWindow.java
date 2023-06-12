package gui.windowp;

import gui.elementp.Coverage;
import gui.elementp.CoverageS;
import gui.panelp.DrawingPanel;
import gui.panelp.VariablePanel;
import instrument.TranslucentImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

//类:主体窗口
public class MainWindow extends JFrame implements Settable{

    //枚举:窗口状态值
    public enum MState {
        Initial,Drawing,Setup,Help,About
    }

    //属性:窗口组件与面板
    private BufferedImage titleImage;
    private MState mState;
    private Color color;
    private PanelManager panelManager;
    private MainMenu mainMenu;
    private Point locationtT;
    private int wT;
    private int hT;

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Point getLocationtT() {
        return locationtT;
    }

    public void setLocationtT(Point locationtT) {
        this.locationtT = locationtT;
    }

    public void setwT(int wT) {
        this.wT = wT;
    }

    public void sethT(int hT) {
        this.hT = hT;
    }

    //方法:获取面板管理器
    public PanelManager getPanelManager() {
        return panelManager;
    }

    //方法:获取各个面板
    public VariablePanel getMainPanel() { return panelManager.getMainPanel(mState); }

    //方法:获取状态
    public MState getStateT() {
        return mState;
    }

    //方法:设置主窗口的状态
    public void setState(MState MState) { this.mState = MState; }

    //构造方法:初始化界面
    public MainWindow(){
        mState = mState.Initial;

        color=new Color(249, 106, 173, 255);
        panelManager=new PanelManager();
        mainMenu =new MainMenu();

        File file=new File("C:\\Users\\32156\\爱绘\\src\\resource\\tit.jpg");
        try {
            titleImage = ImageIO.read(file);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        panelManager.setColorT(color);
        setJMenuBar(mainMenu);
        setIconImage(titleImage);
        locationtT=new Point();
        locationtT.x=500;
        locationtT.y=70;
        wT=1200;
        hT=900;
        this.setBounds(locationtT.x,locationtT.y,wT,hT);
        getContentPane().setLayout(new BorderLayout());
        addComponentListener(new MainComponentListener());
        overLoadingPandel();
        refresh();
    }

    //方法:更新面板
    public void updateStateT(MState ne){
        getContentPane().remove(panelManager.getMainPanel(mState));
        setState(ne);
        overLoadingPandel();
        refresh();
    }

    public void loadS(){
        setBounds(locationtT.x,locationtT.y,wT,hT);
        overLoadingPandel();
        refresh();
    }

    //设置窗口
    @Override
    public void set(Point p, Integer width, Integer height, Color color) {
        if(p!=null)setLocation(p);
        if(width!=null)setSize(width,getHeight());
        if(height!=null)setSize(getWidth(),height);
        if(color!=null)this.color=color;
        panelManager.setColorT(this.color);
        overLoadingPandel();
        refresh();
    }

    //方法:重载大小
    public void overLoadingSize(){
        panelManager.loadPanel(mState);
        repaint();
    }

    //方法:重载面板
    public void overLoadingPandel(){
        getPanelManager().setColorT(color);
        overLoadingSize();
        getContentPane().add(panelManager.getMainPanel(mState));
        repaint();
    }
    public void refresh(){
        setSize(getWidth(),getHeight()+1);
        setSize(getWidth(),getHeight()-1);
    }
    //类:主体窗口监听器
    public class MainComponentListener extends ComponentAdapter {
        //方法:处理窗口改变大小事情
        @Override
        public void componentResized(ComponentEvent e) {
            super.componentResized(e);
            try {
                MainWindow mainWindow=(MainWindow)e.getSource();
                mainWindow.overLoadingSize();
            }catch (Exception e1){
                System.out.println("MainWindowListener监听器专用于MainWindow类");
                e1.printStackTrace();
            }
        }
    }

    //类:主体窗口的菜单栏
    public class MainMenu extends JMenuBar {

        protected JMenu jMenu1;
        protected JMenu jMenu2;
        protected JMenu jMenu3;
        protected JMenu jMenu4;
        protected JMenuItem jMenuItem1;
        protected JMenuItem jMenuItem2;
        protected JMenuItem jMenuItem3;
        protected JMenuItem jMenuItem4;
        protected JMenuItem jMenuItem5;
        protected JMenuItem jMenuItem6;
        protected JMenuItem jMenuItem7;
        protected JMenuItem jMenuItem8;
        protected JMenuItem jMenuItem9;
        protected JMenuItem jMenuItem10;
        protected JMenuItem jMenuItem11;
        protected JMenuItem jMenuItem12;

        //构造方法：初始化菜单栏
        public MainMenu() {
            jMenu1 = new JMenu("选项");
            jMenu2 = new JMenu("编辑");
            jMenu3 = new JMenu("帮助");
            jMenu4 = new JMenu("文件");
            jMenuItem1 = new JMenuItem("设为最佳布局");
            jMenuItem2 = new JMenuItem("主界面");
            jMenuItem3 = new JMenuItem("设置");
            jMenuItem4 = new JMenuItem("退出");
            jMenuItem5 = new JMenuItem("清空");
            jMenuItem6 = new JMenuItem("撤销");
            jMenuItem7 = new JMenuItem("重做");
            jMenuItem8 = new JMenuItem("使用指南");
            jMenuItem9 = new JMenuItem("文档");
            jMenuItem10 = new JMenuItem("关于");
            jMenuItem11 = new JMenuItem("保存");
            jMenuItem12 = new JMenuItem("处理图片");
            jMenu1.add(jMenuItem1);
            jMenu1.add(jMenuItem2);
            jMenu1.add(jMenuItem3);
            jMenu1.add(jMenuItem4);
            jMenu2.add(jMenuItem5);
            jMenu2.add(jMenuItem6);
            jMenu2.add(jMenuItem7);
            //jMenu3.add(jMenuItem8);
            jMenu3.add(jMenuItem9);
            jMenu3.add(jMenuItem10);
            jMenu4.add(jMenuItem11);
            jMenu4.add(jMenuItem12);

            add(jMenu4);
            add(jMenu1);
            add(jMenu2);
            add(jMenu3);

            MenuAction menuAction = new MenuAction();
            jMenuItem6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_DOWN_MASK));
            jMenuItem7.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_DOWN_MASK));
            jMenuItem1.addActionListener(menuAction);
            jMenuItem2.addActionListener(menuAction);
            jMenuItem3.addActionListener(menuAction);
            jMenuItem4.addActionListener(menuAction);
            jMenuItem5.addActionListener(menuAction);
            jMenuItem6.addActionListener(menuAction);
            jMenuItem7.addActionListener(menuAction);
            jMenuItem8.addActionListener(menuAction);
            jMenuItem9.addActionListener(menuAction);
            jMenuItem10.addActionListener(menuAction);
            jMenuItem11.addActionListener(menuAction);
            jMenuItem12.addActionListener(menuAction);
        }

        public class MenuAction implements ActionListener {

            public static int qn = 1;
            public static int en = 1;
            int aph;



            public static void setQn(int qn) {
                MenuAction.qn = qn;
            }

            public static void setEn(int en) {
                MenuAction.en = en;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                JMenuItem jMenuItem = (JMenuItem) e.getSource();
                if (jMenuItem.getText() == "保存") {
                    Robot rbt = null;
                    try {
                        rbt = new Robot();
                    } catch (Exception e1) {
                    }
                    Point point = MainWindow.this.getLocationOnScreen();
                    int w = MainWindow.this.getWidth();
                    int h = MainWindow.this.getHeight();
                    jMenuItem.setSelected(false);
                    FileDialog fileDialog = new FileDialog(MainWindow.this, "保存文件", FileDialog.SAVE);
                    fileDialog.setVisible(true);
                    File outputfile = new File(fileDialog.getDirectory() + fileDialog.getFile());
                    BufferedImage image = rbt.createScreenCapture(new Rectangle(point.x + 7, point.y + 53, w - 14, h - 60));
                    try {
                        ImageIO.write(image, "png", outputfile);
                    } catch (Exception e2) {
                    }
                } else if (jMenuItem.getText() == "处理图片") {
                    FileDialog fileDialog1 = new FileDialog(MainWindow.this, "选择处理图片", FileDialog.LOAD);
                    fileDialog1.setVisible(true);
                    File file1 = new File(fileDialog1.getDirectory() + fileDialog1.getFile());
                    BufferedImage image = null;
                    try {
                        image = ImageIO.read(file1);
                    } catch (Exception e1) {
                    }
                    JDialog jDialog=new JDialog(MainWindow.this,"设置图片透明度",true);
                    jDialog.setLocation(MainWindow.this.getX(),MainWindow.this.getY()+50);
                    jDialog.getContentPane().setLayout(new GridLayout(1,2));
                    jDialog.getContentPane().add(new JLabel("透明度"));
                    JTextField jTextField=new JTextField();
                    jDialog.getContentPane().add(jTextField);
                    jDialog.pack();
                    aph=250;
                    jDialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            try {
                                aph=Integer.valueOf(jTextField.getText());
                            }catch (Exception e1){
                                e1.printStackTrace();
                            }
                        }
                    });
                    jDialog.setVisible(true);
                    FileDialog fileDialog2 = new FileDialog(MainWindow.this, "选择保存图片", FileDialog.SAVE);
                    fileDialog2.setVisible(true);
                    File file2 = new File(fileDialog2.getDirectory() + fileDialog2.getFile());
                    TranslucentImage.Translucent(file1, file2, aph);
                } else if (jMenuItem.getText() == "撤销") {
                    if (mState == MState.Drawing) {
                        {
                            CoverageS eles = ((DrawingPanel) (panelManager.getMainPanel(mState))).getElementManagements();
                            Coverage ele = eles.getElementMangement();
                            if (ele != null)
                                for (int i = 0; i < en; i++) {
                                    ele.esc();
                                }
                            overLoadingPandel();
                            refresh();
                        }
                    }
                }else if (jMenuItem.getText() == "重做") {
                        if (mState == MState.Drawing) {
                            CoverageS eles = ((DrawingPanel) (panelManager.getMainPanel(mState))).getElementManagements();
                            Coverage ele = eles.getElementMangement();
                            if (ele != null)
                                for (int i = 0; i < qn; i++) {
                                    ele.next();
                                }
                            overLoadingPandel();
                            refresh();
                        }
                } else if (jMenuItem.getText() == "清空") {
                        if (mState == MState.Drawing) {
                            CoverageS eles = ((DrawingPanel) (panelManager.getMainPanel(mState))).getElementManagements();
                            Coverage ele = eles.getElementMangement();
                            ele.deleteAll();
                            overLoadingPandel();
                            refresh();
                        }
                } else if (jMenuItem.getText() == "退出") {
                        try {
                            Thread.sleep(700);
                        } catch (Exception e1) {
                        }
                        System.exit(0);
                } else if (jMenuItem.getText() == "关于") {
                        updateStateT(MState.About);
                } else if (jMenuItem.getText() == "文档") {
                        updateStateT(MState.Help);
                } else if (jMenuItem.getText() == "主界面") {
                        updateStateT(MState.Initial);
                } else if (jMenuItem.getText() == "设置") {
                        updateStateT(MState.Setup);
                } else if (jMenuItem.getText() == "设为最佳布局") {
                        MainWindow.this.loadS();
                } else if (jMenuItem.getText() == "使用指南") {
                        JFrame jFrame = new JFrame("使用指南");
                        jFrame.setBounds(MainWindow.this.getX(), MainWindow.this.getY() + hT / 17, wT / 2, hT / 2);
                        //JScrollPane jScrollPane=new JScrollPane(jFrame);
                        //jScrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                        jFrame.setVisible(true);
                }
            }

        }

    }

}

