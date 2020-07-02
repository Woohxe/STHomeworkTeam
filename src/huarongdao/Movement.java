package huarongdao;

import GUI.MainFrame;
import GUI.SidePanel2;

import javax.swing.*;

import static GUI.SidePanel2.recordTextArea;

public class Movement {
	public static String[] dir = {"up", "down", "left", "right"};
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	int pos, mt;

	void show(int[] a) {
		JLabel label = new JLabel(Blocks.s_name[a[pos]]+ "(" + Blocks.l_name[a[pos]] + ") move " + Movement.dir[mt]);
		recordTextArea.add(label);
	}

	public Movement(int block_pos, int move_type) {
		pos = block_pos;//一维下标，块左上角
		mt = move_type;//dir[]下标：0, 1, 2, 3
	}
}
