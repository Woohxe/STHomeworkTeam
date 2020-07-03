package GUI;

import commons.GameMap;
import huarongdao.Solver;
import listener.BeginListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 主游戏框架，初始化为开始界面
 */
public class MainFrame extends JFrame {
    //游戏区框架
    GamePanel gamePanel;
    //功能区框架
    SidePanel sidePanel;
    //获取窗体内置容器
    Container container = this.getContentPane();
    //开始游戏按钮与游戏规则按钮
    JButton beginBtn,ruleBtn;
    //按钮框架
    JPanel BtnPanel;
    //游戏标题
    JLabel lab;
    //默认框架宽度与高度
    public final int DEFAULTWIDTH = 900;
    public final int DEFAULTHEIGHT = 750;
    //字体样式
    Font fnt1,fnt2;

    /**
     * 构造方法，初始化为开始游戏界面
     */
    public MainFrame() {
        container.setLayout(new BorderLayout());
        this.setSize(DEFAULTWIDTH+20, DEFAULTHEIGHT+50);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("华容道小游戏");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        //生成开始游戏界面各组件
        initialize();
        //读取地图文件并生成相应的最短路径
        GameMap.readMap();
        Solver.solve();
    }

    /**
     * 开始游戏界面各组件初始化
     */
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
        //开始游戏按钮绑定监听器
        BeginListener beginListener = new BeginListener(this);
        beginBtn.addMouseListener(beginListener);
        //游戏规则按钮绑定监听器
        ruleBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //弹出游戏规则提示框
                JOptionPane.showMessageDialog(null, "游戏规则如下：通过移动各个方块,帮助曹操最大的方块从初始位置移到棋盘最下方中部,从出口逃走。不允许跨越棋子,所有操作在规定框内完成,设法用最少的步数把曹操移到出口。");
            }

        });

    }

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

}


//class loginDialog extends JDialog
//{
//    private MainFrame mainFrame;
//    public loginDialog(MainFrame mainFrame)
//    {
//        this.mainFrame = mainFrame;
//        BeginListener beginListener = new BeginListener(mainFrame);
//        JPanel clogin = (JPanel)this.getContentPane();
//        clogin.setLayout(null);
//
//        JLabel l1 = new JLabel("点击确定开始游戏");
//        JButton loginbt = new JButton("确定");
//
//        loginbt.addMouseListener(beginListener);
//
//        l1.setBounds(40,35,150,100);
//        l1.setFont(new Font("隶书",Font.BOLD,25));
//
//
//        loginbt.setFont(new Font("黑体",Font.BOLD,25));
//        loginbt.setBounds(175,150,100,50);
//        loginbt.setBackground(new Color(139,117,0));
//
//        clogin.add(l1);
//        clogin.add(loginbt);
//
//        clogin.setBackground(new Color(255,228,181));
//        this.setTitle("开始游戏");
//        this.setBounds(30,30,450,300);
//        this.setResizable(false);
//        this.setLocationRelativeTo(null);
//        this.setVisible(true);
//    }
//
//}