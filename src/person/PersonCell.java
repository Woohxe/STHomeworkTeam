package person;

import java.awt.*;

/**
 * Created by lero on 2020/6/20.
 */
public class PersonCell {
    //左上角坐标
    private int x;
    private int y;
    //宽和高
    private int width;
    private int height;
    //在方格区域中的坐标
    private int row;
    private int column;

    public PersonCell(int x, int y, int width, int height, int row, int column) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.row = row;
        this.column = column;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "PersonCell{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", row=" + row +
                ", column=" + column +
                '}';
    }

    public void moveY(int d) {
        if(d == 0)
            return;
        if(d < 0) {
            row--;
        }
        else {
            row++;
        }
        y += d;
    }


    public void movex(int d) {
        if(d == 0)
            return;
        if(d < 0) {
            column--;
        }
        else {
            column++;
        }
        x += d;
    }



    public boolean isIntersected(PersonCell cell2) {
        Rectangle r1 = new Rectangle(x,y,width,height);
        Rectangle r2 = new Rectangle(cell2.getX(),cell2.getY(),cell2.getWidth(),cell2.getHeight());
        return r1.intersects(r2);
    }
}