package huarongdao;

import GUI.MainFrame;
import GUI.SidePanel2;

import javax.swing.*;

import static GUI.SidePanel2.recordTextArea;

/**
  *@Description: 移动描述：某任务向某方向移动
  *@Author: lypl
  *@date: 2020-06-21
 **/

public class Movement {
	public static String[] dir = {"up", "down", "left", "right"};
	public static int[] dx = {-1, 1, 0, 0};
	public static int[] dy = {0, 0, -1, 1};
	int pos, mt;

	/**
	  *@Description: 在GUI界面显示人物移动信息
	  *@Param: 某Road节点的a数组（人物信息）
	  *@return:
	  *@Author: lypl
	  *@date:
	 **/

	void show(int[] a) {
		JLabel label = new JLabel(Blocks.s_name[a[pos]]+ "(" + Blocks.l_name[a[pos]] + ") move " + Movement.dir[mt]);
		recordTextArea.add(label);
	}

	/**
	  *@Description: 构造函数
	  *@Param: block_pos: 该地图块左上角在a数组的下标; move_type: 移动的方向
	  *@return:
	  *@Author: lypl
	  *@date:
	 **/

	public Movement(int block_pos, int move_type) {
		pos = block_pos; //一维下标，块左上角
		mt = move_type; //dir[]下标：0, 1, 2, 3
	}
}
