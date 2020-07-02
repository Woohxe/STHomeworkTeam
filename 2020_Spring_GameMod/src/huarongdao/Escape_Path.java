package huarongdao;
import huarongdao.Road;
import huarongdao.Graph.Edge;

/**
  *@Description: 对所有游戏成功的节点寻找可能的通路
  *@Author: lypl
  *@date: 2020-06-22
 **/
public class Escape_Path {
	final static int R = 5;
	final static int C = 4;
	final static int RC = R*C;
	final static int VALID_ROAD = Valid_Road_Genrator.VALID_ROAD;
	static Road[] rd;
	static int rd_cnt;
	static int[] dist = new int[VALID_ROAD]; //第i号节点（Road在Trie中的编号）到达任意成功节点的最短路
	static int[] pre = new int[VALID_ROAD]; //用于记录路径
	static int[] q = new int[VALID_ROAD];
	static int lf, rt;
	static int[] head;
	static Edge[] E;
	static Movement[] mv = new Movement[VALID_ROAD];

	/**
	  *@Description: 将所有成功的节点放入队列进行多源bfs，得到任意节点（游戏状态）能到达成功节点的最短路
	  *@Param:
	  *@return:
	  *@Author: lypl
	  *@date:
	 **/
	static void bfs() {
		for(int i = 0; i < rd_cnt; ++i) {
			dist[i] = -1;
			pre[i] = -1;
		}
		lf = rt = 0;
		for(int i = 0; i < rd_cnt; ++i) {
			if(rd[i].a[17] == 0 && rd[i].a[18] == 0) {
				q[rt++] = i;
				dist[i] = 0;
			}
		}
		while(lf < rt) {
			int x = q[lf++];
			for(int i = head[x]; i != -1; i = E[i].nxt) {
				int y = E[i].to;
				if(dist[y] != -1) continue;
				dist[y] = dist[x] + 1;
				pre[y] = x;
				int np = E[i].m.pos + Movement.dx[E[i].m.mt]*C + Movement.dy[E[i].m.mt];
				int nmt = (E[i].m.mt ^ 1);//反向的mt
				mv[y] = new Movement(np, nmt);//np: 哪个块(左上角找到一维坐标); nmt: 移动的方向
				q[rt++] = y;
			}
		}
	}

	/**
	  *@Description: 执行bfs，得到所以合法状态到达成功状态的路径
	  *@Param:
	  *@return:
	  *@Author: lypl
	  *@date: 2020-06-22
	 **/
	public static void get_path() {
		rd = Valid_Road_Genrator.vrd;
		rd_cnt = Valid_Road_Genrator.rd_cnt;
		Build_Graph.get_graph();
		head = Build_Graph.head;
		E = Build_Graph.E;
		bfs();
		System.out.println("Escape Path Building Complete");
	}
}

