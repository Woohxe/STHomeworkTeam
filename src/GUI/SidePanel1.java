package GUI;

import commons.GameConfiguration;
import commons.GameStatus;
import listener.BeginListener;
import object.Cell;
import object.TimeModeRecord;
import utils.TimeRecordReader;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

/**
 * 关卡选择功能区界面，展示相应关卡的地图缩略图，可选择游戏模式与查看排行榜
 */
public class SidePanel1 extends JPanel implements ActionListener {
    //功能区框架
    private SidePanel sidePanel;
    //主游戏框架
    private MainFrame mainFrame;
    //模式选择按钮监听器
    private BeginListener beginListener;
    //关卡选择功能区各按钮
    private JPanel tiredPanel, rankPanel, returnPanel;
    //排行榜
    private RankDialog rankDialog;
    //地图缩略图模块
    private JPanel forePanel;
    //计时标签
    public JLabel timeLabel = new JLabel("--:--:--");;

    /**
     * 构造方法，初始化关卡选择功能区
     * @param sidePanel
     * @param mainFrame
     */
    public SidePanel1(SidePanel sidePanel, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.sidePanel = sidePanel;
        //地图缩略图预览框
        forePanel = new JPanel();
        forePanel.setLayout(null);
        forePanel.setPreferredSize(new Dimension(280,365));
        forePanel.setBorder(new EtchedBorder());
        forePanel.setBackground(new Color(255, 255, 255));
        JLabel foreLable = new JLabel("地图预览：",JLabel.CENTER);
        foreLable.setBounds(0,345,280,15);
        forePanel.add(foreLable);
        add(forePanel);
        //计时模式按钮
        tiredPanel=buildButtonPanel("计时模式", 280, 50);
        add(tiredPanel);
        //查看排行按钮
        rankPanel=buildButtonPanel("查看排行", 280, 50);
        add(rankPanel);
        //返回按钮
        returnPanel=buildButtonPanel("返回", 280, 50);
        add(returnPanel);
        this.setEnabled(false);
    }

    /**
     * 关卡选择功能区各按钮监听器
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //触发计时模式按钮
        if (e.getActionCommand().equals("计时模式")) {
                int res=JOptionPane.showConfirmDialog(mainFrame, "点击确认开始计时", "", JOptionPane.YES_NO_OPTION);
                //点击“是”后计时器开始计时，并切换界面
                if(res==JOptionPane.YES_OPTION){
                    startTimeGame();
                }
        }
        //触发查看排行按钮
        else if (e.getActionCommand().equals("查看排行")) {
            try {
                //获取当前选择的关卡号
                int gameId = mainFrame.getGamePanel().getGameStatus().getGameConfiguration().getGameId();
                //根据关卡号得到对应的排行榜
                rankDialog = new RankDialog(mainFrame,gameId);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
        //触发返回按钮
        else if (e.getActionCommand().equals("返回")) {
            //已在buildButtonPanel特殊说明
        }

    }

    /**
     * 开始游戏，切换相关界面，计时器开始计时
     */
    public void startTimeGame() {
        GamePanel gamePanel = mainFrame.getGamePanel();
        //计时器初始化为0
        sidePanel.getSidePanel2().getTimerCount().refreshTime();
        //更新游戏配置当前关卡的地图
        gamePanel.getGameStatus().getGameConfiguration().initMap();
        //更新地图状态里面的二维数组
        gamePanel.getGameStatus().setMapStep(gamePanel.getGameStatus().getGameConfiguration().getMapStep());
        //更新地图状态里面cells的位置
        gamePanel.getGameStatus().initPanel();
        //更新游戏操作界面
        mainFrame.getGamePanel().getGamePanel2().refresh();
        //切换界面
        turnPanel();
        //计时器开始计时
        sidePanel.getSidePanel2().getTimerCount().getTimer().start();
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
        //返回按钮单独设置监听事件
        if(name == "返回")
        {
            beginListener = new BeginListener(mainFrame);
            button.addMouseListener(beginListener);
        }
        button.setPreferredSize(new Dimension(width,height));
        button.setBorderPainted(false);
        button.setBackground(new Color(255, 255, 255));
        panel.add(button);
        return panel;
    }

    /**
     * 时间监听器，可实时更新时间，并转换格式
     */
    private class UpdateTask implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String timerstr = sdf.format(System.currentTimeMillis());
            timeLabel.setText(timerstr);
        }
    }

    /**
     * 关卡选择与游戏操作相应的功能区与操作区的界面切换
     */
    public void turnPanel(){
        sidePanel.getSidePanel1().setEnabled(false);
        sidePanel.getSidePanel1().setVisible(false);
        sidePanel.getSidePanel2().setVisible(true);
        sidePanel.getSidePanel2().setEnabled(true);
        mainFrame.getGamePanel().getGamePanel1().setEnabled(false);
        mainFrame.getGamePanel().getGamePanel1().setVisible(false);
        mainFrame.getGamePanel().getGamePanel2().setVisible(true);
        mainFrame.getGamePanel().getGamePanel2().setEnabled(true);

    }

    public JPanel getForePanel() {
        return forePanel;
    }
    public void setForePanel(JPanel forePanel) {
        this.forePanel = forePanel;
    }

}

