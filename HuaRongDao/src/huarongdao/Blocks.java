package huarongdao;

public class Blocks {
	//全局的块
	//每个人物的id：0, 1, 2, 3, ……, 11
	public static String[] s_name = {"CC", "GY", "ZF", "MC", "HZ", "ZY", "Z0", "Z1", "Z2", "Z3", "S0", "S1"};
	public static String[] l_name = {"CaoMengde", "GuanYunchang", "ZhangYide", "MaMengqi", "HuangHansheng", "ZhaoZilong", "ZuZero", "ZuOne", "ZuTwo", "ZuThree", "SpaceZero", "SpaceOne"};
	public static int[] type = {0, 1, 2, 2, 2, 2, 3, 3, 3, 3, 4, 4};//different shape kinds of blocks,每个id的type = blocks.type[id]
	public static int[] num = {0, 0, 0, 1, 2, 3, 0, 1, 2, 3, 0, 1};//属于这个type的第几个
	public static int[] r = {2, 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1};
	public static int[] c = {2, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	public static int[] sz = {1, 1, 4, 4, 2};//each type's cnt
	public static int[][] id = {{0}, {1}, {2, 3, 4, 5}, {6, 7, 8, 9}, {10, 11}};
}