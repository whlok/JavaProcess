package leetcode100;

/**
 * 俩数相加。
 *
 * @author wenhoulai
 */
public class Test2 {
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode list = new ListNode();
		ListNode res = list;

		int flag = 0;
		while (l1 != null || l2 != null || flag != 0) {
			int val1 = l1 != null ? l1.val : 0;
			int val2 = l2 != null ? l2.val : 0;

			int val = val1 + val2 + flag;

			list.next = new ListNode(val % 10);

			flag = val / 10;
			
			if (l1 != null) l1 = l1.next;
			if (l2 != null) l2 = l2.next;

			list = list.next;
		}
		return res.next;
	}

	public static void main(String[] args) {
		ListNode l1 = new ListNode(9);
		ListNode l2 = new ListNode(1);

		ListNode node = addTwoNumbers(l1, l2);

		while (node != null) {
			System.out.println(node.val);
			node = node.next;
		}
	}
}
