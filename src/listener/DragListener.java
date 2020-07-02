package listener;

import GUI.GamePanel;
import GUI.MainFrame;
import GUI.PersonLabel;
import commons.GameStatus;
import object.Cell;
import object.TimeModeRecord;
import timer.GameTimer;
import utils.TimeRecordReader;
import utils.TimeRecordWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;

/**
 * 继承MouseAdapter的监听器，作用是实现拖拽方块功能以及对“曹操”方块位置的监听
 * Created by lero on 2020/6/30.
 */
public class DragListener extends MouseAdapter {

    //监听对象
    private PersonLabel dragLabel;
    //游戏状态
    private GameStatus gameStatus;
    //游戏面板
    private GamePanel gamePanel;
    //主游戏框架
    private MainFrame mainFrame;
    //游戏计时器
    private GameTimer gameTimer;
    //拖拽点的坐标
    private Point point = new Point(0,0);
    //游戏状态中各矩形块的数组，目的是为了获取各个快的逻辑坐标
    private Cell[] cells;
    //记录该块是否已经移动，防止重复触发的情况发生
    private boolean hasMoved;

    /**
     * 构造方法
     * @param dragLabel
     * @param gamePanel
     * @param gameStatus
     * @param mainFrame
     */
    public DragListener(PersonLabel dragLabel, GamePanel gamePanel, GameStatus gameStatus, MainFrame mainFrame) {
        this.dragLabel = dragLabel;
        this.gamePanel = gamePanel;
        this.gameStatus = gameStatus;
        this.mainFrame = mainFrame;
        this.cells = gameStatus.getCells();
        //System.out.println("我被创建啦！");
        //System.out.print(dragLabel.getX());
        //System.out.print(",");
        //System.out.println(dragLabel.getY());
        point.setLocation(dragLabel.getX(),dragLabel.getY());
        hasMoved = false;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    /**
     * 实现拖拽功能
     * @param e
     */
    public void mouseDragged(MouseEvent e) {
        //将获取到的坐标转为界面中的实际坐标
        point = e.getPoint();
        SwingUtilities.convertPointToScreen(point,dragLabel);
        SwingUtilities.convertPointFromScreen(point,gamePanel);
        //当拖拽合法且该矩形未被移动过，则对其进行移动
        if(isValid(point) && !hasMoved){
            //设置移动后的坐标
            dragLabel.setX(dragLabel.getX());
            dragLabel.setY(dragLabel.getY());
            //更新hasMoved变量，说明该对象已被移动
            hasMoved = true;
            //更新步数
            gameStatus.setStep(gameStatus.getStep()+1);
        }
    }

    /**
     * 用户点击时触发此方法，记录点击时的坐标
     * @param e
     */
    public void mousePressed(MouseEvent e) {
        //System.out.println("我被点击啦！");
        point = e.getPoint();
        SwingUtilities.convertPointToScreen(point,dragLabel);
        SwingUtilities.convertPointFromScreen(point,gamePanel);
        System.out.println(point);
    }

    /**
     * 当用户松开鼠标，触发此方法，在GUI中实现移动
     * @param e
     */
    public void mouseReleased(MouseEvent e) {
        //获取方格的长度
        int baseLength = gameStatus.getGameConfiguration().getBaseLength();
        //System.out.println("释放时xy："+dragLabel.getX()+" "+dragLabel.getY());
        //根据标签中更新过后的坐标，对标签的位置进行更新，实现移动
        dragLabel.setLocation(
                ((int) Math.round(dragLabel.getX() / baseLength)) * baseLength,
                ((int) Math.round(dragLabel.getY() / baseLength)) * baseLength);
        //更新hasMoved变量，说明被监听对象可以继续移动了
        hasMoved = false;

        //判断标号为0的标签是否在出口处
        if(dragLabel.getNum() == 0 && dragLabel.getRow() == 3 && dragLabel.getColumn() == 1) {//成功通关
            //更新游戏状态为胜利状态
            gameStatus.setWin(true);
            //将标号为0的标签设置为不可见，以达到逃脱效果
            dragLabel.setVisible(false);
            //获取当前计时器
            gameTimer = mainFrame.getSidePanel().getSidePanel2().getTimerCount();
            //停止计时
            gameTimer.getTimer().stop();
            //获取游戏时常
            int gameTimeTotal = gameTimer.getTotalTime();
            //询问用户是否计入成绩，加入排行榜
            int res=JOptionPane.showConfirmDialog(gamePanel, "闯关成功，是否记录成绩？", "恭喜通关", JOptionPane.YES_NO_OPTION);
            //用户点击“是”后执行这个代码块
            if(res==JOptionPane.YES_OPTION){
                //获取用户名;
                String userName = JOptionPane.showInputDialog("输出用户名：");
                //获取当前关卡编号
                int gameId = gamePanel.getGamePanel2().getGameStatus().getGameConfiguration().getGameId();
                try {
                    //写入排行榜
                    recordGameTime(userName,gameId,gameTimeTotal);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            //游戏结束，实现跳转
            mainFrame.getSidePanel().getSidePanel2().turnPanel();
        }

    }

    /**
     * 根据用户输入的用户名将游戏成绩写入对应的排行榜文件
     * @param userName
     * @param gameId
     * @param gameTimeTotal
     * @throws IOException
     */
    private void recordGameTime(String userName, int gameId, int gameTimeTotal) throws IOException {//记录排行榜记录

        //需要先读出排行榜文件后重写，所以需要初始化读文件的工具类
        TimeRecordReader reader = null;
        try {
            reader = new TimeRecordReader(gameId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //读出旧排行榜的内容
        TimeModeRecord[] recordsOld = reader.getRecords();
        //写入新记录
        TimeModeRecord[] recordsNew = new TimeModeRecord[recordsOld.length+1];
        for(int i=0; i<recordsOld.length; i++) {
            recordsNew[i] = recordsOld[i];
        }
        recordsNew[recordsOld.length]=new TimeModeRecord(userName,gameId,gameTimeTotal);//最新的记录
        //根据游戏用时进行排序
        Arrays.sort(recordsNew);
        //构建写文件类，将新的记录写入对应文件
        TimeRecordWriter writer = new TimeRecordWriter(recordsNew);
        writer.write();
    }

    /**
     * 判断移动是否合法
     * @param p
     * @return
     */
    public boolean isValid(Point p) {

        //若该块已经处于移动状态，则直接返回不合法
        if(hasMoved)
            return false;
        //获取拖拽点的横纵坐标
        int x = p.x;
        int y = p.y;

        //获取此块的原始坐标和标号
        int id = dragLabel.getNum();
        int curx = dragLabel.getX();
        int cury = dragLabel.getY();
        int curR = dragLabel.getRow();
        int curC = dragLabel.getColumn();

        //从游戏配置中读出方格单位长度、最大行和列以及触发移动的最小拖拽长度
        int BASELENGTH = gameStatus.getGameConfiguration().getBaseLength();
        int maxR = gameStatus.getGameConfiguration().getRowNum();
        int maxC = gameStatus.getGameConfiguration().getColumnNum();
        int LIMIT = gameStatus.getGameConfiguration().getLIMIT();

        //拖拽后，矩形坐标的合法范围
        int minX = 0;
        int minY = 0;
        int maxX = maxC*BASELENGTH;
        int maxY = maxR*BASELENGTH;

        //flag为是否合法的状态变量
        boolean flag = true;
        //dx和dy记录横纵坐标的移动长度
        int dx = x - curx;
        int dy = y - cury;

        //判断是否满足移动的触发条件，优先对横坐标进行移动
        //移动距离为一个单位长度
        if(Math.abs(dx)>=LIMIT) {
            dy = 0;
            if(dx<0) {
                dx = -1*BASELENGTH;
            }
            else {
                dx = BASELENGTH;
            }
        }
        else if(Math.abs(dy)>=LIMIT) {
            dx = 0;
            if(dy<0) {
                dy = -1*BASELENGTH;
            }
            else {
                dy = BASELENGTH;
            }
        }
        else {
            flag = false;
            dx = 0;
            dy = 0;
        }
        //获取cell对象，以判断移动后是否合法
        Cell cell = dragLabel.getCell();

        //试移动cell对象
        cell.moveX(dx);
        cell.moveY(dy);
        //获取移动后矩形的上下左右边界
        int xl = cell.getX();
        int xr = xl + cell.getWidth();
        int yu = cell.getY();
        int yd = yu + cell.getHeight();

        //判断移动后是否超出游戏界面
        if(xl < minX || xr > maxX || yu<minY || yd>maxY) {
            System.out.println("在判断边界这里，我不合法！");
            flag = false;
        }
        //判断移动后是否与其他矩形块重叠
        for(int i = 0; i<cells.length; i++) {
            if(i == id) {
                continue;
            }
            else if(cell.isIntersected(cells[i])) {
                System.out.println("在判断交叉这里，我不合法:");
                System.out.println(cell);
                System.out.println("我和" + cells[i].toString() + " 是重叠的");
                flag = false;
            }
        }

        //若移动合法，则更新cell数组
        if(flag == true) {
            cells[id] = cell;
        }
        //移动不合法，还原cell到原先的位置
        else {
            cell.moveY(-dy);
            cell.moveX(-dx);
        }

        if(!flag) {
            System.out.println("不能移动：");
            System.out.println(String.valueOf(dx) + "," + String.valueOf(dy));
        }

        return flag;



    }

}
