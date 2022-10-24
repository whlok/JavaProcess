package leetcode100;

import java.util.HashMap;
import java.util.Stack;

/**
 * 有效的括号。
 * <p>
 * https://leetcode.cn/problems/valid-parentheses/?favorite=2cktkvj
 *
 * @author wenhoulai
 */
public class Test20 {
	public static boolean isValid(String s) {
		if (s.length() % 2 == 1) return false;

		Stack<Character> stack = new Stack<>();
		HashMap<Character, Character> map = new HashMap<>();

		map.put('}', '{');
		map.put(']', '[');
		map.put(')', '(');

		for (int i = 0; i < s.length(); i++) {
			char str = s.charAt(i);
			if (!map.containsKey(str)) {
				stack.push(str);
			} else if (stack.size() == 0 || map.get(str) != stack.pop()) {
				return false;
			}
		}
		return stack.size() == 0;
	}

	public static void main(String[] args) {
		String s = "){";
		if (isValid(s)) {
			System.out.println("true");
		} else {
			System.out.println("false");
		}
	}
}
