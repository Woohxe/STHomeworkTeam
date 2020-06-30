//package person;
//
//import javax.swing.*;
//
//import panel.GamePanel2;
//
//public class Person extends JButton {
//    private int x;
//    private int y;
//    private int w;
//    private int h;
//    private String name;
//
//    public Person(String name, int x, int y, int w, int h, GamePanel2 gamePanel2) {
//        super(name);
//        this.name = name;
//        this.x = x;
//        this.y = y;
//        this.w = w;
//        this.h = h;
//        gamePanel2.add(this);
//        this.setBounds(x, y, w, h);
//    }
//}
package person;

/**
 * Created by lero on 2020/6/20.
 */
public class Person{
    PersonCell personCell;
    String name;

    public Person(PersonCell personCell, String name) {
        this.personCell = personCell;
        this.name = name;
    }

    public PersonCell getPersonCell() {
        return personCell;
    }

    public void setPersonCell(PersonCell personCell) {
        this.personCell = personCell;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return personCell.getX();
    }

    public void setX(int x) {
        personCell.setX(x);
    }

    public int getY() {
        return personCell.getY();
    }

    public void setY(int y) {
        personCell.setY(y);
    }

    public int getWidth() {
        return personCell.getWidth();
    }

    public void setWidth(int width) {
        personCell.setWidth(width);
    }

    public int getHeight() {
        return personCell.getHeight();
    }

    public void setHeight(int height) {
        personCell.setHeight(height);
    }

    public int getRow() {
        return personCell.getRow();
    }

    public void setRow(int row) {
        personCell.setRow(row);
    }

    public int getColumn() {
        return personCell.getColumn();
    }

    public void setColumn(int column) {
        personCell.setColumn(column);
    }

    public boolean isIntersected(Person person2) {
        return personCell.isIntersected(person2.personCell);
    }

    public void moveX(int d) {
        personCell.movex(d);
    }

    public void moveY(int d) {
        personCell.moveY(d);
    }

    @Override
    public String toString() {
        return "Person{" +
                "personCell=" + personCell.toString() +
                ", name='" + name + '\'' +
                '}';
    }
}