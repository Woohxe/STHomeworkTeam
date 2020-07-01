package factory;

import object.Cell;

/**
 * Created by lero on 2020/6/30.
 */
public class CellFactory {
    private Cell cell = null;

    public CellFactory() {
    }

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
