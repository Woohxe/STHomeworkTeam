package huarongdao;
import huarongdao.Road;
import huarongdao.Graph.Edge;
public class Escape_Path {
	final static int R = 5;
	final static int C = 4;
	final static int RC = R*C;
	final static int VALID_ROAD = Valid_Road_Genrator.VALID_ROAD;
	static Road[] rd;
	static int rd_cnt;
	static int[] dist = new int[VALID_ROAD];
	static int[] pre = new int[VALID_ROAD];
	static int[] q = new int[VALID_ROAD];
	static int lf, rt;
	static int[] head;
	static Edge[] E;
	static Movement[] mv = new Movement[VALID_ROAD];
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

