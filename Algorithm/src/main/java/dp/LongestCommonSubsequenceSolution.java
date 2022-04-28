package dp;

/**
 * @author wenhoulai
 */
public class LongestCommonSubsequenceSolution {

	public int longestCommonSubsequence(String text1, String text2) {
		int m = text1.length();
		int n = text2.length();

		if (m == 0 || n == 0) {
			return 0;
		}

		int[][] dp = new int[m + 1][n + 1];

		for (int i = 1; i <= m; i++) {
			char c1 = text1.charAt(i - 1);
			for (int j = 1; j <= n; j++) {
				char c2 = text2.charAt(j - 1);
				if (c1 == c2) {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				}
			}
		}

		return dp[m][n];
	}

	public int longestCommonSubsequence_1(String text1, String text2) {
		int m = text1.length();
		int n = text2.length();

		if (m == 0 || n == 0) {
			return 0;
		}

		int[] dp = new int[m + 1];

		for (int i = 0; i < m; i++) {
			dp[i] = 0;
		}

		for (int i = 1; i <= m; i++) {
			int upLeft = dp[0];
			int c1 = text1.charAt(i - 1);
			for (int j = 1; j <= n; j++) {
				int tmp = dp[j];
				int c2 = text2.charAt(j - 1);
				if (c1 == c2) {
					dp[j] = upLeft + 1;
				} else {
					dp[j] = Math.max(dp[j - 1], dp[j]);
				}
				upLeft = tmp;
			}
		}
		return dp[n];
	}

	public static void main(String[] args) {
		String text1 = "abcde";
		String text2 = "ace";

		LongestCommonSubsequenceSolution solution = new LongestCommonSubsequenceSolution();
		int res = solution.longestCommonSubsequence(text1, text2);
		int res_1 = solution.longestCommonSubsequence_1(text1, text2);

		System.out.println(res);
		System.out.println(res_1);
	}

}
