import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_18243_BJ {

	public static int arr[][];
	public static int dx [] = {0,0,-1,1};
	public static int dy [] = {-1,1,0,0};
	public static Queue<Info> q;
	public static boolean visited[][];
	public static int max;
	public static int N;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		for(int i=1; i<=N; i++) {
			Arrays.fill(arr[i], 100);
		}
		visited = new boolean[N+1][N+1];
		q = new LinkedList<>();
		for(int i = 0; i < M; i ++)
		{
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		/*max = 0;
		int count = 0;
		boolean flag = false;
		aa : for(int i = 1; i <= N ; i ++ )
		{
			visited = new boolean[N+1][N+1];
			for(int j = 1; j <= N; j ++)
			{
				if(arr[i][j] == 1 && !visited[i][j])
				{
					visited[i][j] = true;
					
					q.offer(new Info(j, i));
					
				}
			
			}
			if(!q.isEmpty())
			{
				find();
				for(int k = 1; k <=N; k++)
				{
					for(int l = 1; l <= N; l++)
					{
						if(arr[k][l] == 1 && !visited[k][l])
						{
							flag = true;
							break aa;
						}
					}
				}
			}
			
		}*/
		for(int k=1; k<=N; k++) {
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) {
					if(arr[i][j] > arr[i][k] + arr[k][j]) {
						arr[i][j] = arr[i][k] +arr[k][j];
						System.out.print(arr[i][j] + " ");
					}
				}
			}System.out.println();
		}
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				if(arr[i][j]>=7) {
					System.out.println("Big World!");
					System.exit(0);
				}
			}
		}
		System.out.println("Small World!");
		/*//System.out.println( max + " " + count);
		if(max > 6 || flag)
		{
			System.out.println("Big World!");
		}else
		{	
			System.out.println("Small World!");
						
		}*/
	}
	private static void find() {
		int count = 1;
		while(!q.isEmpty())
		{
			int size = q.size();
			for(int i = 0; i <size; i ++)
			{
				Info temp = q.poll();
				//System.out.println(temp);
				visited[temp.i][temp.j] = true;
				for(int j = 1; j <= N; j ++)
				{
					if(visited[temp.i][j]) continue;
					if(arr[temp.i][j] == 1 && !visited[j][temp.i])
					{
					
						visited[temp.i][j] = true;
						
				
						q.offer(new Info(j, temp.i));
						
					}
				}
				/*for(int j = 0; j <4 ; j ++)
				{
					tx = temp.j + dx[j];
					ty = temp.i + dy[j];
					if(tx <1 || ty < 1 || tx>N ||ty>N)continue;
					if(visited[ty][tx]) continue;
					if(arr[ty][tx] == 1)
					{
						q.offer(new Info(ty, tx));
						visited[ty][tx] = true;
						visited[tx][ty] = true;
					}
				}*/
				
			}
			count ++;
		}
		//System.out.println(count);
		if(max < count) max =count;
	}
	public static class Info{
		int i;
		int j;
		
		@Override
		public String toString() {
			return "Info [i=" + i + ", j=" + j + "]";
		}

		Info(int i, int j)
		{
			this.i = i;
			this.j = j;
		}
	}
}
