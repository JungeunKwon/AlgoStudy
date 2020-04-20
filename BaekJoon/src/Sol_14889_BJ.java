import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Sol_14889_BJ {
	public static int N;
	public static int arr[][];
	public static int min;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		StringTokenizer st;
		min = Integer.MAX_VALUE;
		for(int i = 0 ; i < N ; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N ; j++)
			{
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean flag[] = new boolean[N];
		dfs(0,0,flag);
		System.out.println(min);
	}
	private static void dfs(int depth, int idx, boolean[] flag) {
		if(depth == N/2)
		{
			int fiteam = 0, seteam = 0;
			for(int i = 0; i < N ; i ++)
			{
				for(int j = 0; j < N ; j++)
				{
					if(i == j)continue;
					if(flag[i] != flag[j])continue;
					if(flag[i])
					{
						fiteam += arr[i][j];
					}else
					{
						seteam += arr[i][j];
					}
				}
			}
			int result = Math.abs(fiteam - seteam);
			if(min > result) min = result;
			return;
		}
		for(int i = idx; i < N ; i ++)
		{
			flag[i] = true;
			dfs(depth+1,i+1, flag);
			flag[i] =false;
 		}
	}

}
