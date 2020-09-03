
import java.util.Arrays;

public class GPS_2017_본선 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 7;
		int m = 10;
		int edge_list[][] = { { 1, 2 }, { 1, 3 }, { 2, 3 }, { 2, 4 }, { 3, 4 }, { 3, 5 }, { 4, 6 }, { 5, 6 }, { 5, 7 },
				{ 6, 7 } };
		int k = 6;
		int gps_log[] = { 1, 2, 3, 3, 6, 7 };
		int result = solution(n, m, edge_list, k, gps_log);
		System.out.println(result);
	}

	public static int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
		int answer = 0;
		int arr[][] = new int[n+1][n+1];
		int dp[][] = new int[n+1][n+1];
		int INF = 100000;
		for (int i = 0; i < m; i++) {
			int fi = edge_list[i][0];
			int se = edge_list[i][1];
			arr[fi][se] = 1;
			arr[se][fi] = 1;
		}
		for (int i = 0; i < n+1; i++) {
			Arrays.fill(dp[i], INF);
		}
		dp[0][gps_log[0]] = 0;
		int last = gps_log[k-1];
		for (int i = 1; i < k ; i++) {
			for (int j = 1; j <= n; j++) {
			
				int val = gps_log[i] == j ? 0 : 1;
				dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + val);
				for (int q = 0; q <= n; q++) {
					if(arr[j][q] == 0)continue;
					dp[i][j] = Math.min(dp[i - 1][q] + val, dp[i][j]);
				}
			}

		}
		for(int i = 0; i <= n; i ++) {
			System.out.println(Arrays.toString(dp[i]));
		}
		answer = dp[k-1][last];
		return answer;
	}
}