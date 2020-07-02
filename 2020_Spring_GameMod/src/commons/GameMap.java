package commons;

import java.io.FileNotFoundException;
import java.io.File;

import java.util.Scanner;

/**
 *
 * function: 从地图文件读入地图
 * Created by lypl on 2020/7/1.
 */
public class GameMap {
    final static int mapNum = 11; //可选地图数目
    static int[][][] mp = new int[mapNum][5][4]; //所有地图

    /**
     * @function 从地图文件读入地图
     * @param
     * @return
     */
    public static void readMap() {
//        File directory = new File("./");
//        System.out.println(directory.getAbsolutePath());
        Scanner sin;
        for(int id = 0; id < mapNum; id++) {
            try {
                sin = new Scanner(new File("ValidGraph/graph_" + id + ".txt"));
                for(int i = 0; i < 5; i++) {
                    for(int j = 0; j < 4; j++) {
                        mp[id][i][j] = sin.nextInt();
                    }
                }
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        /*
        //test
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 4; j++) {
                System.out.print(mp[0][i][j] + " ");
            }
            System.out.println("");
        }
        */
    }

    /**
     *
     * @param id：地图(在Trie中的)编号
     * @return int[][] map
     */
    public static int[][] getMap(int id) {
        return mp[id];
    }

/*
    // test
    public static void main(String args[]) {
        GameMap.readMap();
    }
*/
}
