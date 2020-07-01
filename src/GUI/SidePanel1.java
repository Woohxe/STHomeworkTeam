package GUI;

import listener.BeginListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

/**
 * Created by lero on 2020/6/30.
 */
public class SidePanel1 extends JPanel implements ActionListener {
    private SidePanel sidePanel;
    private MainFrame mainFrame;
    private BeginListener beginListener;
    private JPanel tiredPanel, freePanel, rankPanel, returnPanel;
    public JLabel timeLabel = new JLabel("--:--:--");;

    public SidePanel1(SidePanel sidePanel,MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.sidePanel = sidePanel;
        //预览框
        JPanel forePanel = new JPanel();
        forePanel.setPreferredSize(new Dimension(280,350));
        forePanel.setBorder(new EtchedBorder());
        forePanel.setBackground(new Color(255, 255, 255));
        forePanel.setLayout(null);
        JLabel foreLable = new JLabel("地图预览：",JLabel.CENTER);
        foreLable.setBounds(0,0,280,15);
        forePanel.add(foreLable);
        add(forePanel);
        //计时模式
        tiredPanel=buildButtonPanel("计时模式", 280, 50);
        add(tiredPanel);
        //自由模式
        freePanel=buildButtonPanel("自由模式", 280, 50);
        add(freePanel);
        //查看排行
        rankPanel=buildButtonPanel("查看排行", 280, 50);
        add(rankPanel);
        //返回
        returnPanel=buildButtonPanel("返回", 280, 50);
        add(returnPanel);
        this.setEnabled(false);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("计时模式")||e.getActionCommand().equals("自由模式")) {
            if(e.getActionCommand().equals("计时模式")) {
                int res=JOptionPane.showConfirmDialog(mainFrame, "点击确认开始计时", "", JOptionPane.YES_NO_OPTION);
                if(res==JOptionPane.YES_OPTION){//点击“是”后执行这个代码块
                    sidePanel.getSidePanel2().getTimerCount().refreshTime();
                    turnPanel();
                    sidePanel.getSidePanel2().getTimerCount().getTimer().start();
                }
            }
        } else if (e.getActionCommand().equals("查看排行")) {

        } else if (e.getActionCommand().equals("返回")) {
            //已在buildButtonPanel特殊说明
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

    //时间
    private class UpdateTask implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String timerstr = sdf.format(System.currentTimeMillis());
            timeLabel.setText(timerstr);
        }
    }

    //切换界面
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
}

