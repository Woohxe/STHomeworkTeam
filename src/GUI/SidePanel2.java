package GUI;

import commons.GameConfiguration;
import commons.GameStatus;
import huarongdao.Blocks;
import huarongdao.Movement;
import huarongdao.Solver;
import listener.BeginListener;
import object.Cell;
import timer.GameTimer;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static huarongdao.Solver.query;


/**
 * Created by lero on 2020/6/30.
 */
public class SidePanel2 extends JPanel implements ActionListener {
    public GameTimer timerCount;
    private String totalTime;
    private SidePanel sidePanel;
    private MainFrame mainFrame;
    private int stepMap[][];
    private Cell[] cells;
    private BeginListener beginListener;
    private boolean autoStatus;//当前自动寻路状态
    public static JPanel recordTextArea;

    public GameTimer getTimerCount() {
        return timerCount;
    }

    public JPanel getRecordTextArea() {
        return recordTextArea;
    }

    public void setRecordTextArea(JPanel recordTextArea) {
        this.recordTextArea = recordTextArea;
    }

    public SidePanel2(SidePanel sidePanel, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.sidePanel = sidePanel;
        //记录框
        JPanel recordPanel = new JPanel();
        recordPanel.setPreferredSize(new Dimension(280,350));
        recordPanel.setBorder(new EtchedBorder());
        recordPanel.setBackground(new Color(255, 255, 255));
        recordPanel.setLayout(null);
        JLabel recordLable = new JLabel("解法步骤：",JLabel.CENTER);
        recordLable.setBounds(0,0,280,15);
        JScrollPane recordSroll=new JScrollPane(); // 创建滚动面板
        recordSroll.setBounds(0,15,280,350);
        recordTextArea = new JPanel();//创建文本域
        recordTextArea.setPreferredSize(new Dimension(260, 1000));
        recordSroll.getViewport().add(recordTextArea);
        recordSroll.validate();
        recordPanel.add(recordLable);
        recordPanel.add(recordSroll);
        add(recordPanel);
        //重新开始
        JPanel resetPanel = buildButtonPanel("重新开始", 280, 50);
        add(resetPanel);
        //自动寻路
        JPanel autoPanel = buildButtonPanel("自动寻路", 280, 50);
        add(autoPanel);
        //返回
        JPanel returnPanel = buildButtonPanel("结束本局", 280, 50);
        add(returnPanel);
        timerCount = new GameTimer();
        timerCount.lab.setText("00:00:00");
        add(timerCount);
        this.setEnabled(false);
        this.setVisible(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand().equals("结束本局")) {
            timerCount.getTimer().stop();
            if (JOptionPane.showConfirmDialog(mainFrame, "确定结束本局游戏？（本局将不记录成绩）", "提示",
                    JOptionPane.INFORMATION_MESSAGE) == JOptionPane.OK_OPTION) {
                autoStatus=false;
                timerCount.getTimer().stop();
                totalTime = timerCount.getLab().getText(); //记录时间
                recordTextArea.removeAll();
                recordTextArea.setPreferredSize(new Dimension(260, 1000));
                recordTextArea.repaint();
                turnPanel();
            }
            else{
                timerCount.getTimer().start();
            }
        } else if (e.getActionCommand().equals("重新开始")) {
            timerCount.getTimer().stop();
            int res=JOptionPane.showConfirmDialog(mainFrame, "是否重新开始", "提示", JOptionPane.YES_NO_OPTION);
            if(res==JOptionPane.YES_OPTION){
                autoStatus=false;
                recordTextArea.removeAll();
                recordTextArea.setPreferredSize(new Dimension(260, 1000));
                recordTextArea.repaint();
                mainFrame.getSidePanel().getSidePanel1().startTimeGame();//重新设置游戏区
            }else{
                timerCount.getTimer().start();
            }
        } else if (e.getActionCommand().equals("自动寻路")) {
            autoStatus=true;
            //得到二维数组
            cells = mainFrame.getGamePanel().getGamePanel2().getGameStatus().getCells();
            stepMap = new int[5][4];
            int blankNum=0;//空格
            for(int i=0;i<5;i++){
                for(int j=0;j<4;j++) {
                    stepMap[i][j]=-1;
                }
            }
            for(int i=0;i<10;i++){//获取当前cell的左上角坐标和大小
                int c=cells[i].getColumn();
                int r=cells[i].getRow();
                int w=cells[i].getWidth()/150;
                int h=cells[i].getHeight()/150;
                for(int m=0;m<h;m++) {
                    for(int n=0;n<w;n++) {
                        stepMap[r+m][c+n]=i;
                    }
                }
            }
            for(int i=0;i<5;i++){//设置空格在二维数组里面的数字
                for(int j=0;j<4;j++) {
                    if(stepMap[i][j]==-1){
                        stepMap[i][j]=10+blankNum;
                        blankNum++;
                    }
                    if(blankNum==2){
                        break;
                    }
                }
                if(blankNum==2){
                    break;
                }
            }
            int[][][] ret=query(stepMap);//得到的路径结果
            recordTextArea.setPreferredSize(new Dimension(260, ret[0][0][0]*20));
            recordTextArea.validate();
            recordTextArea.repaint();//刷新棋谱
            new Thread(new Runnable(){
                public void run(){
                    for(int i=1;i<=ret[0][0][0]+1&&autoStatus;i++){
                        int a[][]=ret[i];
                        mainFrame.getGamePanel().getGamePanel2().getGameStatus().setMapStep(a);
                        mainFrame.getGamePanel().getGamePanel2().refresh();
                        try{
                            Thread.sleep(260);
                        }
                        catch(Exception ex){
                        }
                    }
                    if(autoStatus) {
                        JOptionPane.showMessageDialog(mainFrame,"曹操跑了！","提示",
                                JOptionPane.INFORMATION_MESSAGE);
                        recordTextArea.removeAll();
                        recordTextArea.setPreferredSize(new Dimension(260, 1000));
                        recordTextArea.repaint();
                        turnPanel();//相当于返回
                    }
                }
            }).start();
        }//自动寻路结束

    }

    //创建按钮
    private JPanel buildButtonPanel(String name, int width, int height) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(280,60));
        panel.setBorder(new EtchedBorder());
        panel.setBackground(new Color(255, 255, 255));
        JButton button = new JButton(name);
        button.addActionListener(this);
        button.setActionCommand(name);
        button.setPreferredSize(new Dimension(width,height));
        button.setBorderPainted(false);
        button.setBackground(new Color(255, 255, 255));
        panel.add(button);
        return panel;
    }

    public void turnPanel() {
        sidePanel.getSidePanel2().setEnabled(false);
        sidePanel.getSidePanel2().setVisible(false);
        sidePanel.getSidePanel1().setVisible(true);
        sidePanel.getSidePanel1().setEnabled(true);
        mainFrame.getGamePanel().getGamePanel2().setEnabled(false);
        mainFrame.getGamePanel().getGamePanel2().setVisible(false);
        mainFrame.getGamePanel().getGamePanel1().setEnabled(true);
        mainFrame.getGamePanel().getGamePanel1().setVisible(true);
    }

}