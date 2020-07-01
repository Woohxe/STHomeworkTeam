package commons;

import factory.CellFactory;
import object.Cell;

/**
 * Created by lero on 2020/6/30.
 */
public class GameStatus {
    private GameConfiguration gameConfiguration;
    private Cell[] cells;
    private int step;
    private CellFactory cellFactory;
    private boolean win;

    public GameStatus(GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
        cellFactory = new CellFactory();
        cells = new Cell[10];
        step = 0;
        win = false;
        int baseLength = gameConfiguration.getBaseLength();
        cells[0] = cellFactory.CreateCell(2,1,1*baseLength,
                2*baseLength,0,baseLength);
        cells[1] = cellFactory.CreateCell(1,1,1*baseLength,
                1*baseLength,1,baseLength);
        cells[2] = cellFactory.CreateCell(2,0,0*baseLength,
                2*baseLength,2,baseLength);
        cells[3] = cellFactory.CreateCell(2,3,3*baseLength,
                2*baseLength,3,baseLength);
        cells[4] = cellFactory.CreateCell(0,0,0*baseLength,
                0*baseLength,4,baseLength);
        cells[5] = cellFactory.CreateCell(0,3,3*baseLength,
                0*baseLength,5,baseLength);
        cells[6] = cellFactory.CreateCell(4,0,0*baseLength,
                4*baseLength,6,baseLength);
        cells[7] = cellFactory.CreateCell(4,3,3*baseLength,
                4*baseLength,7,baseLength);
        cells[8] = cellFactory.CreateCell(0,1,1*baseLength,
                0*baseLength,8,baseLength);
        cells[9] = cellFactory.CreateCell(0,2,2*baseLength,
                0*baseLength,9,baseLength);
    }

    public GameConfiguration getGameConfiguration() {
        return gameConfiguration;
    }

    public void setGameConfiguration(GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
    }

    public Cell[] getCells() {
        return cells;
    }

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public CellFactory getCellFactory() {
        return cellFactory;
    }

    public void setCellFactory(CellFactory cellFactory) {
        this.cellFactory = cellFactory;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }
}
