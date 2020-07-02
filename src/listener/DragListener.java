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

import static GUI.SidePanel2.recordTextArea;

/**
 * Created by lero on 2020/6/30.
 */
public class DragListener extends MouseAdapter {

    private PersonLabel dragLabel;
    private GameStatus gameStatus;
    private GamePanel gamePanel;
    private MainFrame mainFrame;
    private GameTimer gameTimer;
    private Point point = new Point(0,0);
    private Cell[] cells;
    private boolean hasMoved;

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


    public void mouseDragged(MouseEvent e) {
        point = e.getPoint();
        SwingUtilities.convertPointToScreen(point,dragLabel);
        SwingUtilities.convertPointFromScreen(point,gamePanel);
        if(isValid(point) && !hasMoved){
            dragLabel.setX(dragLabel.getX());
            dragLabel.setY(dragLabel.getY());
            hasMoved = true;
            gameStatus.setStep(gameStatus.getStep()+1);
        }
    }

    public void mousePressed(MouseEvent e) {
        //System.out.println("我被点击啦！");
        point = e.getPoint();
        SwingUtilities.convertPointToScreen(point,dragLabel);
        SwingUtilities.convertPointFromScreen(point,gamePanel);
        System.out.println(point);
    }


    public void mouseReleased(MouseEvent e) {
        int baseLength = gameStatus.getGameConfiguration().getBaseLength();
        //System.out.println("释放时xy："+dragLabel.getX()+" "+dragLabel.getY());
        dragLabel.setLocation(
                ((int) Math.round(dragLabel.getX() / baseLength)) * baseLength,
                ((int) Math.round(dragLabel.getY() / baseLength)) * baseLength);
        hasMoved = false;

        if(dragLabel.getNum() == 0 && dragLabel.getRow() == 3 && dragLabel.getColumn() == 1) {//成功通关
            gameStatus.setWin(true);
            dragLabel.setVisible(false);
            gameTimer = mainFrame.getSidePanel().getSidePanel2().getTimerCount();//获取当前计时器
            gameTimer.getTimer().stop();
            int gameTimeTotal = gameTimer.getTotalTime();
            int res=JOptionPane.showConfirmDialog(gamePanel, "闯关成功，是否记录成绩？", "恭喜通关", JOptionPane.YES_NO_OPTION);
            if(res==JOptionPane.YES_OPTION){//点击“是”后执行这个代码块
                String userName = JOptionPane.showInputDialog("输出用户名：");//用户名;
                int gameId = gamePanel.getGamePanel2().getGameStatus().getGameConfiguration().getGameId();//获取当前关卡编号
                try {
                    recordGameTime(userName,gameId,gameTimeTotal);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            mainFrame.getSidePanel().getSidePanel2().turnPanel();
        }

    }

    private void recordGameTime(String userName, int gameId, int gameTimeTotal) throws IOException {//记录排行榜记录
        TimeRecordReader reader = null;
        try {
            reader = new TimeRecordReader(gameId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TimeModeRecord[] recordsOld = reader.getRecords();
        TimeModeRecord[] recordsNew = new TimeModeRecord[recordsOld.length+1];
        for(int i=0; i<recordsOld.length; i++) {
            recordsNew[i] = recordsOld[i];
        }
        recordsNew[recordsOld.length]=new TimeModeRecord(userName,gameId,gameTimeTotal);//最新的记录
        Arrays.sort(recordsNew);
        TimeRecordWriter writer = new TimeRecordWriter(recordsNew);
        writer.write();
    }

    public boolean isValid(Point p) {
        if(hasMoved)
            return false;
        int x = p.x;
        int y = p.y;

        int id = dragLabel.getNum();
        int curx = dragLabel.getX();
        int cury = dragLabel.getY();
        int curR = dragLabel.getRow();
        int curC = dragLabel.getColumn();

        int BASELENGTH = gameStatus.getGameConfiguration().getBaseLength();
        int maxR = gameStatus.getGameConfiguration().getRowNum();
        int maxC = gameStatus.getGameConfiguration().getColumnNum();
        int LIMIT = gameStatus.getGameConfiguration().getLIMIT();

        int minX = 0;
        int minY = 0;
        int maxX = maxC*BASELENGTH;
        int maxY = maxR*BASELENGTH;

        boolean flag = true;
        int dx = x - curx;
        int dy = y - cury;

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
        Cell cell = dragLabel.getCell();

        cell.moveX(dx);
        cell.moveY(dy);

        int xl = cell.getX();
        int xr = xl + cell.getWidth();
        int yu = cell.getY();
        int yd = yu + cell.getHeight();

        if(xl < minX || xr > maxX || yu<minY || yd>maxY) {
            System.out.println("在判断边界这里，我不合法！");
            flag = false;
        }

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

        if(flag == true) {
            cells[id] = cell;
        }
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
