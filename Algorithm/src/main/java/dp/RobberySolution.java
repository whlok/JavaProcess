package dp;

/**
 * @author wenhoulai
 * <p>
 * 选择空间为俩种状态: 偷或不偷，但相邻俩个必须偷一个
 */
public class RobberySolution {

	public int maxRobberyMoney(int[] values) {
		return robberyMoney(values, 0);
	}

	public int robberyMoney(int[] values, int start) {
		// 递归终止条件
		if (start >= values.length) {
			return 0;
		}

		int maxValue = 0;

		// 在这里开始做选择
		int value = Math.max(values[start] + robberyMoney(values, start + 2), robberyMoney(values, start + 1));
		maxValue += value;

		return maxValue;
	}

	public int robberyMoneyDp(int[] nums) {
		int n = nums.length;

		int[] dp = new int[n];

		for (int i = 0; i < n; i++) {
			dp[i] = Integer.MIN_VALUE;
		}
		if (n == 1) {
			return nums[0];
		}
		if (n == 2) {
			return Math.max(nums[0], nums[1]);
		}
		dp[0] = nums[0];
		dp[1] = Math.max(dp[0], nums[1]);
		for (int i = 2; i < n; i++) {
			dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
		}

		return Math.max(dp[n - 1], dp[n - 2]);
	}

	public int robberyMoneyDp2(int[] nums) {
		int pprev = 0;
		int prev = 0;

		// 每次循环，计算“偷到当前房子为止的最大金额”
		for (int i : nums) {
			// 循环开始时，prev 表示 dp[k-1]，pprev 表示 dp[k-2]
			// dp[k] = max{ dp[k-1], dp[k-2] + i }
			int curr = Math.max(prev, pprev + i);
			pprev = prev;
			prev = curr;
			// 循环结束时，prev 表示 dp[k]，pprev 表示 dp[k-1]
		}

		return prev;
	}

	public static void main(String[] args) {
		int[] values = {2, 1};
		RobberySolution robberySolution = new RobberySolution();
		int res = robberySolution.robberyMoney(values, 0);
		int resDp = robberySolution.robberyMoneyDp(values);
		int resDp2 = robberySolution.robberyMoneyDp2(values);

		System.out.println(res);
		System.out.println(resDp);
		System.out.println(resDp2);


	}

}
