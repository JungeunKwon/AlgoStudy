import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_17070_BJ {
	public static int N;
	public static int arr[][];
	public static int count;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		count =0;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i = 0; i <  N; i ++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N ; j ++)
			{
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		//state 0 은 가로 1은 대각선 2 는 세로
		dfs(0,1,0);
		System.out.println(count);
	}
	private static void dfs(int i, int j, int state) {
	
		if(i < 0 || j < 0 || i >= N || j >= N) return;
		
		if(arr[i][j] == 1)return;
		if(state == 1)
			if(arr[i][j-1] == 1 || arr[i-1][j] == 1) return;
		if(i == N-1 && j == N - 1)
		{
			count ++;
			return;
		}
		switch(state) {
		case 0:
			dfs(i, j + 1, 0);
			dfs(i+1, j + 1, 1);
			break;
		case 1:
			dfs(i, j + 1, 0);
			dfs(i+1, j + 1, 1);
			dfs(i+1, j, 2);
			break;
		case 2:
			dfs(i+1, j + 1, 1);
			dfs(i+1, j, 2);
			break;
		}
				
	}
	
}
