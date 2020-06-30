package commons;

/**
 * Created by lero on 2020/6/30.
 */
public class GameConfiguration {
    public int BASELENGTH = 150; // 单位块长度
    public int LIMIT = 100;
    private int GameMode = 1;
    private int rowNum = 5;
    private int columnNum = 4;

    private int startX = 0;
    private int startY = 0;
    private boolean aoto = false;
    private boolean timeLimited = false;

    public GameConfiguration() {
    }

    public int getLIMIT() {
        return LIMIT;
    }

    public void setLIMIT(int LIMIT) {
        this.LIMIT = LIMIT;
    }

    public int getBASELENGTH() {
        return BASELENGTH;
    }

    public void setBASELENGTH(int BASELENGTH) {
        this.BASELENGTH = BASELENGTH;
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

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public boolean isAoto() {
        return aoto;
    }

    public void setAoto(boolean aoto) {
        this.aoto = aoto;
    }

    public boolean isTimeLimited() {
        return timeLimited;
    }

    public void setTimeLimited(boolean timeLimited) {
        this.timeLimited = timeLimited;
    }
}
