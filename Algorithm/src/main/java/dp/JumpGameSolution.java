package dp;

/**
 * @author wenhoulai
 * <p>
 * 45. 跳跃游戏 II
 */
public class JumpGameSolution {
	public int jumpGame(int[] jumps) {
		return jumpGames(jumps, 0);
	}

	public int jumpGames(int[] jumps, int start) {
		// 递归终止条件
		if (start >= jumps.length - 1) {
			return 0;
		}

		int minSteps = Integer.MAX_VALUE;
		int cur = 0;
		for (int i = 1; i <= jumps[start]; i++) {
			cur = start + i;
			int steps = jumpGames(jumps, cur) + 1;
			if (steps == -1) {
				continue;
			}
			minSteps = Math.min(minSteps, steps);
		}

		if (minSteps != Integer.MAX_VALUE) {
			return minSteps;
		}

		return -1;
	}

	public int jumpGamesDp(int[] jumps) {
		// 记录每一个位置到达终点的最小步数
		int[] dp = new int[jumps.length];

		for (int i = 0; i < jumps.length; i++) {
			dp[i] = Integer.MAX_VALUE;
		}

		dp[0] = 0;
		for (int i = 0; i < jumps.length; i++) {
			for (int j = 1; j <= jumps[i]; j++) {
				int maxPos = i + j;
				if (maxPos >= jumps.length) {
					break;
				}
				dp[maxPos] = Math.min(dp[maxPos], dp[i] + 1);
			}
		}

		if (dp[jumps.length - 1] == Integer.MAX_VALUE) {
			return -1;
		}
		return dp[jumps.length - 1];
	}

	public int jumpGamesGa(int[] nums) {
		int end = 0;
		int maxPosition = 0;
		int steps = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			//找能跳的最远的
			maxPosition = Math.max(maxPosition, nums[i] + i);
			if (i == end) { //遇到边界，就更新边界，并且步数加一
				end = maxPosition;
				steps++;
			}
		}
		return steps;
	}

	public static void main(String[] args) {
		int[] jumps = {2, 3, 1, 1, 4, 3, 5, 6, 1, 5, 3, 2};
		JumpGameSolution solution = new JumpGameSolution();
		int res = solution.jumpGame(jumps);
		int resDp = solution.jumpGamesDp(jumps);
		int resGa = solution.jumpGamesGa(jumps);

		System.out.println(res);
		System.out.println(resDp);
		System.out.println(resGa);

	}
}
