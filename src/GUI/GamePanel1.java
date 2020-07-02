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
 * Created by lero on 2020/6/30.
 */
public class GamePanel1 extends JPanel{
    private GamePanel gamePanel;
    private MainFrame mainFrame;
    private int chooseNum = 0;
    private BeginListener beginListener;

    public int getChooseNum() {
        return chooseNum;
    }

    public void setChooseNum(int chooseNum) {
        this.chooseNum = chooseNum;
    }

    public GamePanel1(GamePanel gamePanel, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.gamePanel = gamePanel;
        this.setLayout(null);
        this.setBackground(new Color(216, 255, 222));
        ButtonGroup group = new ButtonGroup();
        JPanel container = new JPanel();
        container.setPreferredSize(new Dimension(580,800));
        for(int i=0;i<11;i++){
            JPanel radioPanel = new JPanel();
            radioPanel.setBackground(new Color(245, 255, 248));
            radioPanel.setBorder(new EtchedBorder());
            JRadioButton c;
            if(i == 0) {
                c = new JRadioButton(""+i+"",true);//默认选择第一关
            } else {
                c = new JRadioButton(""+i+"");
            }
            RadioButtonListener radioButtonListener = new RadioButtonListener();
            c.addActionListener(radioButtonListener);
            c.setBackground(new Color(245, 255, 248));
            radioPanel.setPreferredSize(new Dimension(180,40));
            group.add(c);
            radioPanel.add(c);
            container.add(radioPanel);
        }
        JScrollPane recordSroll=new JScrollPane(); // 创建滚动面板
        recordSroll.setBounds(0,0,150*4,150*5);
        recordSroll.getViewport().add(container);
        recordSroll.validate();
        this.add(recordSroll);
    }

    //选关
    private class RadioButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JRadioButton radio=(JRadioButton)e.getSource();
            if(radio.isSelected()){
                chooseNum = parseInt(radio.getText());
                gamePanel.getGameStatus().getGameConfiguration().setGameId(chooseNum);//当前选中的关卡号
                gamePanel.getGameStatus().getGameConfiguration().initMap();//更新当前关卡的地图
                gamePanel.getGameStatus().setMapStep(gamePanel.getGameStatus().getGameConfiguration().getMapStep());//更新状态里面的地图
                gamePanel.getGameStatus().initPanel();//更新cells的位置
                mainFrame.getGamePanel().getGamePanel2().refresh();//更新界面
                paintForePanel(mainFrame.getSidePanel().getSidePanel1().getForePanel());
            }
        }
    }

    //绘制缩略图
    public void paintForePanel(JPanel forePanel) {
        forePanel.removeAll();
        JLabel foreLable = new JLabel("地图预览：",JLabel.CENTER);
        foreLable.setBounds(0,345,280,15);
        forePanel.add(foreLable);
        PersonLabel[] pl = new PersonLabel[10];
        int BASELENGTH = 70;

        forePanel.setLayout(null);
        forePanel.setEnabled(false);
        forePanel.setVisible(true);

        Cell[] cells=new Cell[10];
        for(int i = 0; i < 10; i++) {
            cells[i] =new Cell(gamePanel.getGameStatus().getCells()[i],0.46);
            pl[i] = new PersonLabel(cells[i]);
            pl[i].setBorder(new EtchedBorder());
            pl[i].setBounds(pl[i].getX(),pl[i].getY(),pl[i].getWidth(),pl[i].getHeight());
            forePanel.add(pl[i]);
        }
        forePanel.validate();
        forePanel.repaint();
    }
}
