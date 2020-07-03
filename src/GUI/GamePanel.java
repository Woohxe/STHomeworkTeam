package GUI;

import commons.GameConfiguration;
import commons.GameStatus;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏区框架，实现关卡选择与游戏操作两个界面的初始化
 */
public class GamePanel extends JPanel{
    //关卡选择界面
    private GamePanel1 gamePanel1;
    //游戏操作界面
    private GamePanel2 gamePanel2;
    //主游戏框架
    private MainFrame mainFrame;
    //游戏状态
    private GameStatus gameStatus;
    // 块单位长度
    private int BASELENGTH = 150;

    /**
     * 构造方法，传入主游戏框架，初始化游戏区框架
     * @param mainFrame1
     */
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
        this.add(gamePanel2);
        gamePanel1.setEnabled(true);
        this.setVisible(true);
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

    public MainFrame getMainFrame() {
        return (MainFrame) this.getParent();
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getBASELENGTH() {
        return BASELENGTH;
    }

    public void setBASELENGTH(int BASELENGTH) {
        this.BASELENGTH = BASELENGTH;
    }

}
