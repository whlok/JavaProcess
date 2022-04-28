package dp;

/**
 * @author wenhoulai
 * <p>
 * 可选择的空间是多种状态
 * <p>
 * 322. 零钱兑换
 */
public class CoinChangeSolution {

	public int coinChange(int[] coins, int amount) {
		return doCoinChange(coins, amount);
	}

	public int doCoinChange(int[] coins, int amount) {
		// 递归的终止条件
		if (amount == 0) {
			return 0;
		}
		if (amount < 0) {
			return -1;
		}

		int minChangeNum = Integer.MAX_VALUE;

		// 在这里开始做选择
		for (int coin : coins) {

			int changeNum = doCoinChange(coins, amount - coin);
			if (changeNum == -1) {
				continue;
			}

			minChangeNum = Math.min(minChangeNum, changeNum + 1);
		}

		if (minChangeNum != Integer.MAX_VALUE) {
			return minChangeNum;
		}

		return -1;
	}

	public int doCoinChangeDp(int[] coins, int n) {
		int[] dp = new int[n + 1];

		for (int i = 1; i < dp.length; i++) {
			dp[i] = Integer.MAX_VALUE;
		}

		for (int i = 1; i <= n; i++) {
			for (int coin : coins) {
				if ((i - coin) < 0) {
					break;
				}
				dp[i] = Math.min(dp[i], dp[i - coin] + 1);
			}
		}
		if (dp[n] == Integer.MAX_VALUE) {
			return -1;
		}
		return dp[n];
	}

	public static void main(String[] args) {
		int[] coins = {1, 2, 5};
		int amount = 11;
		CoinChangeSolution solution = new CoinChangeSolution();
		int res = solution.coinChange(coins, amount);
		int resDp = solution.doCoinChangeDp(coins, amount);
		System.out.println(res);
		System.out.println(resDp);

	}
}
