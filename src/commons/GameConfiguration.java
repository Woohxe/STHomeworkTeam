package commons;


import GUI.GamePanel1;

/**
 * 此类为游戏配置，定义了游戏的一些基本参数。
 * Created by lero on 2020/6/30.
 */
public class GameConfiguration {

    //baseLength规定了游戏界面中每一格的长度
    private int baseLength = 150;
    //定义了用户鼠标触发监听器的拖动长度
    private int LIMIT = 150;
    //定义游戏模式
    private int GameMode = 1;
    //定义游戏界面的行数
    private int rowNum = 5;
    //定义游戏界面的列数
    private int columnNum = 4;

    //定义游戏状态是否为自动寻路
    private boolean aoto = false;
    //定义是否计时
    private boolean timing = false;
    //
    private int mapStep[][];//二维数组
    //定义游戏中的地图标号
    private int gameId;

    /**
     * 构造方法，传入游戏界面1，获取界面中用户选定的地图标号
     * 调用initMap()方法，初始化地图
     * @param gamePanel1
     */
    public GameConfiguration(GamePanel1 gamePanel1) {
        gameId = gamePanel1.getChooseNum();
        initMap();

    }

    /**
     * 初始化地图
     */
    public void initMap() {
        mapStep = GameMap.getMap(gameId);
    }

    /**
     *
     * @param baseLength
     * @param gameMode
     */
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
