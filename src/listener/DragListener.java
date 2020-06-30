package listener;

import commons.GameStatus;
import panel.GamePanel;
import panel.GamePanel2;
import person.Person;
import person.PersonLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by lero on 2020/6/20.
 */
public class DragListener extends MouseAdapter {

    private PersonLabel dragLabel;
    private GameStatus gameStatus;
    private GamePanel gamePanel;
    private Point point = new Point(0,0);
    private Person[] persons;

    public DragListener(PersonLabel dragLabel, GamePanel gamePanel, GameStatus gameStatus) {
        this.dragLabel = dragLabel;
        this.gamePanel = gamePanel;
        this.gameStatus = gameStatus;
        this.persons = gameStatus.getPersons();
        System.out.println("我被创建啦！");
        System.out.print(dragLabel.getX());
        System.out.print(",");
        System.out.println(dragLabel.getY());
        point.setLocation(dragLabel.getX(),dragLabel.getY());
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
        if(isValid(point)){
            dragLabel.setX(dragLabel.getPerson().getX());
            dragLabel.setY(dragLabel.getPerson().getY());
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
        int baseLength = gameStatus.getGameConfiguration().getBASELENGTH();
        System.out.println("释放时xy："+dragLabel.getX()+" "+dragLabel.getY());
        dragLabel.setLocation(
                ((int) Math.round(dragLabel.getX() / baseLength)) * baseLength,
                ((int) Math.round(dragLabel.getY() / baseLength)) * baseLength);

    }

    public boolean isValid(Point p) {

        int x = p.x;
        int y = p.y;

        String curName = dragLabel.getName();
        int curx = dragLabel.getX();
        int cury = dragLabel.getY();
        int curR = dragLabel.getRow();
        int curC = dragLabel.getColumn();

        int BASELENGTH = gameStatus.getGameConfiguration().getBASELENGTH();
        int maxR = gameStatus.getGameConfiguration().getRowNum() - 1;
        int maxC = gameStatus.getGameConfiguration().getColumnNum() - 1;
        int LIMIT = gameStatus.getGameConfiguration().getLIMIT();

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
        Person person = dragLabel.getPerson();

        person.moveX(dx);
        person.moveY(dy);

        if(person.getRow()<0 || person.getRow() > maxR
                || person.getColumn()<0 || person.getColumn() > maxC) {
            System.out.println("在判断边界这里，我不合法！");
            flag = false;
        }



        Person[] persons = gameStatus.getPersons();

        for(int i = 0; i<persons.length; i++) {
            if(persons[i].getName().equals(person.getName())) {
                continue;
            }
            else if(person.isIntersected(persons[i])) {
                System.out.println("在判断交叉这里，我不合法:");
                System.out.println(person);
                System.out.println("我和" + persons[i].toString() + " 是重叠的");
                flag = false;
            }
        }

        if(flag == true) {
            for (int i = 0; i<persons.length; i++) {
                if (persons[i].getName().equals(person.getName())) {
                    persons[i] = person;
                }
            }
        }
        else {
            person.moveY(-dy);
            person.moveX(-dx);
        }

        if(!flag) {
            System.out.println("不能移动：");
            System.out.println(String.valueOf(dx) + "," + String.valueOf(dy));
        }

        return flag;



    }

}
