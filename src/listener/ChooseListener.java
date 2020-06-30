package listener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Timer;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import panel.GamePanel;
import panel.*;

/**
 * 选择关卡还是自定义地图的按钮监听器
 */
public class ChooseListener extends MouseInputAdapter  {
    private JLabel timeLabel;//计时器标签
    private Timer timer;//计时
    private MainFrame mainFrame;
    private GamePanel gamePanel;//游戏面板
    private SidePanel sidePanel;

    public ChooseListener(MainFrame mainFrame){
        this.mainFrame=mainFrame;
    }

    public void mousePressed(MouseEvent e) {
        gamePanel = new GamePanel(mainFrame);
        sidePanel = new SidePanel(mainFrame);
        JButton button = (JButton) e.getSource();
        String text = button.getText();
        if (text == "选择关卡") {
            mainFrame.setGamePanel(gamePanel);
            mainFrame.setSidePanel(sidePanel);
            mainFrame.remove(mainFrame.getBtnPanel());
            // 重复的按开始按钮：首先必须清除上面的组件
            mainFrame.add(gamePanel,BorderLayout.CENTER);//添加游戏主面板到内置容器
            mainFrame.add(sidePanel,BorderLayout.WEST);//添加游戏辅助面板到内置容器
            JButton returnBtn = new JButton("返回");
            BeginListener beginListener = new BeginListener(mainFrame);
            returnBtn.addMouseListener(beginListener);
            sidePanel.add(returnBtn,BorderLayout.SOUTH);
        }
        if (text == "自定义地图") {

        }
        if (text == "返回") {
            mainFrame.remove(mainFrame.getBtnPanel());
            mainFrame.initialize();
        }
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.validate();
    }
}