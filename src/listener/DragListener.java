package listener;

import GUI.GamePanel;
import GUI.PersonLabel;
import commons.GameStatus;
import object.Cell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by lero on 2020/6/30.
 */
public class DragListener extends MouseAdapter {

    private PersonLabel dragLabel;
    private GameStatus gameStatus;
    private GamePanel gamePanel;
    private Point point = new Point(0,0);
    private Cell[] cells;
    private boolean hasMoved;

    public DragListener(PersonLabel dragLabel, GamePanel gamePanel, GameStatus gameStatus) {
        this.dragLabel = dragLabel;
        this.gamePanel = gamePanel;
        this.gameStatus = gameStatus;
        this.cells = gameStatus.getCells();
        System.out.println("我被创建啦！");
        System.out.print(dragLabel.getX());
        System.out.print(",");
        System.out.println(dragLabel.getY());
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
        System.out.println("我被点击啦！");
        point = e.getPoint();
        SwingUtilities.convertPointToScreen(point,dragLabel);
        SwingUtilities.convertPointFromScreen(point,gamePanel);
        System.out.println(point);
    }


    public void mouseReleased(MouseEvent e) {
        int baseLength = gameStatus.getGameConfiguration().getBaseLength();
        System.out.println("释放时xy："+dragLabel.getX()+" "+dragLabel.getY());
        dragLabel.setLocation(
                ((int) Math.round(dragLabel.getX() / baseLength)) * baseLength,
                ((int) Math.round(dragLabel.getY() / baseLength)) * baseLength);
        hasMoved = false;
        if(dragLabel.getNum() == 0 && dragLabel.getRow() == 3 && dragLabel.getColumn() == 1) {
            gameStatus.setWin(true);
            dragLabel.setVisible(false);
            JOptionPane.showMessageDialog(gamePanel,"曹操跑了！","提示",
                    JOptionPane.INFORMATION_MESSAGE);
            PersonLabel[] personLabels = gamePanel.getGamePanel2().getPl();
            for (int i = 0; i<personLabels.length; i++) {
                personLabels[i].setEnabled(false);
            }
        }

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
