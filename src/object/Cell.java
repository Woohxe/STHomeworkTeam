package object;

/**
 * Created by lero on 2020/6/30.
 */
public class Cell {
    //(x,y)构成左上角实际坐标
    private int x;
    private int y;
    //(column,row)为网格坐标
    private int row;
    private int column;
    //height和width为矩形高和宽
    private int height;
    private int width;
    //num作为id使用，对应状态数组里的下标
    private int num;
    //length为单位长度
    private int length;

    /**
     * 构造方法
     * @param row
     * @param column
     * @param x
     * @param y
     * @param height
     * @param width
     * @param num
     * @param length
     */
    public Cell(int row, int column, int x, int y, int height, int width, int num, int length) {
        this.row = row;
        this.column = column;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
        this.num = num;
        this.length = length;
    }

    public Cell(int row, int column, int x, int y, int length) {
        this.row = row;
        this.column = column;
        this.x = x;
        this.y = y;
        this.length = length;
    }

    public Cell(Cell cell,double i) {
        this.width = (int) (cell.width*i);
        this.height= (int) (cell.height*i);
        this.x = (int) (cell.x*i);
        this.y = (int) (cell.y*i);
        this.num = cell.num;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setSmaller(double i) {
        this.height = (int) (this.height*i);
        this.width = (int) (this.width*i);
        this.x = (int) (this.x*i);
        this.y = (int) (this.y*i);
    }

    @Override
    public String toString() {
        return "Cell{"+ "num = "+num+",("+column+","+row+")"+",("+x+","+y+")," +
                width + "*"+ height + ",length = "+length + "}";
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


    public void moveX(int d) {
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

    public boolean isIntersected(Cell cell) {
        int tw = this.width;
        int th = this.height;
        int rw = cell.width;
        int rh = cell.height;
        if (rw <= 0 || rh <= 0 || tw <= 0 || th <= 0) {
            return false;
        }
        int tx = this.getX();
        int ty = this.getY();
        int rx = cell.getX();
        int ry = cell.getY();
        rw += rx;
        rh += ry;
        tw += tx;
        th += ty;
        //      overflow || intersect
        return ((rw < rx || rw > tx) &&
                (rh < ry || rh > ty) &&
                (tw < tx || tw > rx) &&
                (th < ty || th > ry));
    }
}
