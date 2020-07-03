package huarongdao;

import GUI.SidePanel2;
import huarongdao.Trie;
import huarongdao.Road;
import huarongdao.Build_Graph;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
  *@Description: 对任意查询的地图状态返回其移动至成功状态的最短路径
  *@Author: lypl
  *@date: 2020-06-22
 **/

public class Solver {
	final static int R = Valid_Road_Genrator.R;
	final static int C = Valid_Road_Genrator.C;
	final static int RC = Valid_Road_Genrator.RC;
	final static int MAXNUM = 100010;
	static int qrd[] = new int[RC];
	static Road[] rd;
	static int rd_cnt;
	static Trie T;
	static int[] pre;
	static int[] dist;
	static Movement[] mv;

	/**
	  *@Description: 将编号为rdid的地图形态（状态）输出至文件
	  *@Param: 某地图形态（状态）的编号（在Trie中的编号）
	  *@return:
	  *@Author: lypl
	  *@date:
	 **/

	static void output_to_file(int rdid) {
		try {
			PrintStream out = new PrintStream(new FileOutputStream("ValidRoad/Road_" + rdid + ".txt"));
			for(int r = 0; r < R; ++r) {
				for(int c = 0; c < C; ++c) {
					if(c > 0) out.print(' ');
					out.print(rd[rdid].a[r*C + c]);
				}
				out.print('\n');
			}
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	  *@Description: 将各种地图形态输出到文件
	  *@Param: in: 输入流
	  *@return:
	  *@Author: lypl
	  *@date:
	 **/

	static void get_files(Scanner in) {
		int nf = in.nextInt();
		for(int cf = 1; cf <= nf; ++cf) {
			int id = in.nextInt();
			if(id < rd_cnt) output_to_file(id);
		}
	}

	/**
	  *@Description: 得到Road qrd移动至成功状态的最短路径
	  *@Param: qrd: 查询的地图形态; id: qrd的编号
	  *@return: qrd的路径
	  *@Author: lypl
	  *@date:
	 **/
	static int[][][] print_path(Road qrd, int id) {
		if(dist[id] == -1) {
			System.out.println("No Solution");
			return null;//可能有空指针错误
		}
		int[][][] ret = new int[MAXNUM][5][4];
		int pt = 1;
		ret[pt++] = qrd.show();
		int d = dist[id];
		ret[0][0][0] =d;
		while(pre[id] != -1) {
			mv[id].show(qrd.a);//写在textfield里
			Build_Graph.move(qrd.a, mv[id]); //让a（原图）变，形态也变了，需要normalize
			qrd.normalize();
			ret[pt++] = qrd.show();
			id = pre[id];
		}
		System.out.println("Shortest Solution Need " + d + " Movement(s)");
		return ret;
	}

//	static void query(Scanner in) {
//		System.out.println("please input query times: ");
//		int q = in.nextInt();
//		for(int cq = 1; cq <= q; ++cq) {
//			System.out.println("Please input road state: ");
//			Road qrd = new Road();
//			for(int i = 0; i < RC; ++i) {
//				qrd.a[i] = in.nextInt();
//			}
//			qrd.normalize();
//			int rdid = T.retrieval(qrd);
//
//			System.out.println("Road id: " + rdid);//找到该road对应的最小化表示(形状)
//			print_path(qrd, rdid);
//
//
//		}
//	}

//	static void query(int[] mp) {
//		//System.out.println("Please input road state: ");
//		Road qrd = new Road();
//		for(int i = 0; i < RC; ++i) {
//			qrd.a[i] = mp[i];
//		}
//		qrd.normalize();
//		int rdid = T.retrieval(qrd);
//
//		System.out.println("Road id: " + rdid);//找到该road对应的最小化表示(形状)
//		print_path(qrd, rdid);
//	}

	/**
	  *@Description: 查询地图mp的自动寻路路径
	  *@Param: mp: 待查询的地图
	  *@return: mp的路径
	  *@Author: lypl
	  *@date:
	 **/
	public static int[][][] query(int[][] mp) {
		//System.out.println("Please input road state: ");
		Road qrd = new Road();
		int ptr = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				qrd.a[ptr++] = mp[i][j];
			}
		}
		qrd.normalize();
		int rdid = T.retrieval(qrd);

		System.out.println("Road id: " + rdid);//找到该road对应的最小化表示(形状)
		int[][][] ret = new int[MAXNUM][5][4];
		ret = print_path(qrd, rdid);
		return ret;
	}

	/**
	  *@Description: 判断得到的所有合法最小化地图是否是最小化的
	  *@Param:
	  *@return: 所有地图都最小化：true；否则：false
	  *@Author: lypl
	  *@date:
	 **/

	static boolean all_normal() {
		for(int i = 0; i < rd_cnt; ++i) {
			if(!rd[i].normal()) {
				rd[i].show();
				return false;
			}
		}
		return true;
	}

	/**
	  *@Description: 测试输出路径情况
	  *@Param:
	  *@return:
	  *@Author: lypl
	  *@date:
	 **/

	static void test(Scanner in) {
		while(in.hasNext()) {
			int qid = in.nextInt();
			print_path(rd[qid], qid);
		}
	}

	/**
	  *@Description: 自动寻路初始化操作
	  *@Param:
	  *@return:
	  *@Author: lypl
	  *@date:
	 **/
	public static void solve() {
		Valid_Road_Genrator.get_valid_road();
		rd = Valid_Road_Genrator.vrd;
		rd_cnt = Valid_Road_Genrator.rd_cnt;
		if(all_normal()) System.out.println("All Normal!!!");
		else System.out.println("Error!!!");
//		rd[53273].show();
		Build_Trie.get_trie();
		T = Build_Trie.T;
		Escape_Path.get_path();
		pre = Escape_Path.pre;
		dist = Escape_Path.dist;
		mv = Escape_Path.mv;
		Scanner in = new Scanner(System.in);
//		get_files(in);
//		query(in);
//		test(in);
//		in.close();
//		System.out.println("Solver Finish");
	}
}

