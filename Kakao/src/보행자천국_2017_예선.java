import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 보행자천국_2017_예선 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m = 3;
		int n = 6;
		int citymap[][] = { { 0, 2, 0, 0, 0, 2 }, { 0, 0, 2, 0, 1, 0 }, { 1, 0, 0, 2, 2, 0 } };
		int result = solution(m, n, citymap);
		System.out.println(result);
	}

	public static int MOD = 20170805;

	public static int solution(int m, int n, int[][] cityMap) {
		int answer = 0;
		long cnt = 0;
		long dp[][][] = new long[m][n][2];
		dp[0][0][0] = 1;
		dp[0][0][1] = 1;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0)
					continue;
				if (cityMap[i][j] == 1) {
					dp[i][j][0] = 0;
					dp[i][j][1] = 0;
					continue;
				}
				if (i == 0) {
					dp[i][j][0] = 0;
					dp[i][j][1] = dp[i][j - 1][1];
				} else if (j == 0) {
					dp[i][j][0] = dp[i - 1][j][0];
					dp[i][j][1] = 0;
				} else {

					int upi = i - 1, upj = j;
					int lefti = i, leftj = j - 1;
					long upcnt = 0, leftcnt = 0;
					if (cityMap[upi][upj] == 2) {
						upcnt += dp[upi][upj][0];
					} else {
						upcnt += dp[upi][upj][0];
						upcnt += dp[upi][upj][1];
					}
					if (cityMap[lefti][leftj] == 2) {
						leftcnt += dp[lefti][leftj][1];
					} else {
						leftcnt += dp[lefti][leftj][0];
						leftcnt += dp[lefti][leftj][1];
					}
					dp[i][j][0] = upcnt % MOD;
					dp[i][j][1] = leftcnt % MOD;

				}
			}
		}
		cnt = dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1];
		answer = (int) (cnt % MOD);
		return answer;
	}

}