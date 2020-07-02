package GUI;

import commons.GameMap;
import huarongdao.Solver;
import listener.BeginListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by lero on 2020/6/30.
 */
public class MainFrame extends JFrame {
    private MainFrame mainFrame=this;

    public final int DEFAULTWIDTH = 900; // 默认宽度
    public final int DEFAULTHEIGHT = 750; //默认长度
    public String username;
    Container container = this.getContentPane();//获取窗体内置容器
    JButton beginBtn,ruleBtn;
    JPanel BtnPanel;
    JDialog nameDialog;
    JLabel lab;
    Font fnt1,fnt2;
    GamePanel gamePanel;
    SidePanel sidePanel;

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public SidePanel getSidePanel() {
        return sidePanel;
    }

    public void setSidePanel(SidePanel sidePanel) {
        this.sidePanel = sidePanel;
    }


    public JPanel getBtnPanel() {
        return BtnPanel;
    }

    public void setBtnPanel(JPanel btnPanel) {
        BtnPanel = btnPanel;
    }

    public JDialog getNameDialog() {
        return nameDialog;
    }

    public MainFrame() {
        container.setLayout(new BorderLayout());//设置布局
        this.setSize(DEFAULTWIDTH+20, DEFAULTHEIGHT+50);//设置窗体大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//当用户点击窗体右上角的x时，自动退出程序
        this.setTitle("华容道小游戏");//设置窗体标题
        this.setVisible(true);//展示窗体
        this.setLocationRelativeTo(null);//居中
        initialize();
        GameMap.readMap();
        Solver.solve();
    }
    public void initialize(){
        beginBtn = new JButton("开始游戏");
        ruleBtn = new JButton("游戏规则");
        lab = new JLabel("华容道");
        fnt1 = new Font("隶书",Font.BOLD,102);
        fnt2 = new Font("黑体",Font.BOLD,30);

        BtnPanel = new JPanel();
        BtnPanel.setLayout(null);

        lab.setFont(fnt1);
        lab.setForeground(new Color(139,129,76));
        beginBtn.setBackground(new Color(139,117,0));
        ruleBtn.setBackground(new Color(139,117,0));

        beginBtn.setFont(fnt2);
        ruleBtn.setFont(fnt2);

        beginBtn.setBounds(290, 300, 265, 50);
        ruleBtn.setBounds(290, 395, 265, 50);
        lab.setBounds(260,50,330,330);

        BtnPanel.add(beginBtn);
        BtnPanel.add(ruleBtn);
        BtnPanel.add(lab);
        container.add(BtnPanel);

        BeginListener beginListener = new BeginListener(mainFrame);//开始游戏按钮
        beginBtn.addMouseListener(beginListener);

        ruleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "游戏规则如下：通过移动各个方块,帮助曹操最大的方块从初始位置移到棋盘最下方中部,从出口逃走。不允许跨越棋子,所有操作在规定框内完成,设法用最少的步数把曹操移到出口。");
            }

        });

    }

}
class loginDialog extends JDialog
{
    private MainFrame mainFrame;
    public loginDialog(MainFrame mainFrame)
    {
        this.mainFrame = mainFrame;
        BeginListener beginListener = new BeginListener(mainFrame);
        JPanel clogin = (JPanel)this.getContentPane();
        clogin.setLayout(null);

        JLabel l1 = new JLabel("点击确定开始游戏");
        JButton loginbt = new JButton("确定");

        loginbt.addMouseListener(beginListener);

        l1.setBounds(40,35,150,100);
        l1.setFont(new Font("隶书",Font.BOLD,25));


        loginbt.setFont(new Font("黑体",Font.BOLD,25));
        loginbt.setBounds(175,150,100,50);
        loginbt.setBackground(new Color(139,117,0));

        clogin.add(l1);
        clogin.add(loginbt);

        clogin.setBackground(new Color(255,228,181));
        this.setTitle("开始游戏");
        this.setBounds(30,30,450,300);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

}