package factory;

import object.Cell;

/**
 * Cell的工厂类，作用是返回一个Cell类
 * Created by lero on 2020/6/30.
 */
public class CellFactory {
    private Cell cell = null;

    public CellFactory() {
    }

    /**
     * 根据Cell的行号，列号，左上角的实际坐标号和Cell的标号及其地图单位长度
     * 采用工厂模式创建Cell对象
     * @param row
     * @param column
     * @param x
     * @param y
     * @param num
     * @param length
     * @return
     */
    public Cell CreateCell(int row, int column, int x, int y, int num, int length) {
        cell = new Cell(row,column,x,y,length);
        cell.setNum(num);
        if(num == 0) {
            cell.setWidth(2*length);
            cell.setHeight(2*length);
        }
        else if(num == 1) {
            cell.setWidth(2*length);
            cell.setHeight(1*length);
        }
        else if(num>=2 && num<=5) {
            cell.setWidth(1*length);
            cell.setHeight(2*length);
        }
        else {
            cell.setWidth(1*length);
            cell.setHeight(1*length);
        }
        return cell;
    }
}
