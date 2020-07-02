package GUI;

import commons.GameConfiguration;
import commons.GameStatus;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lero on 2020/6/30.
 */
public class GamePanel extends JPanel{
    private GamePanel1 gamePanel1;
    private GamePanel2 gamePanel2;
    private MainFrame mainFrame;
    private GameStatus gameStatus;
    private JButton go;
    public int BASELENGTH = 150; // 单位块长度

    public int getBASELENGTH() {
        return BASELENGTH;
    }

    public void setBASELENGTH(int BASELENGTH) {
        this.BASELENGTH = BASELENGTH;
    }

    public MainFrame getMainFrame() {
        return (MainFrame) this.getParent();
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GamePanel1 getGamePanel1() {
        return gamePanel1;
    }

    public void setGamePanel1(GamePanel1 gamePanel1) {
        this.gamePanel1 = gamePanel1;
    }

    public GamePanel2 getGamePanel2() {
        return gamePanel2;
    }

    public void setGamePanel2(GamePanel2 gamePanel2) {
        this.gamePanel2 = gamePanel2;
    }


    public GamePanel(MainFrame mainFrame1){
        mainFrame = mainFrame1;
        this.setPreferredSize(new Dimension(BASELENGTH*4+20,BASELENGTH*5+50));
        this.setBackground(new Color(198, 241, 245));
        this.setLayout(null);

        gamePanel1 = new GamePanel1(this,mainFrame);
        gameStatus = new GameStatus(new GameConfiguration(gamePanel1));
        gamePanel2 = new GamePanel2(this,mainFrame,gameStatus);
        gamePanel1.setBounds(0,0,BASELENGTH*4+20,BASELENGTH*5+50);
        gamePanel2.setBounds(0,0,BASELENGTH*4+20,BASELENGTH*5+50);
        this.add(gamePanel1);
        gamePanel1.setEnabled(true);
        this.add(gamePanel2);
        this.setVisible(true);

    }
}
