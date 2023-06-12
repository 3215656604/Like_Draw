package gui.dilogP;

import gui.elementp.CoverageS;
import software.Software;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SetCoverageDilog extends JDialog {

    private CoveragePanel coveragePanel;
    private CoverageS coverageS;

    public SetCoverageDilog(JFrame mainWindow, CoverageS coverageS){
        super(mainWindow,"图层设置",true);
        setLocation(mainWindow.getLocation().x,mainWindow.getLocation().y+mainWindow.getHeight()/10);
        setSize(1000,800);
        setLayout(new GridLayout(1,1));
        this.coverageS=coverageS;
        coveragePanel=new CoveragePanel(coverageS,getWidth(),getHeight());
        getContentPane().add(coveragePanel);
        coveragePanel.reLoad();
        repaint();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                for(int i=0;i<coveragePanel.coverageS.getCoveragesList().size();i++){
                    coveragePanel.coverageS.getCoveragesList().get(i).setVisiable(coveragePanel.jRadioButtons[i].isSelected());
                    if(coveragePanel.jRadioButtons[i].isSelected())coveragePanel.coverageS.setIndex(i);
                }
                Software.mainWindow.repaint();
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                coveragePanel.reLoad();
                repaint();
            }

        });
    }

    private class CoveragePanel extends JPanel {
        private JLabel[] jLabels;
        private JRadioButton[] jRadioButtons;
        private JButton[] jButtons1;
        private JButton[] jButtons2;
        private JButton jButton;
        private CoverageS coverageS;

        public CoveragePanel(CoverageS coverageS,int x,int y) {
            this.coverageS=coverageS;
            getContentPane().setLayout(null);
            setBounds(0,0,x,y);
        }

        private void loadSize(int size) {
            int wd = SetCoverageDilog.this.getWidth();
            int hd = SetCoverageDilog.this.getHeight();
            for (int i = 0; i < size; i++) {
                jLabels[i].setFont(new Font(Font.DIALOG, Font.PLAIN, wd / 24));
                jRadioButtons[i].setFont(new Font(Font.DIALOG, Font.PLAIN, wd / 24));
                jButtons1[i].setFont(new Font(Font.DIALOG, Font.PLAIN, wd / 30));
                jButtons2[i].setFont(new Font(Font.DIALOG, Font.PLAIN, wd / 30));
                jLabels[i].setBounds(0, hd / (size + 1) * i, wd / 12 * 4, hd / (size + 1));
                jRadioButtons[i].setBounds(wd / 12 * 3, hd / (size + 1) * i, wd / 12 * 3, hd / (size + 1));
                jButtons1[i].setBounds(wd / 12 * 6, hd / (size + 1) * i, wd / 12 * 2, hd / (size + 1));
                jButtons2[i].setBounds(wd / 12 * 9, hd / (size + 1) * i, wd / 12 * 2, hd / (size + 1));
            }
            jButton.setFont(new Font(Font.DIALOG, Font.PLAIN, wd / 24));
            jButton.setBounds(0, hd / (size + 1) * size , wd , hd / (size + 3));
        }

        private void loadZhi(int size) {
            for (int i = 0; i < size; i++) {
                jRadioButtons[i].setSelected(coverageS.getCoveragesList().get(i).isVisiable());
            }
        }

        public void reLoad(){
            int size = coverageS.getCoveragesList().size();
            getContentPane().removeAll();
            Listener listener = new Listener();
            jLabels = new JLabel[size];
            jRadioButtons = new JRadioButton[size];
            jButtons1 = new JButton[size];
            jButtons2 = new JButton[size];
            jButton = new JButton("添加图层");
            for (int i = 0; i < size; i++) {
                jLabels[i] = new JLabel("图层" + (i + 1));
                jRadioButtons[i] = new JRadioButton("可见性");
                jButtons1[i] = new JButton("删除");
                jButtons2[i] = new JButton("替换");
                jRadioButtons[i].addActionListener(listener);
                jButtons1[i].addActionListener(listener);
                jButtons2[i].addActionListener(listener);
                getContentPane().add(jLabels[i]);
                getContentPane().add(jRadioButtons[i]);
                getContentPane().add(jButtons1[i]);
                getContentPane().add(jButtons2[i]);
            }
            jRadioButtons[0].setEnabled(false);
            jButtons1[0].setText("清空");
            jButton.addActionListener(listener);
            getContentPane().add(jButton);
            load(size);
            repaint();
            SetCoverageDilog.this.repaint();
        }

        public void load(int size) {
            loadSize(size);
            loadZhi(size);
        }

        class Listener implements ActionListener {
            private static boolean isExchange;
            static int next = -1;
            static int zt;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() instanceof  JRadioButton){
                    int i=0;
                    while(jRadioButtons[i]!=e.getSource()&&i<jRadioButtons.length){
                        i++;
                    }
                    coverageS.setVia(i);
                }
                if (e.getSource() instanceof JButton) {
                    JButton button = (JButton) e.getSource();
                    if (button.getText().equals("添加图层")) {
                        coverageS.add();
                        zt=1;
                    } else if (button.getText().equals("删除")) {
                        int i = 0;
                        while (jButtons1[i] != button && i < jButtons1.length) {
                            i++;
                        };
                        coverageS.delete(i);
                        zt=2;
                    } else if (button.getText().equals("替换")) {
                        int i = 0;
                        while (jButtons2[i] != button && i < jButtons2.length) {
                            i++;
                        };
                        if (!isExchange) {
                            isExchange = true;
                            next = i;
                        }
                        else {
                            isExchange = false;
                            coverageS.exchange(next, i);
                        }
                    }else if (button.getText().equals("清空")) {
                        coverageS.getCoveragesList().get(0).deleteAll();
                    }
                    CoveragePanel.this.reLoad();
                    zt=0;
                }
            }
        }
    }


}

