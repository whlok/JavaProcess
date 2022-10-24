package leetcode100;

import java.util.HashMap;

/**
 * 俩数之和。
 * <p>
 * https://leetcode.cn/problems/two-sum
 *
 * @author wenhoulai
 */
public class Test1 {

	public static int[] twoSum(int[] nums, int target) {
		int[] res = new int[2];
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int val = target - nums[i];
			if (map.containsKey(val)) {
				res[0] = map.get(val);
				res[1] = i;
				break;
			} else {
				map.put(nums[i], i);
			}

		}
		return res;
	}

	public static void main(String[] args) {
		int[] nums = {1, 2, 3, 4, 6};
		int[] res = twoSum(nums, 8);
		System.out.println(res[0]);
		System.out.println(res[1]);

	}

}
