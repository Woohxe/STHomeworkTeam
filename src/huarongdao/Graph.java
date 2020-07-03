package huarongdao;

/**
  *@Description: 链式前向星表示图
  *@Author: lypl
  *@date: 2020-06-21
 **/

public class Graph {
	final static int VALID_ROAD = Valid_Road_Genrator.VALID_ROAD;

	/**
	  *@Description: 有向边的描述
	  *@Author: lypl
	  *@date: 2020-06-21
	 **/
	class Edge {
		int to, nxt;
		Movement m;

		/**
		  *@Description: 构造函数
		  *@Param: _to: 边指向的一端点; _nxt: 该边另一端点的head值; _m: 移动信息
		  *@return:
		  *@Author: lypl
		  *@date:
		 **/
		public Edge(int _to, int _nxt, Movement _m) {
			to = _to;
			nxt = _nxt;
			m = _m;
		}
	}
	Edge[] E;
	int[] head;
	int tot;

	/**
	  *@Description: 向图中加入一条边
	  *@Param: u: 边的出发点；v: 边的指向点；m: 移动信息
	  *@return:
	  *@Author: lypl
	  *@date:
	 **/

	void add_edge(int u, int v, Movement m) {
		E[tot] = new Edge(v, head[u], m);
		head[u] = tot++;
	}
	
	/**
	  *@Description: 使用链式前向星建图前的初始化操作
	  *@Param: 
	  *@return: 
	  *@Author: lypl
	  *@date: 
	 **/
	  
	public Graph() {
		tot = 0;
		E = new Edge[VALID_ROAD*8*2 + 5];
		head = new int[VALID_ROAD];
		for(int i = 0; i < VALID_ROAD; ++i) {
			head[i] = -1;
		}
	}
}
