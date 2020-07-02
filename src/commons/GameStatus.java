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
    private int mapStep[][];//二维数组

    public GameStatus(GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
        this.mapStep = gameConfiguration.getMapStep();
        cellFactory = new CellFactory();
        cells = new Cell[10];

        step = 0;
        win = false;
        initPanel();

    }

    public void initPanel() {
        int baseLength = gameConfiguration.getBaseLength();
        int flag[]=new int[10];//当前块是否已被绘制
        for(int i = 0; i < 10; i++) {
            flag[i] = 0;
        }
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j <4 ; j++) {
                if(mapStep[i][j] == 0 && flag[0] == 0) {
                    cells[0] = cellFactory.CreateCell(i,j,j*baseLength,
                            i*baseLength,0,baseLength);
                    flag[0] = 1;
                }
                else if(mapStep[i][j] == 1 && flag[1] == 0) {
                    cells[1] = cellFactory.CreateCell(i,j,j*baseLength,
                            i*baseLength,1,baseLength);
                    flag[1] = 1;
                }
                else if(mapStep[i][j] == 2 && flag[2] == 0) {
                    cells[2] = cellFactory.CreateCell(i,j,j*baseLength,
                            i*baseLength,2,baseLength);
                    flag[2] = 1;
                }
                else if(mapStep[i][j] == 3 && flag[3] == 0) {
                    cells[3] = cellFactory.CreateCell(i,j,j*baseLength,
                            i*baseLength,3,baseLength);
                    flag[3] = 1;
                }
                else if(mapStep[i][j] == 4 && flag[4] == 0) {
                    cells[4] = cellFactory.CreateCell(i,j,j*baseLength,
                            i*baseLength,4,baseLength);
                    flag[4] = 1;
                }
                else if(mapStep[i][j] == 5 && flag[5] == 0) {
                    cells[5] = cellFactory.CreateCell(i,j,j*baseLength,
                            i*baseLength,5,baseLength);
                    flag[5] = 1;
                }
                else if(mapStep[i][j] == 6 && flag[6] == 0) {
                    cells[6] = cellFactory.CreateCell(i,j,j*baseLength,
                            i*baseLength,6,baseLength);
                    flag[6] = 1;
                }
                else if(mapStep[i][j] == 7 && flag[7] == 0) {
                    cells[7] = cellFactory.CreateCell(i,j,j*baseLength,
                            i*baseLength,7,baseLength);
                    flag[7] = 1;
                }
                else if(mapStep[i][j] == 8 && flag[8] == 0) {
                    cells[8] = cellFactory.CreateCell(i,j,j*baseLength,
                            i*baseLength,8,baseLength);
                    flag[8] = 1;
                }
                else if(mapStep[i][j] == 9 && flag[9] == 0) {
                    cells[9] = cellFactory.CreateCell(i,j,j*baseLength,
                            i*baseLength,9,baseLength);
                    flag[9] = 1;
                }

            }
        }

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

    public int[][] getMapStep() {
        return mapStep;
    }

    public void setMapStep(int[][] mapStep) {
        this.mapStep = mapStep;
        initPanel();
    }

}
