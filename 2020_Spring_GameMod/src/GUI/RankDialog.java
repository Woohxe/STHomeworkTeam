package GUI;

import object.TimeModeRecord;
import utils.TimeRecordReader;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * Created by 77357 on 2020/7/1.
 */
public class RankDialog extends JDialog {
    private MainFrame mainFrame;
    private int gameId;
    public RankDialog(MainFrame mainFrame,int gameId) throws Exception {
        this.mainFrame = mainFrame;
        this.gameId = gameId;
        this.setTitle("排行榜");
        this.setBounds(30,30,450,600);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(new BorderLayout(10,5));

        JLabel l1 = new JLabel("第"+gameId+"关",JLabel.CENTER);
        JPanel rankPanel = new JPanel();
        TimeRecordReader reader = new TimeRecordReader(gameId);
        TimeModeRecord[] records = reader.getRecords();
        rankPanel.setPreferredSize(new Dimension(400,50*records.length));
        rankPanel.setLayout(new GridLayout(0,3));//未指定行数，但是三列
        rankPanel.setBorder(new EtchedBorder());
        JLabel title = new JLabel("用户名",JLabel.CENTER);
        rankPanel.add(title);
        title = new JLabel("用时(s)",JLabel.CENTER);
        rankPanel.add(title);
        title = new JLabel("游戏记录",JLabel.CENTER);
        rankPanel.add(title);
        for(int i = 0; i<records.length; i++){
            JLabel name = new JLabel(records[i].getName(),JLabel.CENTER);
            JLabel useTime = new JLabel(String.valueOf(records[i].getSecond()),JLabel.CENTER);
            JLabel beginTime = new JLabel(records[i].getCurDate(),JLabel.CENTER);
//            name.setBackground(new Color(245, 255, 248));
//            c.setPreferredSize(new Dimension(380,60));
            rankPanel.add(name);
            rankPanel.add(useTime);
            rankPanel.add(beginTime);
        }
        JScrollPane recordSroll=new JScrollPane(); // 创建滚动面板
        recordSroll.setBounds(0,20,150*4,records.length*40);
        recordSroll.getViewport().add(rankPanel);
        recordSroll.validate();

        this.add(l1,BorderLayout.NORTH);
        this.add(recordSroll,BorderLayout.CENTER);
    }
}
