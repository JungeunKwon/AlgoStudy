import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_11052_BJ {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N+1];
		int dp[] = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i ++)
		{
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = arr[i];
		}
		for(int i = 1; i <=N ; i ++)
		{
			for(int j = 0; j < i; j ++)
			{
				dp[i] = Math.max(dp[i], dp[i-j] + arr[j]);
			}
		}
		System.out.println(dp[N]);
		
	}

}
