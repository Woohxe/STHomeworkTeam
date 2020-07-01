package GUI;

import listener.BeginListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lero on 2020/6/30.
 */
public class GamePanel1 extends JPanel implements ActionListener {
    private GamePanel gamePanel;
    private MainFrame mainFrame;
    private BeginListener beginListener;

    public GamePanel1(GamePanel gamePanel, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.gamePanel = gamePanel;
        this.setBackground(new Color(184, 245, 199));
        ButtonGroup group = new ButtonGroup();
        for(int i=0;i<100;i++){
            //JRadioButton c1 = new JRadioButton("面筋哥",true);//只传了两个参数
            JRadioButton c = new JRadioButton("闯关闯关");
            c.setBackground(new Color(245, 255, 248));
            c.setPreferredSize(new Dimension(190,100));
            group.add(c);
            this.add(c);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
