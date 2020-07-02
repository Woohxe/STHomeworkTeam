package commons;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.File;

import java.util.Scanner;

/**
 * Created by l on 2020/7/1.
 */
public class GameMap {
    final static int mapNum = 11;
    static int[][][] mp = new int[mapNum][5][4];

    public static void readMap() {
        //读入文件mp
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
    public static int[][] getMap(int id) {
        return mp[id];
    }

/*
    public static void main(String args[]) {
        GameMap.readMap();
//
    }
*/
}
