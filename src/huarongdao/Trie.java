package huarongdao;

import huarongdao.Road;
public class Trie {
	final static int R = 5;
	final static int C = 4;
	final static int RC = R*C;
	final static int MAXID = 12;
	final static int VALID_ROAD = 65880;
	final static int MAXNODE = VALID_ROAD*MAXID + 5;
	final static int TYPE = 5;
	final static int[] sz = Blocks.sz;
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
	int new_node() {
		int ret = tot;
		tn[tot++] = new TrieNode();
		return ret;
	}
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
	int retrieval(Road rd) {
		return retrieval(rd.p);
	}
	public Trie() {
		tot = 0;
		tn = new TrieNode[MAXNODE];
		root = new_node();
	}
}
