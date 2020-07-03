package huarongdao;

import huarongdao.Trie;
import huarongdao.Graph.Edge;
import huarongdao.Road;
import huarongdao.Movement;

/**
  *@Description: 将所有最小化Road（地图所有形态）作为状态节点建图
  *@Author: lypl
  *@date: 2020-06-21
 **/
  
public class Build_Graph {
	final static int R = 5;
	final static int C = 4;
	final static int RC = R*C;
	final static int MAXID = 12;
	final static int VALID_ROAD = 65880;
	final static int TYPE = 5;
	static Road[] rd;
	static int rd_cnt;
	static Graph g;
	static int[] head;
	static Edge[] E;
	static Trie T;
	
	/**
	  *@Description: 利用m中的移动信息改变地图上人物的位置
	  *@Param: a: 地图人物位置描述；m: 移动信息
	  *@return: 
	  *@Author: lypl
	  *@date: 
	 **/
	public static void move(int[] a, Movement m) {
		int pos = m.pos;
		int bid = a[pos];
		int mvid = m.mt;
		int x = pos/C, y = pos%C;
		int r = Blocks.r[bid], c = Blocks.c[bid];
		if(mvid == 0) {
			for(int i = 0; i < c; ++i) {
				int nx = x - 1, ny = y + i;
				int up = nx*C + ny;
				int down = (x + r - 1)*C + ny;
				int tmp = a[up];
				a[up] = a[down];
				a[down] = tmp;
			}
		} else if(mvid == 1) {
			for(int i = 0; i < c; ++i) {
				int nx = x + r, ny = y + i;
				int down = nx*C + ny;
				int up = x*C + ny;
				
				int tmp = a[up];
				a[up] = a[down];
				a[down] = tmp;
			}
		} else if(mvid == 2) {
			for(int i = 0; i < r; ++i) {
				int nx = x + i, ny = y - 1;
				int left = nx*C + ny;
				int right = nx*C + (y + c - 1);
				int tmp = a[left];
				a[left] = a[right];
				a[right] = tmp;
			}
		} else {
			for(int i = 0; i < r; ++i) {
				int nx = x + i, ny = y + c;
				int right = nx*C + ny;
				int left = nx*C + y;
				int tmp = a[left];
				a[left] = a[right];
				a[right] = tmp;
			}
		}
	}
	
	/**
	  *@Description: 判断是否可以移动
	  *@Param: a: 地图人物位置描述；m: 移动信息
	  *@return:
	  *@Author: lypl
	  *@date:
	 **/
	public static boolean can_move(int[] a, Movement m) {
		//空格10、11,上下是列C，左右是行R
		int pos = m.pos;
		int bid = a[pos];
		int mvid = m.mt;
		int x = pos/C, y = pos%C;
		int r = Blocks.r[bid], c = Blocks.c[bid];
		if(mvid == 0) {
			for(int i = 0; i < c; ++i) {
				int nx = x - 1, ny = y + i;
				if(nx < 0 || a[nx*C + ny] <= 9) return false;
			}
		} else if(mvid == 1) {
			for(int i = 0; i < c; ++i) {
				int nx = x + r, ny = y + i;
				if(nx >= R || a[nx*C + ny] <= 9) return false;
			}
		} else if(mvid == 2) {
			for(int i = 0; i < r; ++i) {
				int nx = x + i, ny = y - 1;
				if(ny < 0 || a[nx*C + ny] <= 9) return false;
			}
		} else {
			for(int i = 0; i < r; ++i) {
				int nx = x + i, ny = y + c;
				if(ny >= C || a[nx*C + ny] <= 9) return false;
			}
		}
		return true;
	}

	/**
	  *@Description: 对一个状态节点x连边
	  *@Param: x: 待连边的Road; xid: Road状态x的编号
	  *@return:
	  *@Author: lypl
	  *@date:
	 **/
	static void get_edge(Road x, int xid) {
		//对每个人物(id)上下左右移动：能移动变成另一种状态就移动（连边）；
		for(int i = 0; i < MAXID; ++i) {
			int ti = Blocks.type[i], ni = Blocks.num[i];
			int pos = x.p[ti][ni]; 
			for(int j = 0; j < 4; ++j) {
				Road y = new Road(x);
				Movement m = new Movement(pos, j); //a[pos] = bid
				if(can_move(y.a, m)) {
					move(y.a, m);
					//move后，a变化，normalize()时p也变化
					y.normalize();
					int yid = T.retrieval(y);
					if(yid == -1) {
						m.show(y.a);
						x.show();
						y.show();
						System.exit(1);
						return ;
					}
					g.add_edge(xid, yid, m);
				}
			}
		}
	}

	/**
	  *@Description: 将所有最小化Road（地图所有形态）作为状态节点建图
	  *@Param:
	  *@return:
	  *@Author: lypl
	  *@date:
	 **/

	public static void get_graph() {
		rd = Valid_Road_Genrator.vrd;
		rd_cnt = Valid_Road_Genrator.rd_cnt;
		T = Build_Trie.T;//存储最小化表示（形状+特定id顺序）
		g = new Graph();
		head = g.head;
		E = g.E;
		for(int i = 0; i < rd_cnt; ++i) {
			get_edge(rd[i], i);//rd里存的是最小化以后的road，他们的a就是最小化后的人物id顺序
		}
		System.out.println("Graph Building Complete");
		System.out.println("This Graph Contains " + g.tot + " Edges");
	}
}

