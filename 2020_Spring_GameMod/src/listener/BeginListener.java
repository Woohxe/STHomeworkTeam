package listener;

import GUI.GamePanel;
import GUI.MainFrame;
import GUI.SidePanel;
import commons.GameMap;
import huarongdao.Solver;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by lero on 2020/6/30.
 */
public class BeginListener extends MouseInputAdapter {
    private MainFrame mainFrame;
    private GamePanel gamePanel;
    private SidePanel sidePanel;
    JLabel lab;
    Font fnt1 = new Font("隶书",Font.BOLD,102);
    Font fnt2 = new Font("黑体",Font.BOLD,30);

    public BeginListener(MainFrame mainFrame){
        this.mainFrame=mainFrame;
    }

    public void mousePressed(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        JPanel BtnPanel = new JPanel();
        String text = button.getText();
        if (text == "开始游戏") {//开始游戏里面的“开始游戏”
            //初始化
            //mainFrame.getNameDialog().setVisible(false);
            BtnPanel = mainFrame.getBtnPanel();
        }
        if(text == "返回") {//地图选择里面的“返回”
            mainFrame.remove(mainFrame.getGamePanel());
            mainFrame.remove(mainFrame.getSidePanel());
            mainFrame.setBtnPanel(BtnPanel);
        }
        BtnPanel.removeAll();
        BtnPanel.setLayout(null);
        lab = new JLabel("华容道");
        lab.setFont(fnt1);
        lab.setForeground(new Color(139,129,76));
        lab.setBounds(260,50,330,330);

        JButton fixedBtn = createBtn("选择关卡",290, 300, 265, 50);
        JButton customizeBtn = createBtn("自定义地图",290, 395, 265, 50);
        JButton returnBtn = createBtn("返回",290, 490, 265, 50);


//        fixedBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                gamePanel = new GamePanel();;//游戏面板
//                sidePanel = new SidePanel();
//                mainFrame.remove(BtnPanel);
//                // 重复的按开始按钮：首先必须清除上面的组件
//                mainFrame.add(gamePanel,BorderLayout.CENTER);//添加游戏主面板到内置容器
//                mainFrame.add(sidePanel,BorderLayout.WEST);//添加游戏辅助面板到内置容器
//                JButton returnBtn = new JButton("返回");
//                BeginListener beginListener = new BeginListener(mainFrame);
//                returnBtn.addMouseListener(beginListener);
//                sidePanel.add(returnBtn,BorderLayout.SOUTH);
//                mainFrame.revalidate();
//                mainFrame.repaint();
//                mainFrame.validate();
//            }
//        });


        ChooseListener chooseListener = new ChooseListener(mainFrame);
        fixedBtn.addMouseListener(chooseListener);
        customizeBtn.addMouseListener(chooseListener);


        returnBtn.addMouseListener(chooseListener);
        BtnPanel.add(fixedBtn);
        BtnPanel.add(customizeBtn);
        BtnPanel.add(returnBtn);
        BtnPanel.add(lab);
        mainFrame.add(BtnPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.validate();
    }

    public JButton createBtn(String name, int x, int y, int width, int height){
        JButton Btn = new JButton(name);
        Btn.setBackground(new Color(139,117,0));
        Btn.setFont(fnt2);
        Btn.setBounds(x,y,width,height);
        return Btn;
    }
}