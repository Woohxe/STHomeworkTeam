package huarongdao;

import huarongdao.Trie;
import huarongdao.Road;

public class Build_Trie {
	final static int R = Valid_Road_Genrator.R;
	final static int C = Valid_Road_Genrator.C;
	final static int RC = Valid_Road_Genrator.RC;
	static int qrd[] = new int[RC];
	static Road[] rd;
	static int rd_cnt;
	static Trie T;
	public static void get_trie() {
		rd = Valid_Road_Genrator.vrd;
		rd_cnt = Valid_Road_Genrator.rd_cnt;
		T = new Trie();
		for(int i = 0; i < rd_cnt; ++i) {
			T.insert(rd[i], i);
		}
		System.out.println("Trie Building Complete");
	}
}
