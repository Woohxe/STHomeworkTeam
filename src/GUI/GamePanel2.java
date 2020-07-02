package GUI;

import commons.GameStatus;
import listener.DragListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lero on 2020/6/30.
 */
public class GamePanel2 extends JPanel {
    private JButton back;
    private JButton go;
    private GamePanel gamePanel;
    private MainFrame mainFrame;
    private DragListener dragListener;
    private GameStatus gameStatus;
    private PersonLabel[] pl = new PersonLabel[10];
    //public int BASELENGTH;
    Color normal = new Color(192, 205, 53);
    Color cao = new Color(205, 38, 38);
    Font fnt = new Font("黑体", Font.BOLD, 25);

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GamePanel2(GamePanel gamePanel, MainFrame mainFrame, GameStatus gameStatus) {
        this.mainFrame = mainFrame;
        this.gamePanel = gamePanel;
        this.gameStatus = gameStatus;
        int BASELENGTH = gameStatus.getGameConfiguration().getBaseLength();

        for(int i = 0; i < 10; i++) {
            pl[i] = new PersonLabel(gameStatus.getCells()[i]);
            pl[i].setBorder(new EtchedBorder());
            dragListener = new DragListener(pl[i],gamePanel,gameStatus,mainFrame);
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

    public void refresh() {
        this.removeAll();
        for(int i = 0; i < 10; i++) {
            pl[i] = new PersonLabel(gameStatus.getCells()[i]);
            pl[i].setBorder(new EtchedBorder());
            dragListener = new DragListener(pl[i],gamePanel,gameStatus,mainFrame);
            pl[i].addMouseListener(dragListener);
            pl[i].addMouseMotionListener(dragListener);
            pl[i].setBounds(pl[i].getX(),pl[i].getY(),pl[i].getWidth(),pl[i].getHeight());
            this.add(pl[i]);
        }
        this.validate();
        this.repaint();
    }

    public PersonLabel[] getPl() {
        return pl;
    }
}
