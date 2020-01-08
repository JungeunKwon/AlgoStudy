import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Sol_2579_BJ {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N+1];
		int dp[][] = new int[2][N+1];
		for(int i = 1; i <= N; i ++)
		{
			arr[i] = Integer.parseInt(br.readLine());
		}
		dp[1][1] = arr[1];
		dp[0][1] = arr[1];
		dp[0][2] = arr[2];
		dp[1][2] = dp[0][1] + arr[2];
		for(int i = 2; i <= N ; i ++)
		{
			dp[0][i] = Math.max(dp[0][i-2]+arr[i], dp[1][i-2]+arr[i]);
			dp[1][i] = dp[0][i-1] + arr[i];
		}
		
		System.out.println(Math.max(dp[0][N], dp[1][N]));
			
	}

}