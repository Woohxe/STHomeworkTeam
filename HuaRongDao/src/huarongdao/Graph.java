package huarongdao;

public class Graph {
	final static int VALID_ROAD = Valid_Road_Genrator.VALID_ROAD;
	class Edge {
		int to, nxt;
		Movement m;
		public Edge(int _to, int _nxt, Movement _m) {
			to = _to;
			nxt = _nxt;
			m = _m;
		}
	}
	Edge[] E;
	int[] head;
	int tot;
	void add_edge(int u, int v, Movement m) {
		E[tot] = new Edge(v, head[u], m);
		head[u] = tot++;
	}
	public Graph() {
		tot = 0;
		E = new Edge[VALID_ROAD*8*2 + 5];
		head = new int[VALID_ROAD];
		for(int i = 0; i < VALID_ROAD; ++i) {
			head[i] = -1;
		}
	}
}
