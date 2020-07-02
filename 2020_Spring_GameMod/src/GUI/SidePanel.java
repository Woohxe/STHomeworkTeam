package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lero on 2020/6/30.
 */
public class SidePanel extends JPanel {
    private SidePanel1 sidePanel1;
    private SidePanel2 sidePanel2;
    private MainFrame mainFrame;
    public final int SIDEWIDTH=300; // 工具栏宽度

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


    public SidePanel(MainFrame mainFrame){
        this.mainFrame = mainFrame;
        this.setPreferredSize(new Dimension(SIDEWIDTH,800));
        this.setVisible(true);
        this.add(new JLabel("工具面板"));
        this.setBackground(new Color(127, 174, 252));
        this.setLayout(null);
        //BoxLayout sideLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        //this.setLayout(sideLayout);//组件垂直放置
        initialize(); // 加操作框
    }

    // 工具栏的各个操作功能
    private void initialize() {
        sidePanel1 = new SidePanel1(this,mainFrame);
        sidePanel2 = new SidePanel2(this,mainFrame);
        sidePanel1.setBounds(0,0,SIDEWIDTH,800);
        sidePanel2.setBounds(0,0,SIDEWIDTH,800);
        this.add(sidePanel1);
        sidePanel1.setEnabled(true);
        this.add(sidePanel2);
        this.setVisible(true);

    }

}
