package Collection;

import java.util.ArrayList;

/**
 * ArrayList类的更多方法.
 *
 * @author wenhoulai
 */
public class TestArrayList3 {
	public static void main(String[] args) {
		//创建一个ArrayList对象
		ArrayList<Integer> list = new ArrayList<Integer>();
		//向集合中添加多个分数
		list.add(78); //加到最后
		list.add(89);
		list.add(56);
		//删除
		list.remove(0);
		list.remove(Integer.valueOf(78));
		//更新
		list.set(1, 65);
		//输出集合中分数
		//list.clear();
		System.out.println(list.contains(65));
		System.out.println(list.toString());
		//list.ensureCapacity(100);
	}
}

