package commons;


import GUI.GamePanel1;
import GUI.MainFrame;

/**
 * Created by lero on 2020/6/30.
 */
public class GameConfiguration {
    private int baseLength = 150;
    private int LIMIT = 150;
    private int GameMode = 1;
    private int rowNum = 5;
    private int columnNum = 4;

    private boolean aoto = false;
    private boolean timing = false;
    private int mapStep[][];//二维数组
    private int gameId;

    public GameConfiguration(GamePanel1 gamePanel1) {
        gameId = gamePanel1.getChooseNum();
        initMap();

    }

    public void initMap() {
        mapStep = GameMap.getMap(gameId);
    }

    public GameConfiguration(int baseLength, int gameMode) {
        this.baseLength = baseLength;
        GameMode = gameMode;
        this.rowNum = rowNum;
        this.columnNum = columnNum;

    }

    public int getLIMIT() {
        return LIMIT;
    }

    public void setLIMIT(int LIMIT) {
        this.LIMIT = LIMIT;
    }

    public boolean isTiming() {
        return timing;
    }

    public void setTiming(boolean timing) {
        this.timing = timing;
    }

    public int getBaseLength() {
        return baseLength;
    }

    public void setBaseLength(int baseLength) {
        this.baseLength = baseLength;
    }


    public int getGameMode() {
        return GameMode;
    }

    public void setGameMode(int gameMode) {
        GameMode = gameMode;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getColumnNum() {
        return columnNum;
    }

    public void setColumnNum(int columnNum) {
        this.columnNum = columnNum;
    }

    public boolean isAoto() {
        return aoto;
    }

    public void setAoto(boolean aoto) {
        this.aoto = aoto;
    }

    public int[][] getMapStep() {
        return mapStep;
    }

    public void setMapStep(int[][] mapStep) {
        this.mapStep = mapStep;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }


}
