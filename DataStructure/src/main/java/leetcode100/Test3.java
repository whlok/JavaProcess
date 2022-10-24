package leetcode100;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @author wenhoulai
 */
public class Test3 {
	public static int lengthOfLongestSubstring(String s) {
		if (s.equals("")) {
			return 0;
		}

		Map<Character, Integer> map = new HashMap<>();
		int maxLen = 0;

		for (int i = 0; i < s.length(); i++) {
			if (!map.containsKey(s.charAt(i))) {
				map.put(s.charAt(i), i);
			} else {
				int len = i - map.get(s.charAt(i));
				map.put(s.charAt(i), i);
				if (len > maxLen) {
					maxLen = len;
				}
			}
		}

		// 如果未出现重复的
		if (maxLen == 0) {
			return s.length();
		}
		// 如果只出现了一个重复的
		if (maxLen == 1 && map.size() != 1) {
			return map.size();
		}
		return maxLen;
	}

	public static void main(String[] args) {
		String s = "abcabcbb";
		String s2 = "bbbbb";
		String s3 = " ";
		String s4 = "ca";
		String s5 = "aab";
		String s6 = "abba";

		int val = lengthOfLongestSubstring(s6);
		System.out.println(val);
	}
}
