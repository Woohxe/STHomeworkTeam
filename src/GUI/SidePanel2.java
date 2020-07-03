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
 * 游戏操作功能区界面，展示相应关卡自动寻路的解法，可选择重新开始、结束本局、自动寻路功能
 */
public class SidePanel2 extends JPanel implements ActionListener {
    //功能区框架
    private SidePanel sidePanel;
    //主游戏框架
    private MainFrame mainFrame;
    //计时器
    public GameTimer timerCount;
    //本局游戏所花总时间
    private String totalTime;
    //当前地图人物块的二维矩阵
    private int stepMap[][];
    //当前地图人物块对应的矩形块
    private Cell[] cells;
    //当前自动寻路状态
    private boolean autoStatus;
    //自动寻路解法的展示模块
    public static JPanel recordTextArea;

    /**
     * 构造方法，初始化游戏操作功能区
     * @param sidePanel
     * @param mainFrame
     */
    public SidePanel2(SidePanel sidePanel, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.sidePanel = sidePanel;
        //自动寻路解法记录框
        JPanel recordPanel = new JPanel();
        recordPanel.setPreferredSize(new Dimension(280,350));
        recordPanel.setBorder(new EtchedBorder());
        recordPanel.setBackground(new Color(255, 255, 255));
        recordPanel.setLayout(null);
        JLabel recordLable = new JLabel("解法步骤：",JLabel.CENTER);
        recordLable.setBounds(0,0,280,15);
        //创建滚动面板
        JScrollPane recordSroll=new JScrollPane();
        recordSroll.setBounds(0,15,280,350);
        //创建文本域
        recordTextArea = new JPanel();
        recordTextArea.setPreferredSize(new Dimension(260, 1000));
        recordSroll.getViewport().add(recordTextArea);
        recordSroll.validate();
        recordPanel.add(recordLable);
        recordPanel.add(recordSroll);
        add(recordPanel);
        //重新开始按钮
        JPanel resetPanel = buildButtonPanel("重新开始", 280, 50);
        add(resetPanel);
        //自动寻路按钮
        JPanel autoPanel = buildButtonPanel("自动寻路", 280, 50);
        add(autoPanel);
        //结束本局按钮
        JPanel returnPanel = buildButtonPanel("结束本局", 280, 50);
        add(returnPanel);
        //计时器，并初始设置为0
        timerCount = new GameTimer();
        timerCount.lab.setText("00:00:00");
        add(timerCount);
        //初始该界面不可见
        this.setEnabled(false);
        this.setVisible(false);
    }


    /**
     * 游戏操作功能区各按钮监听器
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //触发结束本局按钮
        if (e.getActionCommand().equals("结束本局")) {
            //暂停计时
            timerCount.getTimer().stop();
            //点击“是”后切换回关卡选择界面
            if (JOptionPane.showConfirmDialog(mainFrame, "确定结束本局游戏？（本局将不记录成绩）", "提示",
                    JOptionPane.INFORMATION_MESSAGE) == JOptionPane.OK_OPTION) {
                //若正在自动寻路，结束自动寻路线程
                autoStatus=false;
                //totalTime = timerCount.getLab().getText(); //记录时间
                recordTextArea.removeAll();
                recordTextArea.setPreferredSize(new Dimension(260, 1000));
                recordTextArea.repaint();
                turnPanel();
            }
            //点击“否”后计时器继续计时
            else{
                timerCount.getTimer().start();
            }
        }
        //触发重新开始按钮
        else if (e.getActionCommand().equals("重新开始")) {
            //暂停计时
            timerCount.getTimer().stop();
            int res=JOptionPane.showConfirmDialog(mainFrame, "是否重新开始", "提示", JOptionPane.YES_NO_OPTION);
            //点击“是”后刷新游戏操作界面，计时器重新开始计时
            if(res==JOptionPane.YES_OPTION){
                //若正在自动寻路，结束自动寻路线程
                autoStatus=false;
                recordTextArea.removeAll();
                recordTextArea.setPreferredSize(new Dimension(260, 1000));
                recordTextArea.repaint();
                //重新设置游戏操作区
                mainFrame.getSidePanel().getSidePanel1().startTimeGame();
            }
            //点击“否”后计时器继续计时
            else{
                timerCount.getTimer().start();
            }
        }
        //触发自动寻路按钮
        else if (e.getActionCommand().equals("自动寻路")) {
            //自动寻路状态设置为true
            autoStatus=true;
            //当前地图人物块的矩形块
            cells = mainFrame.getGamePanel().getGamePanel2().getGameStatus().getCells();
            //建立当前地图人物块的二维数组并初始化为-1
            stepMap = new int[5][4];
            for(int i=0;i<5;i++){
                for(int j=0;j<4;j++) {
                    stepMap[i][j]=-1;
                }
            }
            //空格数量，用以设置二维数组的空格位置
            int blankNum=0;
            //获取当前cell的左上角坐标和大小，并将人物块所占位置的二维数组位置设置为其编号
            for(int i=0;i<10;i++){
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
            //遍历二维数组，找到当前仍是初始值的位置，设置为空格，编号为10，11
            for(int i=0;i<5;i++){
                for(int j=0;j<4;j++) {
                    if(stepMap[i][j]==-1){
                        stepMap[i][j]=10+blankNum;
                        blankNum++;
                    }
                    if(blankNum==2){
                        break;
                    }
                }
                //总共两个空格，遍历到了就结束
                if(blankNum==2){
                    break;
                }
            }
            //格局二维数组得到的路径结果，ret[0][0][0]存放的是自动寻路所需的步数
            int[][][] ret=query(stepMap);
            recordTextArea.setPreferredSize(new Dimension(260, ret[0][0][0]*20));
            recordTextArea.validate();
            //刷新解法步骤模块
            recordTextArea.repaint();
            //自动寻路线程，实现动画效果
            new Thread(new Runnable(){
                public void run(){
                    //如果当前自动寻路状态为true，即开始动画
                    for(int i=1;i<=ret[0][0][0]+1&&autoStatus;i++){
                        int a[][]=ret[i];
                        mainFrame.getGamePanel().getGamePanel2().getGameStatus().setMapStep(a);
                        mainFrame.getGamePanel().getGamePanel2().refresh();
                        try{
                            //每隔0.26s走一步
                            Thread.sleep(260);
                        }
                        catch(Exception ex){
                        }
                    }
                    //动画结束后通关，提示自动寻路结束
                    if(autoStatus) {
                        JOptionPane.showMessageDialog(mainFrame,"曹操跑了！","提示",
                                JOptionPane.INFORMATION_MESSAGE);
                        recordTextArea.removeAll();
                        recordTextArea.setPreferredSize(new Dimension(260, 1000));
                        recordTextArea.repaint();
                        //返回关卡选择界面
                        turnPanel();
                    }
                }
            }).start();
        }//自动寻路结束

    }

    /**
     * 根据名字和宽高创建按钮
     * @param name
     * @param width
     * @param height
     * @return
     */
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

    /**
     * 关卡选择与游戏操作相应的功能区与操作区的界面切换
     */
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

    public GameTimer getTimerCount() {
        return timerCount;
    }

    public JPanel getRecordTextArea() {
        return recordTextArea;
    }

    public void setRecordTextArea(JPanel recordTextArea) {
        this.recordTextArea = recordTextArea;
    }

}