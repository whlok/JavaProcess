package dp;

/**
 * @author wenhoulai
 * <p>
 * 70.爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class ClimbStairsSolution {

	public int climbStairs(int[] steps, int n) {
		// 递归终止条件
		if (n < 0) {
			return 0;
		}
		if (n == 0) {
			return 1;
		}
		int allSteps = 0;

		for (int step : steps) {
			allSteps += climbStairs(steps, n - step);
		}

		return allSteps;
	}

	public int climbStairsDp(int[] jumps, int n) {

		int[] dp = new int[n + 1];
		if (n <= 2) {
			return n;
		}

		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}

		return dp[n];
	}

	public static void main(String[] args) {
		int[] steps = {1, 2};
		int n = 6;
		ClimbStairsSolution solution = new ClimbStairsSolution();
		int res = solution.climbStairs(steps, n);
		int res_dp = solution.climbStairsDp(steps, n);
		System.out.println(res);
		System.out.println(res_dp);

	}
}
