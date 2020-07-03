package huarongdao;

import huarongdao.Road;

/**
  *@Description: 用于存储所有最小化表示法的地图Road
  *@Author: lypl
  *@date: 2020-06-20
 **/

public class Trie {
	final static int R = 5;
	final static int C = 4;
	final static int RC = R*C;
	final static int MAXID = 12;
	final static int VALID_ROAD = 65880;
	final static int MAXNODE = VALID_ROAD*MAXID + 5;
	final static int TYPE = 5; //地图块的形状数
	final static int[] sz = Blocks.sz; //每种形状的地图块有几个

	/**
	  *@Description: Trie节点
	  *@Author: lypl
	  *@date: 2020-06-20
	 **/

	class TrieNode {
		int[] Son;
		int rid;
		public TrieNode() {
			Son = new int[RC];
			for(int i = 0; i < RC; ++i) {
				Son[i] = -1;
			}
			rid = -1;
		}
	}
	
	TrieNode[] tn;
	int tot, root;

	/**
	  *@Description: 新建一个Trie节点
	  *@Param:
	  *@return: ret: 新建节点编号
	  *@Author: lypl
	  *@date: 2020-06-20
	 **/

	int new_node() {
		int ret = tot;
		tn[tot++] = new TrieNode();
		return ret;
	}

	/**
	  *@Description: 往Trie中插入一个地图Road
	  *@Param: rd: 待插入的地图; id: 该Road的编号
	  *@return:
	  *@Author: lypl
	  *@date:
	 **/

	void insert(Road rd, int id) {
		int pos = root;
		for(int i = 0; i < TYPE; ++i) {
			for(int j = 0; j < sz[i]; ++j) {
				if(tn[pos].Son[rd.p[i][j]] == -1) tn[pos].Son[rd.p[i][j]] = new_node();
				pos = tn[pos].Son[rd.p[i][j]];
			}
		}
		tn[pos].rid = id;
	}

	/**
	  *@Description: 遍历Trie找到Road的编号
	  *@Param: 一个最小化表示的Road的p数组
	  *@return: 该Road的编号
	  *@Author: lypl
	  *@date:
	 **/

	int retrieval(int[][] p) {
		int pos = root;
		for(int i = 0; i < TYPE; ++i) {
			for(int j = 0; j < sz[i]; ++j) {
				if(tn[pos].Son[p[i][j]] == -1) return -1;
				pos = tn[pos].Son[p[i][j]];
			}
		}
		return tn[pos].rid;
	}

	/**
	 *@Description: 遍历Trie找到Road的编号
	 *@Param: rd：一个最小化表示的Road
	 *@return: 该Road的编号
	 *@Author: lypl
	 *@date:
	 **/
	int retrieval(Road rd) {
		return retrieval(rd.p);
	}
	public Trie() {
		tot = 0;
		tn = new TrieNode[MAXNODE];
		root = new_node();
	}
}
