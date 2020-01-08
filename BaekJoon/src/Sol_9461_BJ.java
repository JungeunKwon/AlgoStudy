import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Sol_9461_BJ {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int dp[] = new int[101];
		dp[0] =0;
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;
		for(int i = 4; i <=100; i++)
		{
			dp[i] = dp[i-3] + dp[i-2];
		}
		int N =Integer.parseInt(br.readLine());
		for(int i = 0; i < N ; i ++)
		{
			int s = Integer.parseInt(br.readLine());
			System.out.println(dp[s]);
		}

	}

}
