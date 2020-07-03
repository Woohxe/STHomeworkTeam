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
 * 继承MouseInputAdapter的监听器，实现点击开始游戏按钮后界面的跳转，以及点击关卡选择界面返回按钮的界面跳转
 */
public class BeginListener extends MouseInputAdapter {
    //主游戏框架
    private MainFrame mainFrame;
    //游戏标题
    JLabel lab;
    //字体样式
    Font fnt1 = new Font("隶书",Font.BOLD,102);
    Font fnt2 = new Font("黑体",Font.BOLD,30);

    /**
     * 构造方法
     * @param mainFrame
     */
    public BeginListener(MainFrame mainFrame){
        this.mainFrame=mainFrame;
    }

    /**
     * 用户点击按钮时触发此方法，根据按钮名字切换界面
     * @param e
     */
    public void mousePressed(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        //新的按钮组合界面
        JPanel BtnPanel = new JPanel();
        //获取触发此方法的按钮的名字
        String text = button.getText();
        //点击的是开始游戏界面里面的“开始游戏”按钮
        if (text == "开始游戏") {
            //初始化
            BtnPanel = mainFrame.getBtnPanel();
        }
        //点击的是关卡选择界面“返回”按钮
        if(text == "返回") {
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

        //进入关卡选择还是自定义地图的界面
        JButton fixedBtn = createBtn("选择关卡",290, 300, 265, 50);
        JButton returnBtn = createBtn("返回",290, 395, 265, 50);
        //给“选择关卡”与“返回”按钮绑定监听事件
        ChooseListener chooseListener = new ChooseListener(mainFrame);
        fixedBtn.addMouseListener(chooseListener);
        returnBtn.addMouseListener(chooseListener);
        //按钮组合界面添加组件
        BtnPanel.add(fixedBtn);
        BtnPanel.add(returnBtn);
        BtnPanel.add(lab);
        mainFrame.add(BtnPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.validate();
    }

    /**
     * 根据名字和宽高和左上角坐标创建按钮
     * @param name
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    public JButton createBtn(String name, int x, int y, int width, int height){
        JButton Btn = new JButton(name);
        Btn.setBackground(new Color(139,117,0));
        Btn.setFont(fnt2);
        Btn.setBounds(x,y,width,height);
        return Btn;
    }
}