package timer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 计时器
 */

public class GameTimer extends JPanel {
    public final Label lab;
    final Timer timer;
    Date now = new Date();

    public Label getLab() {
        return lab;
    }

    public Timer getTimer() {
        return timer;
    }

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
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                lab.setText(formatter.format(now));
            }
        });
        JButton startBtn = new JButton("继续");
        JButton endBtn = new JButton("暂停");
        startBtn.setBackground(new Color(255, 255, 255));
        endBtn.setBackground(new Color(255, 255, 255));
        startBtn.setVisible(false);
        endBtn.setPreferredSize(new Dimension(120,40));
        startBtn.setPreferredSize(new Dimension(120,40));

        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startBtn.setVisible(false);
                endBtn.setVisible(true);
                timer.start();
            }
        });
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

    public void refreshTime(){
        now.setHours(0);
        now.setMinutes(0);
        now.setSeconds(0);
        lab.setText("00:00:00");
    }
}
