package GUI;

import commons.GameConfiguration;
import commons.GameStatus;
import listener.BeginListener;
import listener.DragListener;
import object.Cell;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Integer.parseInt;

/**
 * 关卡选择界面，实现关卡选择与功能区对应的缩略图显示
 */
public class GamePanel1 extends JPanel{
    //游戏区框架
    private GamePanel gamePanel;
    //主游戏框架
    private MainFrame mainFrame;
    //默认选择的关卡号码
    private int chooseNum = 0;

    /**
     * 构造方法，传入主游戏框架与游戏区框架，生成关卡选择框
     * @param gamePanel
     * @param mainFrame
     */
    public GamePanel1(GamePanel gamePanel, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.gamePanel = gamePanel;
        this.setLayout(null);
        this.setBackground(new Color(216, 255, 222));
        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(580,800));
        //单选框组
        ButtonGroup group = new ButtonGroup();
        for(int i=0;i<11;i++){
            JPanel radioPanel = new JPanel();
            radioPanel.setBackground(new Color(245, 255, 248));
            radioPanel.setBorder(new EtchedBorder());
            JRadioButton c;
            //默认选择第一关
            if(i == 0) {
                c = new JRadioButton(""+i+"",true);
            } else {
                c = new JRadioButton(""+i+"");
            }
            //给单选框添加监听事件
            RadioButtonListener radioButtonListener = new RadioButtonListener();
            c.addActionListener(radioButtonListener);
            c.setBackground(new Color(245, 255, 248));
            radioPanel.setPreferredSize(new Dimension(180,40));
            group.add(c);
            radioPanel.add(c);
            container.add(radioPanel);
        }
        //创建滚动面板，将单选框加入
        JScrollPane recordSroll=new JScrollPane();
        recordSroll.setBounds(0,0,150*4,150*5);
        recordSroll.getViewport().add(container);
        recordSroll.validate();
        this.add(recordSroll);
    }

    /**
     * 单选框监听器，实现关卡号选择之后对应地图的变化
     */
    private class RadioButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton radio=(JRadioButton)e.getSource();
            if(radio.isSelected()){
                chooseNum = parseInt(radio.getText());
                //更新游戏配置中当前选中的关卡号
                gamePanel.getGameStatus().getGameConfiguration().setGameId(chooseNum);
                //更新游戏配置当前关卡的地图
                gamePanel.getGameStatus().getGameConfiguration().initMap();
                //更新地图状态里面的二维数组
                gamePanel.getGameStatus().setMapStep(gamePanel.getGameStatus().getGameConfiguration().getMapStep());
                //更新地图状态里面cells的位置
                gamePanel.getGameStatus().initPanel();
                //更新游戏操作界面
                mainFrame.getGamePanel().getGamePanel2().refresh();
                //绘制功能区地图缩略图
                paintForePanel(mainFrame.getSidePanel().getSidePanel1().getForePanel());
            }
        }
    }

    /**
     * 绘制功能区的地图缩略图，实现关卡与缩略图同步更新
     * @param forePanel
     */
    public void paintForePanel(JPanel forePanel) {
        forePanel.removeAll();
        forePanel.setLayout(null);
        forePanel.setEnabled(false);
        forePanel.setVisible(true);

        JLabel foreLable = new JLabel("地图预览：",JLabel.CENTER);
        foreLable.setBounds(0,345,280,15);
        forePanel.add(foreLable);
        //得到当前关卡的地图
        PersonLabel[] pl = new PersonLabel[10];
        Cell[] cells=new Cell[10];
        for(int i = 0; i < 10; i++) {
            //按比例缩小地图
            cells[i] =new Cell(gamePanel.getGameStatus().getCells()[i],0.46);
            pl[i] = new PersonLabel(cells[i]);
            pl[i].setBorder(new EtchedBorder());
            pl[i].setBounds(pl[i].getX(),pl[i].getY(),pl[i].getWidth(),pl[i].getHeight());
            forePanel.add(pl[i]);
        }
        forePanel.validate();
        forePanel.repaint();
    }

    public int getChooseNum() {
        return chooseNum;
    }

    public void setChooseNum(int chooseNum) {
        this.chooseNum = chooseNum;
    }
}
