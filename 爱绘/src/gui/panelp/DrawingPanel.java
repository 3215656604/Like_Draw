package gui.panelp;

import gui.dilogP.InsertShapeDilog;
import gui.dilogP.PenSizeDilog;
import gui.dilogP.SetColorJDilog;
import gui.dilogP.SetCoverageDilog;
import gui.elementp.Element;
import gui.elementp.CoverageS;
import gui.elementp.shape.ArryShape;
import gui.elementp.shape.ImageShape;
import gui.elementp.shape.PointShape;
import gui.drawTool.PenManagement;
import gui.windowp.MainWindow;
import software.Software;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class DrawingPanel extends VariablePanel {

    private enum MouseState {
        DrawPen, Pencil, Eraser
    }

    private void loadMoState() {
        BufferedImage image = null;
        File file = null;
        int x = 0, y = 0;
        if (mouseState == MouseState.DrawPen) {
            file = new File("C:\\Users\\32156\\爱绘\\src\\resource\\drawPen.png");
        } else if (mouseState == MouseState.Pencil) {
            file = new File("C:\\Users\\32156\\爱绘\\src\\resource\\pencil.png");
        } else if (mouseState == MouseState.Eraser) {
            x = y = 2;
            file = new File("C:\\Users\\32156\\爱绘\\src\\resource\\eraser.png");
        }
        try {
            image = ImageIO.read(file);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        setCursor(Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(x, y), "mycursor"));
    }

    private Point pointT;
    private MouseState mouseState;
    private MouseMenu mouseMenu;
    private CoverageS coverageS;
    private PenManagement penManagement;
    private SetColorJDilog setColorJDilog;
    private SetCoverageDilog setCoverageDilog;

    public SetColorJDilog getSetColorJDilog() {
        return setColorJDilog;
    }

    public void setSetColorJDilog(SetColorJDilog setColorJDilog) {
        this.setColorJDilog = setColorJDilog;
    }

    public CoverageS getElementManagements() {
        return coverageS;
    }

    public PenManagement getPenManagement() {
        return penManagement;
    }

    public DrawingPanel() {
        mouseState = MouseState.DrawPen;
        mouseMenu = new MouseMenu();
        MouseListen mouseListen = new MouseListen();
        addMouseListener(mouseListen);
        addMouseMotionListener(mouseListen);
        coverageS = new CoverageS();
        coverageS.add();
        penManagement = new PenManagement();
        setBackground(new Color(255,255,255));
        loadMoState();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        coverageS.show(g);
    }

    @Override
    public void loadBounds() {

    }

    @Override
    public void loadSkin() {
        mouseMenu.load();
    }

    public class MouseListen extends MouseAdapter implements MouseMotionListener {

        private static Point[] points;
        private static int n;
        private static boolean insertShape;
        private static Point p;
        private static int x;

        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if(insertShape);
            else if (e.getButton() == MouseEvent.BUTTON1) {
                Point[] points = new Point[1];
                points[0] = new Point(e.getX(), e.getY());
                PointShape pointShape = new PointShape(points);
                if(coverageS.getElementMangement()!=null)coverageS.getElementMangement().add(new Element(penManagement.getPen(), pointShape));
                repaint();
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                MainWindow in = Software.mainWindow;
                pointT=new Point(e.getX(),e.getY());
                mouseMenu.show(in, e.getX(), e.getY());
            }
            loadMoState();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            if(insertShape){
                Point[] pointss = new Point[1];
                pointss[0] = new Point(e.getX(), e.getY());
                PointShape pointShape = new PointShape(pointss);
                if(coverageS.getElementMangement()!=null)coverageS.getElementMangement().add(new Element(penManagement.getPen(), pointShape));
                try {
                    points[n++] = new Point(e.getX(), e.getY());
                }catch (Exception e1)
                {
                    insertShape=false;
                    n=0;
                }
                if(n==points.length){
                    ArryShape arryShape=new ArryShape(points);
                    if(coverageS.getElementMangement()!=null)coverageS.getElementMangement().add(new Element(penManagement.getPen(),arryShape));
                    insertShape=false;
                }
                repaint();
            }else if(e.getSource() instanceof JMenuItem) {
                MainWindow mainWindow = Software.mainWindow;
                JMenuItem jMenuItem = (JMenuItem) e.getSource();
                if (jMenuItem.getText().equals("橡皮擦")) {
                    penManagement.setPenState(PenManagement.PState.Eraser);
                    DrawingPanel drawingPanel = drawingPanel = (DrawingPanel) mainWindow.getPanelManager().getMainPanel(mainWindow.getStateT());
                    drawingPanel.mouseState = MouseState.Eraser;
                    drawingPanel.loadMoState();
                    repaint();
                } else if (jMenuItem.getText().equals("笔")) {
                    penManagement.setPenState(PenManagement.PState.DrawPenT);
                    DrawingPanel drawingPanel = drawingPanel = (DrawingPanel) mainWindow.getPanelManager().getMainPanel(mainWindow.getStateT());
                    drawingPanel.mouseState = MouseState.DrawPen;
                    drawingPanel.loadMoState();
                } else if (jMenuItem.getText().equals("绘笔样式")) {

                } else if (jMenuItem.getText().equals("绘笔颜色")) {
                    if (setColorJDilog == null) {
                        setColorJDilog = new SetColorJDilog(mainWindow);
                    }
                    setColorJDilog.load();
                    penManagement.getPen().setPen(setColorJDilog.getColor(), null, -1);
                } else if (jMenuItem.getText().equals("插入图片")) {
                    FileDialog fileDialog = new FileDialog(mainWindow, "插入文件", FileDialog.LOAD);
                    Image image = null;
                    fileDialog.setVisible(true);
                    File file = new File(fileDialog.getDirectory() + fileDialog.getFile());
                    try {
                        image = ImageIO.read(file);
                    } catch (Exception e1) {
                        System.out.println(e1.getMessage());
                    }
                    Point[] points = new Point[2];
                    points[0] = new Point(pointT.x, pointT.y);

                    JDialog jDialog=new JDialog(mainWindow,"调整图片大小",true);
                    JLabel jLabel1=new JLabel("长");
                    JTextField jTextField1=new JTextField();
                    JLabel jLabel2=new JLabel("宽");
                    JTextField jTextField2=new JTextField();
                    jDialog.getContentPane().setLayout(null);
                    jDialog.setSize(240,150);
                    jLabel1.setFont(new Font(Font.DIALOG,Font.PLAIN,20));
                    jLabel2.setFont(new Font(Font.DIALOG,Font.PLAIN,20));
                    jLabel1.setBounds(0,0,jDialog.getWidth()/3,jDialog.getHeight()/3);
                    jLabel2.setBounds(0,jDialog.getHeight()/3,jDialog.getWidth()/3,jDialog.getHeight()/3);
                    jTextField1.setBounds(jDialog.getWidth()/3,0,jDialog.getWidth()/3*2-40,jDialog.getHeight()/3);
                    jTextField2.setBounds(jDialog.getWidth()/3,jDialog.getHeight()/3,jDialog.getWidth()/3*2-40,jDialog.getHeight()/3);
                    jDialog.setLocation(mainWindow.getX()+p.x,mainWindow.getY()+p.y);
                    jDialog.getContentPane().add(jLabel1);
                    jDialog.getContentPane().add(jLabel2);
                    jDialog.getContentPane().add(jTextField1);
                    jDialog.getContentPane().add(jTextField2);
                    points[1]=new Point(500,500);
                    jDialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            try {
                                int x=Integer.valueOf(jTextField1.getText());
                                int y=Integer.valueOf(jTextField2.getText());
                                points[1]=new Point(x,y);
                            }catch (Exception e1){
                                e1.printStackTrace();
                            }
                        }
                    });
                    jDialog.setVisible(true);
                    ImageShape imageShape = new ImageShape(points, image, mainWindow);
                    if(coverageS.getElementMangement()!=null)coverageS.getElementMangement().add(new Element(penManagement.getPen(), imageShape));
                    repaint();
                } else if (jMenuItem.getText().equals("填充")) {
                    Robot rbt = null;
                    try {
                        rbt = new Robot();
                    } catch (Exception e1) {
                    }
                    Point point = mainWindow.getLocationOnScreen();
                    int w = mainWindow.getWidth();
                    int h = mainWindow.getHeight();
                    jMenuItem.setSelected(false);
                    BufferedImage image = rbt.createScreenCapture(new Rectangle(point.x + 7, point.y + 53, w - 14, h - 60));
                    //Queue<Point> queue=new
                    //BFS(image,);
                } else if (jMenuItem.getText().equals("绘笔粗细")) {
                    PenSizeDilog penSizeDilog = new PenSizeDilog(mainWindow, "粗细", true,p);
                    penManagement.getPen().setPen(null, null, PenSizeDilog.penSize);
                } else if (jMenuItem.getText().equals("插入图形")) {
                    InsertShapeDilog insertShapeDilog=new InsertShapeDilog(mainWindow,penManagement.getPen(),p);
                    if(insertShapeDilog.getElement()!=null){
                        coverageS.getElementMangement().add(insertShapeDilog.getElement());
                    }else {
                        insertShape = true;
                        n = insertShapeDilog.getN();
                        if(n==0)insertShape=false;
                        points = new Point[n];
                        n=0;
                    }
                    repaint();
                } else if (jMenuItem.getText().equals("图层设置")) {
                    if(setCoverageDilog==null)setCoverageDilog=new SetCoverageDilog(Software.mainWindow,coverageS);
                    setCoverageDilog.setVisible(true);
                }else if (jMenuItem.getText().equals("字体颜色")) {
                    if (setColorJDilog == null) {
                        setColorJDilog = new SetColorJDilog(mainWindow);
                    }
                    setColorJDilog.load();
                    penManagement.getPen().setPen(null, null, -1);
                }else if (jMenuItem.getText().equals("字体大小")) {
                    JDialog jDialog=new JDialog(mainWindow,"设置字体大小",true);
                    JTextField jTextField=new JTextField();
                    jDialog.getContentPane().add(jTextField);
                    jDialog.pack();
                    jDialog.setLocation(mainWindow.getX()+p.x,mainWindow.getY()+p.y);
                    x=24;
                    jDialog.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            super.windowClosing(e);
                            try{
                                x=Integer.valueOf(jTextField.getText());
                            }catch (Exception e1){
                                e1.printStackTrace();
                            }
                        }
                    });
                    jDialog.setVisible(true);
                    Font font=new Font(Font.DIALOG,Font.PLAIN,x);
                    penManagement.getPen().setPen(null,font, -1);
                }
            }else p=new Point(e.getX(),e.getY());

        }
        public static int[] getRGB(BufferedImage image, int x, int y){
            int[] rgb = new int [3];
            int pixel = image.getRGB(x, y);
            rgb[0] = (pixel & 0xff0000) >> 16;
            rgb[1] = (pixel & 0xff00) >> 8;
            rgb[2] = (pixel & 0xff);
            return  rgb;
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Point[] points = new Point[1];
            points[0] = new Point(e.getX(), e.getY());
            PointShape pointShape = new PointShape(points);
            if(coverageS.getElementMangement()!=null)coverageS.getElementMangement().add(new Element(penManagement.getPen(), pointShape));
            repaint();
        }

    }

    //类:画图鼠标右键菜单
    public class MouseMenu extends JPopupMenu {

        //属性:菜单的栏目与选项
        private JMenu jMenuDrawPen;
        private JMenu jTool;
        private JMenu jFunction;
        private JMenuItem jDrawColor;
        private JMenuItem jSize;
        private JMenuItem jStyle;
        private JMenuItem jFill;
        private JMenuItem jEraser;
        private JMenuItem jInsertPicture;
        private JMenuItem jInsertShape;
        private JMenuItem jCoverage;
        private JMenuItem jFontColor;
        private JMenuItem jFontSize;
        private JMenuItem jPen;

        //方法:get获取菜单组件
        public JMenu getjMenuDrawPen() {
            return jMenuDrawPen;
        }

        public JMenu getjTool() {
            return jTool;
        }

        public JMenu getjFunction() {
            return jFunction;
        }

        public JMenuItem getjDrawColor() {
            return jDrawColor;
        }

        public JMenuItem getjSize() {
            return jSize;
        }

        public JMenuItem getjStyle() {
            return jStyle;
        }

        public JMenuItem getjFill() {
            return jFill;
        }

        public JMenuItem getjEraser() {
            return jEraser;
        }

        public JMenuItem getjInsertPicture() {
            return jInsertPicture;
        }

        //构造方法:初始化组件
        public MouseMenu() {
            jCoverage=new JMenuItem("图层设置");
            jMenuDrawPen = new JMenu("绘笔");
            jPen=new JMenuItem("笔");
            jTool = new JMenu("工具");
            jFunction = new JMenu("功能");
            jDrawColor = new JMenuItem("绘笔颜色");
            jFontColor = new JMenuItem("字体颜色");
            jFontSize=new JMenuItem("字体大小");
            jSize = new JMenuItem("绘笔粗细");
            jStyle = new JMenuItem("绘笔样式");
            jFill = new JMenuItem("填充");
            jEraser = new JMenuItem("橡皮擦");
            jInsertPicture = new JMenuItem("插入图片");
            jInsertShape=new JMenuItem("插入图形");
            jMenuDrawPen.add(jDrawColor);
            jMenuDrawPen.add(jSize);
            //jMenuDrawPen.add(jStyle);
            //jMenuDrawPen.add(jFontColor);
            jMenuDrawPen.add(jFontSize);
            //jTool.add(jFill);
            jTool.add(jPen);
            jTool.add(jEraser);
            jFunction.add(jInsertPicture);
            jFunction.add(jInsertShape);
            jFunction.add(jCoverage);
            add(jMenuDrawPen);
            add(jTool);
            add(jFunction);
            MouseListen mouseItemListen = new MouseListen();
            jEraser.addMouseListener(mouseItemListen);
            jSize.addMouseListener(mouseItemListen);
            jPen.addMouseListener(mouseItemListen);
            //jFill.addMouseListener(mouseItemListen);
            jDrawColor.addMouseListener(mouseItemListen);
            jStyle.addMouseListener(mouseItemListen);
            jInsertPicture.addMouseListener(mouseItemListen);
            jInsertShape.addMouseListener(mouseItemListen);
            jCoverage.addMouseListener(mouseItemListen);
            jFontSize.addMouseListener(mouseItemListen);
            jFontColor.addMouseListener(mouseItemListen);
            load();
        }

        public void load(){
            jCoverage.setBackground(DrawingPanel.this.colorT);
            jMenuDrawPen.setBackground(DrawingPanel.this.colorT);
            jPen.setBackground(DrawingPanel.this.colorT);
            jTool.setBackground(DrawingPanel.this.colorT);
            jFunction.setBackground(DrawingPanel.this.colorT);
            jDrawColor.setBackground(DrawingPanel.this.colorT);
            jFontColor.setBackground(DrawingPanel.this.colorT);
            jFontSize.setBackground(DrawingPanel.this.colorT);
            jSize.setBackground(DrawingPanel.this.colorT);
            jStyle.setBackground(DrawingPanel.this.colorT);
            jFill.setBackground(DrawingPanel.this.colorT);
            jEraser.setBackground(DrawingPanel.this.colorT);
            jInsertPicture.setBackground(DrawingPanel.this.colorT);
            jInsertShape.setBackground(DrawingPanel.this.colorT);
        }
    }
}
