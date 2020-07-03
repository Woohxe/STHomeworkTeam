package commons;

import factory.CellFactory;
import object.Cell;

/**
 * 当前地图状态
 */
public class GameStatus {
    //当前游戏配置
    private GameConfiguration gameConfiguration;
    //相应人物块的矩形块
    private Cell[] cells;
    //获取步数
    private int step;
    //矩形块的工厂类
    private CellFactory cellFactory;
    //判断游戏输赢状态
    private boolean win;
    //二维数组
    private int mapStep[][];

    /**
     * 构造方法
     * @param gameConfiguration
     */
    public GameStatus(GameConfiguration gameConfiguration) {
        this.gameConfiguration = gameConfiguration;
        this.mapStep = gameConfiguration.getMapStep();
        cellFactory = new CellFactory();
        cells = new Cell[10];

        step = 0;
        win = false;
        initPanel();

    }

    /**
     * 根据配置构造相应矩形块
     */
    public void initPanel() {
        int baseLength = gameConfiguration.getBaseLength();
        //判断当前块是否已被绘制
        int flag[]=new int[10];
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
        //根据当前地图选择刷新界面
        initPanel();
    }

}
