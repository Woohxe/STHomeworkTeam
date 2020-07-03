package huarongdao;

import java.util.Arrays;

/**
  *@Description: 每个地图的描述信息
  *@Author: lypl
  *@date: 2020-06-20
 **/

public class Road {
	final static int R = 5;
	final static int C = 4;
	final static int RC = R*C;
	final static int MAXID = 12;
	final static int TYPE = 5;
	final static int[] sz = Blocks.sz; //每种形状的块有多少个
	int[] a; //地图
	int[][] p; //p[i][j]:第i中形状的第j个块左上角在a中的下标
	// 0 1 2 2 2 2 3 3 3 3 4 4
	// [0, 1), [1, 2), [2, 6), [6, 10), [10, 12)
//	void show() {
//		System.out.println("+++++ Road State +++++");
//		for(int i = 0; i < R; ++i) {
//			for(int j = 0; j < C; ++j) {
//				if(j > 0) System.out.print(" ");
//				System.out.print(Blocks.s_name[a[i*C + j]]);
//			}
//			System.out.println();
//		}
////		for(int i = 0; i < TYPE; ++i) {
////			System.out.print("p[" + i + "]:");
////			for(int j = 0; j < sz[i]; ++j) System.out.print(" " + p[i][j]);
////			System.out.println();
////		}
//		System.out.println("----- Road State -----");
//	}

	/**
	  *@Description: 得到地图描述二维数组
	  *@Param:
	  *@return: 二维数组map
	  *@Author: lypl
	  *@date: 2020-06-20
	 **/

	int[][] show() {
		int[][] ret = new int[5][4];
		for(int i = 0; i < R; ++i) {
			for(int j = 0; j < C; ++j) {
				ret[i][j] = a[i*C + j];
				//System.out.print(ret[i][j] + " ");
			}
			//System.out.println("");
		}
		return ret;
	}

	/**
	  *@Description: 判断是否是用最小表示法表示的地图
	  *@Param:
	  *@return: 最小表示：true; 否则：false
	  *@Author: lypl
	  *@date: 2020-06-20
	 **/

	boolean normal() {
		for(int i = 0; i < TYPE; ++i) {
			for(int j = 1; j < sz[i]; ++j) {
				if(p[i][j - 1] >= p[i][j]) return false;
			}
		}
		return true;
	}

	/**
	  *@Description: 计算p数组
	  *@Param:
	  *@return: p数组
	  *@Author: lypl
	  *@date: 2020-06-20
	 **/

	void get_p() {
		for(int i = 0; i < TYPE; ++i) {
			for(int j = 0; j < sz[i]; ++j) {
				p[i][j] = -1;
			}
		}
		for(int i = 0; i < RC; ++i) {
			int x = a[i];
			int tid = Blocks.type[x], nid = Blocks.num[x];
			if(p[tid][nid] == -1) p[tid][nid] = i;
		}
	}

	/**
	  *@Description: 用最小表示法表示地图（最小化：表示地图形态）
	  *@Param:
	  *@return:
	  *@Author: lypl
	  *@date: 2020-06-20
	 **/

	void normalize() {
		get_p();
		for(int i = 0; i < TYPE; ++i) {
			Arrays.sort(p[i]);
		}
	}

	/**
	  *@Description: 构造函数，构造a、p数组
	  *@Param:
	  *@return: 最小化表示的Road
	  *@Author: lypl
	  *@date:
	 **/

	public Road() {
		a = new int[RC];
		p = new int[TYPE][];
		for(int i = 0; i < TYPE; ++i) {
			p[i] = new int[sz[i]];
		}
	}


	/**
	  *@Description: 用于build_Graph里get_edge里不能在原图里，需要复制
	  *@Param: x：一个Road
	  *@return: 与x相同的Road
	  *@Author: lypl
	  *@date:
	 **/

	public Road(Road x) {
		a = new int[RC];
		p = new int[TYPE][];
		for(int i = 0; i < TYPE; ++i) {
			p[i] = new int[sz[i]];
		}
		for(int i = 0; i < RC; ++i) a[i] = x.a[i];
		for(int i = 0; i < TYPE; ++i) {
			for(int j = 0; j < sz[i]; ++j) {
				p[i][j] = x.p[i][j];
			}
		}
	}
}
