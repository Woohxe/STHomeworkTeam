package huarongdao;

import huarongdao.Blocks;
import huarongdao.Road;
public class Valid_Road_Genrator {
	final static int R = 5;
	final static int C = 4;
	final static int RC = R*C;
	final static int MAXID = 12;
	final static int VALID_ROAD = 65880;
	final static int TYPE = 5;
	public static int rd_cnt;
	public static Road[] vrd = new Road[VALID_ROAD];
	static int[] rd = new int[R*C];//搜索试探的一种可能：rd:Road的a[]
	static int[] sz = Road.sz;
	static int[] p = new int[MAXID];//搜索试探的一种可能：p[i]：第i(< 12)号人物的一维下标
	static void init() {
		for(int i = 0; i < RC; ++i) {
			rd[i] = -1;
		}
		for(int i = 0; i < MAXID; ++i) {
			p[i] = -1;
		}
		rd_cnt = 0;
	}
	static boolean valid(int pos, int bid) {
		int x = pos/C, y = pos%C;
		//扫描该人物块里的所有格子（2*2 / 2*1/ 1*1 / 1*2）
		for(int i = 0; i < Blocks.r[bid]; ++i) {
			int nx = x + i;
			if(nx < 0 || nx >= R) return false;//越界
			for(int j = 0; j < Blocks.c[bid]; ++j) {
				int ny = y + j;
				if(ny < 0 || ny >= C) return false;//越界
				if(rd[nx*C + ny] != -1) return false;//被填过
			}
		}
		//bid左上角可以放在下标pos的位置
		p[bid] = pos;
		//把bid对应长宽的块填满
		for(int i = 0; i < Blocks.r[bid]; ++i) {
			int nx = x + i;
			for(int j = 0; j < Blocks.c[bid]; ++j) {
				int ny = y + j;
				rd[nx*C + ny] = bid;//转化成一维的
			}
		}
		return true;
	}
	static void reset(int pos, int bid) {
		int x = pos/C, y = pos%C;
		p[bid] = -1;
		for(int i = 0; i < Blocks.r[bid]; ++i) {
			int nx = x + i;
			for(int j = 0; j < Blocks.c[bid]; ++j) {
				int ny = y + j;
				rd[nx*C + ny] = -1;
			}
		}
	}
	static void dfs(int bid, int mi) {
		if(bid == MAXID) {
			vrd[rd_cnt] = new Road();
			for(int i = 0; i < RC; ++i) {
				vrd[rd_cnt].a[i] = rd[i];
			}
			for(int i = 0; i < MAXID; ++i) {
				int tid = Blocks.type[i], nid = Blocks.num[i];
				vrd[rd_cnt].p[tid][nid] = p[i];
			}
			++rd_cnt;
//			if(rd_cnt%100000 == 0) System.out.println(rd_cnt/100000);
			return ;
		}
		//第i号人物左上角可放的位置（一维下标）
		for(int i = mi; i < RC; ++i) {
			if(valid(i, bid)) {
				if(bid + 1 < MAXID && Blocks.type[bid] == Blocks.type[bid + 1]) dfs(bid + 1, i + 1);
				else dfs(bid + 1, 0);
//				dfs(bid + 1, 0);
				reset(i, bid);
			}
		}
	}
	public static void get_valid_road() {
		init();
		dfs(0, 0);
		System.out.println("Valid Road Building Complete");
		System.out.println("get " + rd_cnt + " valid roads");
	}
	//test
	public static void main(String[] args) {
		get_valid_road();
//		65880 
//		65880*1!*1!*4!*4!*2! = 75893760 
	}
}
