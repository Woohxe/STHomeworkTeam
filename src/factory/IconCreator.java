package factory;

import javax.swing.*;
import java.awt.*;

/**
 * Icon方法的实现类，作用是在指定位置绘制目标矩形
 * Created by lero on 2020/6/30.
 */
public class IconCreator implements Icon{
    //矩形左上角点的横坐标
    private int x;
    //矩形左上角点的纵坐标
    private int y;
    //矩形的高
    private int height;
    //矩形的宽
    private int width;
    //矩形的颜色
    private Color color;

    /**
     * 构造方法
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public IconCreator(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.color = color;
    }

    /**
     * 绘制color颜色的一个实心矩形
     * @param c
     * @param g
     * @param x
     * @param y
     */
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x,y,width,height);
    }

    @Override
    public int getIconWidth() {
        return width;
    }

    @Override
    public int getIconHeight() {
        return height;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
