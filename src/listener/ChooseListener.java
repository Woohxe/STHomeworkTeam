package listener;

import GUI.GamePanel;
import GUI.MainFrame;
import GUI.SidePanel;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Timer;

/**
 * 继承MouseInputAdapter的监听器，实现点击“选择关卡”、“自定义地图”、“返回”按钮后的界面跳转
 */
public class ChooseListener extends MouseInputAdapter {
    //主游戏框架
    private MainFrame mainFrame;
    //游戏区框架
    private GamePanel gamePanel;
    //功能区框架
    private SidePanel sidePanel;
    //计时器标签
    private JLabel timeLabel;
    //计时器
    private Timer timer;

    /**
     * 构造方法
     * @param mainFrame
     */
    public ChooseListener(MainFrame mainFrame){
        this.mainFrame=mainFrame;
    }

    /**
     * 用户点击按钮时触发此方法，根据按钮名字切换界面
     * @param e
     */
    public void mousePressed(MouseEvent e) {
        gamePanel = new GamePanel(mainFrame);
        sidePanel = new SidePanel(mainFrame);
        JButton button = (JButton) e.getSource();
        //获取触发此方法的按钮的名字
        String text = button.getText();
        //点击的是“选择关卡”按钮
        if (text == "选择关卡") {
            mainFrame.setSidePanel(sidePanel);
            mainFrame.setGamePanel(gamePanel);
            // 重复的按开始按钮：首先必须清除上面的组件
            mainFrame.remove(mainFrame.getBtnPanel());
            //添加功能区框架到内置容器
            mainFrame.add(sidePanel,BorderLayout.WEST);
            //添加游戏区框架到内置容器
            mainFrame.add(gamePanel, BorderLayout.CENTER);
            JButton returnBtn = new JButton("返回");
            BeginListener beginListener = new BeginListener(mainFrame);
            returnBtn.addMouseListener(beginListener);
            sidePanel.add(returnBtn,BorderLayout.SOUTH);
        }
        //点击的是“返回”按钮
        if (text == "返回") {
            mainFrame.remove(mainFrame.getBtnPanel());
            mainFrame.initialize();
        }
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.validate();
    }
}