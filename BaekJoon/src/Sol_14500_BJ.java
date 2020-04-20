import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Sol_14500_BJ {
	public static int N;
	public static int M;
	public static int arr[][];
	public static int max;
	public static int fx[] = { 1, 1, 2, 0, 1, 0, 1, 1, 2, 0, -1, 0 };
	public static int fy[] = { 0, -1, 0, 1, 1, 2, 0, 1, 0, 1, 1, 2 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		max = 0;
		for(int i = 0; i < N; i++)
		{
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M ; j ++)
			{
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean visited[][] = new boolean[N][M];

		for(int i = 0; i < N ; i ++ )
		{
			for(int j = 0; j < M ; j ++)
			{
				dfs(i, j , 0, 0, visited);
				find2(i,j, arr[i][j]);
			}
		}
		System.out.println(max);
	}
	private static void find2(int i, int j, int sum) {

		int tx = 0, ty = 0;
		for (int l = 0; l < 4; l++) {
			int tsum = sum;
			aa : for (int k = l * 3; k < (l * 3) + 3; k++) {
				tx = j + fx[k];
				ty = i + fy[k];
				if (tx < 0 || ty < 0 || tx >= M || ty >= N)
					break aa;
				tsum += arr[ty][tx];
			}
			if (tsum > max)
				max = tsum;
		}

	}
	private static void dfs(int i, int j, int sum, int count, boolean [][] visited) {
		if(i >= N || i < 0 || j >= M || j< 0) return;
		if(count == 4)
		{
			if(max < sum) max =sum;
			return;
		}
		if(visited[i][j])return;
		visited[i][j] = true;

		int tsum =  sum + arr[i][j];
		
		int ti = i , tj = j;
		tj = tj + 1;
		dfs(ti,tj,tsum, count + 1, visited);
		ti = i + 1;
		tj = j;
		dfs(ti, tj, tsum, count +1, visited);
		ti = i -1 ;
		tj = j;
		dfs(ti, tj, tsum, count +1, visited);
		ti = i;
		tj = j - 1;
		dfs(ti, tj, tsum, count +1, visited);
		visited[i][j] = false;
		
	}

}
