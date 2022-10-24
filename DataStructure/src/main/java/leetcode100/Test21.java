package leetcode100;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * @author wenhoulai
 */
public class Test21 {


	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

		ListNode list = new ListNode();
		ListNode res = list;

		while (list1 != null && list2 != null) {
			if (list1.val < list2.val) {
				list.next = new ListNode(list1.val);
				list1 = list1.next;
			} else {
				list.next = new ListNode(list2.val);
				list2 = list2.next;
			}
			list = list.next;
		}

		list.next = list1 != null ? list1 : list2;

		return res.next;
	}

	public static void main(String[] args) {
	}
}
