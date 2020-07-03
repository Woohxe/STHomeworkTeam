package timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 计时器模块
 */
public class GameTimer extends JPanel {
    //计时器文字
    public final Label lab;
    //时间格式
    protected SimpleDateFormat formatter;
    //计时器
    final Timer timer;
    //当前时间
    Date now = new Date();

    /**
     * 构造方法，初始化计时器模块
     */
    public GameTimer() {
        now.setHours(0);
        now.setMinutes(0);
        now.setSeconds(0);
        this.setPreferredSize(new Dimension(280, 60));
        lab = new Label();
        lab.setFont(new Font("黑体",Font.BOLD,20));

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date now2 = new Date(now.getTime() + 1000);
                now = now2;
                formatter = new SimpleDateFormat("HH:mm:ss");
                lab.setText(formatter.format(now));
            }
        });
        //设置计时器旁边的继续暂停按钮
        JButton startBtn = new JButton("继续");
        JButton endBtn = new JButton("暂停");
        startBtn.setBackground(new Color(255, 255, 255));
        endBtn.setBackground(new Color(255, 255, 255));
        startBtn.setVisible(false);
        endBtn.setPreferredSize(new Dimension(120,40));
        startBtn.setPreferredSize(new Dimension(120,40));

        //给“继续”按钮绑定监听事件，切换至“暂停”按钮
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startBtn.setVisible(false);
                endBtn.setVisible(true);
                timer.start();
            }
        });

        //给“暂停”按钮绑定监听事件，切换至“继续”按钮
        endBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startBtn.setVisible(true);
                endBtn.setVisible(false);
                timer.stop();
            }
        });
        this.setLayout(new FlowLayout());
        this.add(endBtn);
        this.add(startBtn);
        this.add(lab);
        this.setVisible(true);
    }

    /**
     * 重置计时器时间
     */
    public void refreshTime(){
        now.setHours(0);
        now.setMinutes(0);
        now.setSeconds(0);
        lab.setText("00:00:00");
    }

    /**
     * 返回本局通关花费总时间，以s为单位
     * @return
     */
    public int getTotalTime() {
        return now.getHours()*3600+now.getMinutes()*60+now.getSeconds();
    }

    public Label getLab() {
        return lab;
    }

    public Timer getTimer() {
        return timer;
    }

}
