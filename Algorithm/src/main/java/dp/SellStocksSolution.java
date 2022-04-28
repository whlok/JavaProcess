package dp;

/**
 * @author wenhoulai
 * <p>
 * 121. 买卖股票的最佳时机
 */
public class SellStocksSolution {

	public int sellStock(int[] days) {
		return sellStock_rec(days, 0);
	}

	/**
	 * 暴力求解
	 */
	public int sellStock_for(int[] days) {

		int maxValue = 0;

		for (int i = 0; i < days.length - 1; i++) {
			for (int j = i + 1; j < days.length - 1; j++) {
				maxValue = Math.max(maxValue, days[j] - days[i]);
			}
		}

		return maxValue;
	}

	/**
	 * 递归思想
	 */
	public int sellStock_rec(int[] days, int buyDay) {
		if (buyDay >= days.length) {
			return 0;
		}
		int maxValue = 0;

		for (int i = buyDay + 1; i < days.length; i++) {
			maxValue = Math.max(maxValue, days[i] - days[buyDay]);
		}
		maxValue = Math.max(sellStock_rec(days, buyDay + 1), maxValue);

		return maxValue;
	}

	/**
	 * 动态规划
	 */
	public int sellStock_Dp(int[] days) {
		int n = days.length;
		int[] dp = new int[n];
		int maxValue = 0;
		for (int i = 0; i < n; i++) {
			dp[i] = 0;
		}
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (dp[j] >= 0 && days[i] > days[j]) {
					dp[i] = dp[j] + days[i] - days[j];
				}
			}
			maxValue = Math.max(maxValue, dp[i]);
		}
		return maxValue;
	}

	/**
	 * 贪心思想
	 */
	public int sellStock_Ga(int[] days) {
		int minPrice = Integer.MAX_VALUE;
		int res = 0;
		for (int price : days) {
			minPrice = Math.min(minPrice, price);
			res = Math.max(res, price - minPrice);
		}
		return res;
	}

	public static void main(String[] args) {
		int[] days = {7, 1, 5, 3, 6, 4, 9, 8, 4, 3, 9, 3, 10, 8, 4};
		SellStocksSolution sellStocksSolution = new SellStocksSolution();
		int res = sellStocksSolution.sellStock(days);
		int resDp = sellStocksSolution.sellStock_Dp(days);
		int resGa = sellStocksSolution.sellStock_Ga(days);

		System.out.println(res);
		System.out.println(resDp);
		System.out.println(resGa);

	}
}
