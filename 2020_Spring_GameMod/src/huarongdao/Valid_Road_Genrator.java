package huarongdao;

import huarongdao.Blocks;
import huarongdao.Road;

/**
  *@Description: 获取所有合法的最小表示法的Road
  *@Author: lypl
  *@date: 2020-06-20
 **/
  
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
	
	/**
	  *@Description: 搜索前初始化工作
	  *@Param: 
	  *@return: 
	  *@Author: lypl
	  *@date: 
	 **/
	static void init() {
		for(int i = 0; i < RC; ++i) {
			rd[i] = -1;
		}
		for(int i = 0; i < MAXID; ++i) {
			p[i] = -1;
		}
		rd_cnt = 0;
	}
	
	/**
	  *@Description: 判断下标pos处能否放bid号人物
	  *@Param: pos: 地图一维数组的下标; bid: 人物id
	  *@return: bid号人物能放在pos处：放置并返回true；否则返回false
	  *@Author: lypl
	  *@date: 2020-006-20
	 **/
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
	
	/**
	  *@Description: 撤销pos处放置bid人物的操作
	  *@Param: 地图一维数组的下标; bid: 人物id
	  *@return: 
	  *@Author: lypl
	  *@date: 2020-006-20
	 **/
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
	
	/**
	  *@Description: 试探bid号人物可以放的位置
	  *@Param: bid: 人物编号，试探该人物的位置（之前的人物都已试探并放置完毕）; mi: 实现最小表示法的辅助变量
	  *@return: 
	  *@Author: lypl
	  *@date: 2020-06-20
	 **/
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
				//下一个人物：bid+1，同类形状的人物（bid）已经放过，bid+1只能从当前位置pos的下一个开始试探
				//保持最小化表示
				if(bid + 1 < MAXID && Blocks.type[bid] == Blocks.type[bid + 1]) dfs(bid + 1, i + 1);
				else dfs(bid + 1, 0);
//				dfs(bid + 1, 0);
				reset(i, bid);
			}
		}
	}
	
	/**
	  *@Description: 获取所有合法的最小表示法的Road
	  *@Param: 
	  *@return: 
	  *@Author: lypl
	  *@date: 
	 **/
	public static void get_valid_road() {
		init();
		dfs(0, 0);
		System.out.println("Valid Road Building Complete");
		System.out.println("get " + rd_cnt + " valid roads");
	}
	
	/**
	  *@Description: 测试所有合法最小化表示的Road
	  *@Param: 
	  *@return: 
	  *@Author: lypl
	  *@date: 
	 **/
	  
	public static void main(String[] args) {
		get_valid_road();
//		65880 
//		65880*1!*1!*4!*4!*2! = 75893760 
	}
}
