package GUI;

import commons.GameStatus;
import listener.DragListener;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 游戏操作界面，实现根据关卡选择生成对应游戏界面
 */
public class GamePanel2 extends JPanel {
    //游戏区框架
    private GamePanel gamePanel;
    //主游戏框架
    private MainFrame mainFrame;
    //人物块的鼠标监听器
    private DragListener dragListener;
    //游戏状态
    private GameStatus gameStatus;
    //人物块
    private PersonLabel[] pl = new PersonLabel[10];

    /**
     * 构造方法，游戏操作界面初始化
     * @param gamePanel
     * @param mainFrame
     * @param gameStatus
     */
    public GamePanel2(GamePanel gamePanel, MainFrame mainFrame, GameStatus gameStatus) {
        //游戏配置当前设置的人物块单位长度
        int BASELENGTH = gameStatus.getGameConfiguration().getBaseLength();
        this.mainFrame = mainFrame;
        this.gamePanel = gamePanel;
        this.gameStatus = gameStatus;
        this.setLayout(null);
        this.setPreferredSize(new Dimension(BASELENGTH*4+20,BASELENGTH*5+40));
        this.setEnabled(false);
        this.setVisible(false);
        //更新加载各人物块
        refresh();
    }

    /**
     *更新加载各人物块的位置，并为每个人物块添加鼠标监听器
     */
    public void refresh() {
        this.removeAll();
        for(int i = 0; i < 10; i++) {
            pl[i] = new PersonLabel(gameStatus.getCells()[i]);
            pl[i].setBorder(new EtchedBorder());
            dragListener = new DragListener(pl[i],gamePanel,gameStatus,mainFrame);
            pl[i].addMouseListener(dragListener);
            pl[i].addMouseMotionListener(dragListener);
            //设置每个人物块的位置
            pl[i].setBounds(pl[i].getX(),pl[i].getY(),pl[i].getWidth(),pl[i].getHeight());
            this.add(pl[i]);
        }
        this.validate();
        this.repaint();
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public PersonLabel[] getPl() {
        return pl;
    }

}
