package Collection;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 使用泛型保证集合操作的安全和简便.
 *
 * @author wenhoulai
 */
public class TestArrayList2 {
	public static void main(String[] args) {
		//创建一个ArrayList对象
		ArrayList<Integer> list = new ArrayList<Integer>();
		//向集合中添加多个分数
		list.add(78); //加到最后
		list.add(89);
		list.add(56);
		//list.add(new Integer(56));
		list.add(89);
		list.add(2, 100);
		//list.add(10,20);
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(60);
		list2.add(58);
		list2.add(29);
		list.addAll(0, list2);
		//输出集合中分数
		System.out.println(list.size());//4
		System.out.println(list);//[]
		//遍历1：使用for循环
		System.out.println("遍历1：使用for循环");
		for (int i = 0; i < list.size(); i++) {
			//获取第i个元素
			int elem = list.get(i);//自动拆箱
			//输出第i个元素
			System.out.println(i + "    " + elem);
		}
		//遍历2：使用增强的for循环
		System.out.println("遍历2：使用增强的for循环");
		for (Integer elem : list) {
			System.out.println(elem);
			//Integer i = (Integer)elem;
			//System.out.println(i);
		}
		//遍历3：使用Iterator迭代器
		System.out.println("遍历3：使用Iterator迭代器");
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {//还有元素，没有就结束循环
			//如果有，就取出来
			int elem = it.next();
			//输出来
			System.out.println(elem);
		}
	}
}
