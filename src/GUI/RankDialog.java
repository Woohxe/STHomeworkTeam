package GUI;

import object.TimeModeRecord;
import utils.TimeRecordReader;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

/**
 * 排行榜弹窗，根据选择关卡号初始化
 */
public class RankDialog extends JDialog {
    //主游戏框架
    private MainFrame mainFrame;
    //当前选择的关卡号
    private int gameId;

    /**
     * 构造方法
     * @param mainFrame
     * @param gameId
     * @throws Exception
     */
    public RankDialog(MainFrame mainFrame,int gameId) throws Exception {
        this.mainFrame = mainFrame;
        this.gameId = gameId;
        this.setTitle("排行榜");
        this.setBounds(30,30,450,600);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setLayout(new BorderLayout(10,5));

        JLabel rankTitle = new JLabel("第"+gameId+"关",JLabel.CENTER);
        JPanel rankPanel = new JPanel();
        TimeRecordReader reader = new TimeRecordReader(gameId);
        TimeModeRecord[] records = reader.getRecords();
        //根据记录长度生成相应长度
        rankPanel.setPreferredSize(new Dimension(400,50*records.length));
        //排行榜布局，固定三列
        rankPanel.setLayout(new GridLayout(0,3));
        rankPanel.setBorder(new EtchedBorder());
        //排行榜题头
        JLabel title = new JLabel("用户名",JLabel.CENTER);
        rankPanel.add(title);
        title = new JLabel("用时(s)",JLabel.CENTER);
        rankPanel.add(title);
        title = new JLabel("游戏记录",JLabel.CENTER);
        rankPanel.add(title);
        //排行榜每行加入一个记录
        for(int i = 0; i<records.length; i++){
            JLabel name = new JLabel(records[i].getName(),JLabel.CENTER);
            JLabel useTime = new JLabel(String.valueOf(records[i].getSecond()),JLabel.CENTER);
            JLabel beginTime = new JLabel(records[i].getCurDate(),JLabel.CENTER);
            rankPanel.add(name);
            rankPanel.add(useTime);
            rankPanel.add(beginTime);
        }
        // 创建滚动面板
        JScrollPane recordSroll=new JScrollPane();
        recordSroll.setBounds(0,20,150*4,600);
        recordSroll.getViewport().add(rankPanel);
        recordSroll.validate();

        this.add(rankTitle,BorderLayout.NORTH);
        this.add(recordSroll,BorderLayout.CENTER);
    }
}
