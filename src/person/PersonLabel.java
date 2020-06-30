package person;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lero on 2020/6/20.
 */
public class PersonLabel extends JLabel {

    private Person person;
    private Icon icon;

    public PersonLabel(Person person) {
        this.person = person;
        String type = person.getName();
        Color color;
        switch (type){
            case "曹操":
                color = new Color(255, 93, 100);
                break;
            case "张飞":
                color = new Color(255, 174, 131);
                break;
            case "刘备":
                color = new Color(255, 174, 131);
                break;
            case "曹仁":
                color = new Color(255, 174, 131);
                break;
            case "张辽":
                color = new Color(255, 174, 131);
                break;
            case "关羽":
                color = new Color(255, 145, 178);
                break;
            default:
                color = new Color(255, 230, 121);
                break;
        }
        icon = new IconCreator(person.getX(), person.getY(),
                person.getWidth(), person.getHeight(), color);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getX() {
        return person.getX();
    }

    public void setX(int x) {
        person.setX(x);
    }

    public int getY() {
        return person.getY();
    }

    public void setY(int y) {
        person.setY(y);
    }

    public int getWidth() {
        return person.getWidth();
    }

    public void setWidth(int width) {
        person.setWidth(width);
    }

    public int getHeight() {
        return person.getHeight();
    }

    public void setHeight(int height) {
        person.setHeight(height);
    }

    public int getRow() {
        return person.getRow();
    }

    public void setRow(int row) {
        person.setRow(row);
    }

    public int getColumn() {
        return person.getColumn();
    }

    public void setColumn(int column) {
        person.setColumn(column);
    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    @Override
    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    /**
     @Override
     public void setBounds(int x, int y, int width, int height) {
     this.setBounds(person.getX(), person.getY(),
     person.getWidth(), person.getHeight());
     this.setVisible(true);
     }
     **/
}
