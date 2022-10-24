package Collection;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * 使用ArrayList存储多个学生的分数.
 *
 * @author wenhoulai
 */
public class TestArrayList1 {
	@SuppressWarnings({"all"})
	public static void main(String[] args) {
		//创建一个ArrayList对象
		ArrayList list = new ArrayList();
		//向集合中添加多个分数
		list.add(78); //加到最后
		list.add(89);
		list.add(56);
		//list.add(new Integer(56));
		list.add(89);
		list.add(2, 100);//加到指定位置  底层发生了元素大量后移
		//list.add(10,20);
		ArrayList list2 = new ArrayList();
		list2.add(60);
		list2.add(58);
		list2.add(29);
		//list.addAll(list2);
		list.addAll(0, list2);
		//输出集合中分数
		System.out.println(list.size());//4
		System.out.println(list);//[]
		//遍历1：使用for循环
		System.out.println("遍历1：使用for循环");
		for (int i = 0; i < list.size(); i++) {
			//获取第i个元素
			int elem = (Integer) list.get(i);//自动拆箱
			//输出第i个元素
			System.out.println(i + "    " + elem);
		}
		//遍历2：使用增强的for循环
		System.out.println("遍历2：使用增强的for循环");
		for (Object elem : list) {
			//System.out.println(elem);
			Integer i = (Integer) elem;
			System.out.println(i);
		}
		//遍历3：使用Iterator迭代器
		System.out.println("遍历3：使用Iterator迭代器");
		Iterator it = list.iterator();
		while (it.hasNext()) {//还有元素，没有就结束循环
			//如果有，就取出来
			int elem = (Integer) it.next();
			//输出来
			System.out.println(elem);
		}

		//遍历4：forEach
		System.out.println("遍历4：使用forEach");
		list.forEach((elem) -> System.out.println(elem));
	}
}
