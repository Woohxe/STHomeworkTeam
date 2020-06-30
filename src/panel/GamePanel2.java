package panel;

import commons.GameConfiguration;
import commons.GameStatus;
import listener.DragListener;
import person.Person;
import person.PersonCell;
import person.PersonLabel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel2 extends JPanel implements ActionListener {
    private JButton back;
    private JButton go;
    private GamePanel gamePanel;
    private MainFrame mainFrame;
    private GameStatus gameStatus;
    private DragListener[] dragListener= new DragListener[10];
    private PersonCell[] pc = new PersonCell[10];
    private Person[] pp = new Person[10];
    private PersonLabel[] pl = new PersonLabel[10];
    public int BASELENGTH;
    Color normal = new Color(192, 205, 53);
    Color cao = new Color(205, 38, 38);
    Font fnt = new Font("黑体", Font.BOLD, 25);

    public GamePanel2(GamePanel gamePanel, MainFrame mainFrame,GameStatus gameStatus) {
        this.mainFrame = mainFrame;
        this.gamePanel = gamePanel;
        this.BASELENGTH = gamePanel.getBASELENGTH();
        this.gameStatus = gameStatus;
        this.BASELENGTH = gamePanel.getBASELENGTH();
        pc[0] = new PersonCell(BASELENGTH,0,BASELENGTH*2, BASELENGTH*2,0,1);
        pc[1] = new PersonCell(0, 0, BASELENGTH, BASELENGTH*2, 0,0);
        pc[2] = new PersonCell(BASELENGTH*3,0,  BASELENGTH, BASELENGTH*2, 0,3);
        pc[3] = new PersonCell(0,BASELENGTH*2 , BASELENGTH, BASELENGTH*2, 2,0);
        pc[4] = new PersonCell(BASELENGTH,BASELENGTH*2, BASELENGTH*2, BASELENGTH,2,1);
        pc[5] = new PersonCell( BASELENGTH*3, BASELENGTH*2, BASELENGTH, BASELENGTH*2, 2,3);
        pc[6] = new PersonCell(BASELENGTH, BASELENGTH*3, BASELENGTH, BASELENGTH, 3,1);
        pc[7] = new PersonCell(BASELENGTH*2, BASELENGTH*3, BASELENGTH, BASELENGTH, 3,2);
        pc[8] = new PersonCell(0, BASELENGTH*4, BASELENGTH, BASELENGTH, 4,0);
        pc[9] = new PersonCell(BASELENGTH*3, BASELENGTH*4, BASELENGTH, BASELENGTH, 4,3);

        pp[0] = new Person(pc[0],"曹操");
        pp[1] = new Person(pc[1], "张辽");
        pp[2] = new Person(pc[2], "曹仁");
        pp[3] = new Person(pc[3], "张飞");
        pp[4] = new Person(pc[4], "关羽");
        pp[5] = new Person(pc[5], "刘备");
        pp[6] = new Person(pc[6], "兵1");
        pp[7] = new Person(pc[7], "兵2");
        pp[8] = new Person(pc[8], "兵3");
        pp[9] = new Person(pc[9], "兵4");

        for(int i = 0; i < 10; i++) {
            pl[i] = new PersonLabel(pp[i]);
            pl[i].setBorder(new EtchedBorder());
            dragListener[i] = new DragListener(pl[i], gamePanel, gameStatus);
            pl[i].addMouseListener(dragListener[i]);
            pl[i].addMouseMotionListener(dragListener[i]);
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

