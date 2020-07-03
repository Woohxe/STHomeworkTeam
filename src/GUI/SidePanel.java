package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * 功能区框架，实现关卡选择功能区与游戏操作功能区两个界面的初始化
 */
public class SidePanel extends JPanel {
    //关卡选择功能区界面
    private SidePanel1 sidePanel1;
    //游戏操作功能区界面
    private SidePanel2 sidePanel2;
    //主游戏框架
    private MainFrame mainFrame;
    //功能区宽度
    public final int SIDEWIDTH=300;

    /**
     * 构造方法，传入主游戏框架，初始化功能区框架
     * @param mainFrame
     */
    public SidePanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        this.setPreferredSize(new Dimension(SIDEWIDTH,800));
        this.setVisible(true);
        this.setLayout(null);
        this.add(new JLabel("工具面板"));
        this.setBackground(new Color(127, 174, 252));
        //初始化关卡选择功能区与游戏操作功能区两个界面
        initialize();
    }

    /**
     * 初始化关卡选择功能区与游戏操作功能区界面
     */
    private void initialize() {
        sidePanel1 = new SidePanel1(this,mainFrame);
        sidePanel2 = new SidePanel2(this,mainFrame);
        sidePanel1.setBounds(0,0,SIDEWIDTH,800);
        sidePanel2.setBounds(0,0,SIDEWIDTH,800);
        this.add(sidePanel1);
        this.add(sidePanel2);
        //设置关卡选择功能区为当前可见
        sidePanel1.setEnabled(true);
        this.setVisible(true);
    }

    public SidePanel1 getSidePanel1() {
        return sidePanel1;
    }

    public void setSidePanel1(SidePanel1 sidePanel1) {
        this.sidePanel1 = sidePanel1;
    }

    public SidePanel2 getSidePanel2() {
        return sidePanel2;
    }

    public void setSidePanel2(SidePanel2 sidePanel2) {
        this.sidePanel2 = sidePanel2;
    }

}
