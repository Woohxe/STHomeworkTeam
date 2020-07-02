package huarongdao;

public class Movement {
	public static String[] dir = {"up", "down", "left", "right"};
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	int pos, mt;
	void show(int[] a) {
		System.out.println(Blocks.s_name[a[pos]]+ "(" + Blocks.l_name[a[pos]] + ") move " + Movement.dir[mt]);
	}
	Movement(int block_pos, int move_type) {
		pos = block_pos;//一维下标，块左上角
		mt = move_type;//dir[]下标：0, 1, 2, 3
	}
}
