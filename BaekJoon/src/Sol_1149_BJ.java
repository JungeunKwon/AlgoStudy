import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_1149_BJ {
//실행하세요
	public static void main(String[] args) throws Exception {
		BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int dp[][] = new int[N][3];
		int arr[][] = new int[N][3];
		StringTokenizer st;
		for(int i = 0; i<N; i ++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++)
			{
				arr[i][j] = Integer.parseInt(st.nextToken());			
			}
		}
		for(int i = 0; i < N; i ++)
		{
		
			for(int j = 0; j < 3 ;  j++)
			{
				if(i == 0)
				{
					dp[i][j] = arr[i][j];
				}else
				{
					int min = Integer.MAX_VALUE;
					for(int k = 0; k < 3; k ++)
					{
						if(j == k ) continue;
						int t = arr[i][j] + dp[i-1][k];
						if(t < min) min =t;
					}
					dp[i][j] = min;
 				}
			}
			
		}
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i ++)
		{
			if(min > dp[N-1][i]) min = dp[N-1][i];
		}
		
		System.out.println(min);
	}

}
