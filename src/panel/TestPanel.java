package panel;

import commons.GameConfiguration;
import commons.GameStatus;
import listener.DragListener;
import person.PersonLabel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lero on 2020/6/30.
 */
public class TestPanel extends JPanel implements ActionListener {
    private JButton back;
    private JButton go;
    private GamePanel gamePanel;
    private MainFrame mainFrame;
    private DragListener dragListener;
    private GameStatus gameStatus;
    private PersonLabel[] pl = new PersonLabel[10];
    public int BASELENGTH;
    Color normal = new Color(192, 205, 53);
    Color cao = new Color(205, 38, 38);
    Font fnt = new Font("黑体", Font.BOLD, 25);

    public TestPanel(GamePanel gamePanel, MainFrame mainFrame, GameStatus gameStatus) {
        this.mainFrame = mainFrame;
        this.gamePanel = gamePanel;
        this.gameStatus = gameStatus;
        this.BASELENGTH = gamePanel.getBASELENGTH();

        for(int i = 0; i < 10; i++) {

            pl[i] = new PersonLabel(gameStatus.getPersons()[i]);
            pl[i].setBorder(new EtchedBorder());
            dragListener = new DragListener(pl[i],gamePanel,gameStatus);
            pl[i].addMouseListener(dragListener);
            pl[i].addMouseMotionListener(dragListener);
            pl[i].setBounds(pl[i].getX(),pl[i].getY(),pl[i].getWidth(),pl[i].getHeight());
            this.add(pl[i]);
        }

        this.setLayout(null);
        this.setPreferredSize(new Dimension(BASELENGTH*4+20,BASELENGTH*5+40));
        this.setEnabled(false);
        this.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

