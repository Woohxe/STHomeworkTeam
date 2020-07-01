package GUI;

import listener.BeginListener;
import timer.GameTimer;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lero on 2020/6/30.
 */
public class SidePanel2 extends JPanel implements ActionListener {
    public GameTimer timerCount;
    private String totalTime;
    private SidePanel sidePanel;
    private MainFrame mainFrame;
    private BeginListener beginListener;

    public GameTimer getTimerCount() {
        return timerCount;
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
        JLabel recordLable = new JLabel("历史记录：",JLabel.CENTER);
        recordLable.setBounds(0,0,280,15);
        JScrollPane recordSroll=new JScrollPane(); // 创建滚动面板
        recordSroll.setBounds(0,15,280,350);
        JPanel recordTextArea = new JPanel();//创建文本域
        recordTextArea.setPreferredSize(new Dimension(260, 700));
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
            if (JOptionPane.showConfirmDialog(mainFrame, "确定结束本局游戏?", "提示",
                    JOptionPane.INFORMATION_MESSAGE) == JOptionPane.OK_OPTION) {
                timerCount.getTimer().stop();
                totalTime = timerCount.getLab().getText(); //记录时间
                System.out.println(totalTime);
//                    try {
//                        Thread.sleep(1000 * 2);
//                    } catch (InterruptedException e1) {
//                        e1.printStackTrace();
//                        throw new RuntimeException(e1);
//                    }
                turnPanel();
            }
        } else if (e.getActionCommand().equals("重新开始")) {
            timerCount.refreshTime();
            //刷新右侧
        } else if (e.getActionCommand().equals("自动寻路")) {

        }

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