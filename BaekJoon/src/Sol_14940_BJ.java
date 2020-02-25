import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_14940_BJ {
	public static int N;
	public static int M;
	public static Queue<Info>q;
	public static boolean visited[][];
	public static int arr[][];
	public static int newarr[][];
	public static int dx [] = {0,0,-1,1};
	public static int dy [] = {-1,1,0,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		 N = Integer.parseInt(st.nextToken());
		 M = Integer.parseInt(st.nextToken());
		 q = new LinkedList<>();
		 newarr = new int[N][M];
		 visited = new boolean[N][M];
		 arr = new int[N][M];
		 for(int i = 0; i  < N ; i ++)
		 {
			 st = new StringTokenizer(br.readLine());
			 for(int j = 0; j < M ; j ++)
			 {
				arr[i][j] =  Integer.parseInt(st.nextToken());
				if(arr[i][j] == 2)
				{
					visited[i][j] = true;
					q.offer(new Info(i, j));
				}
			 }
		 }
		 bfs();
		 for(int i = 0; i  < N ; i ++)
		 {
			
			 for(int j = 0; j < M ; j ++)
			 {
				if(visited[i][j])
				{
					System.out.print(newarr[i][j] + " ");
				}else if(!visited[i][j] && arr[i][j] == 0)
				{
					System.out.print(0 + " ");
				}else
				{
					System.out.print(-1 + " ");
				}
				
			 }
			 System.out.println();
		 }
	}
	
	private static void bfs() {
		int tx = 0, ty = 0;
		int cnt =1;
		while(!q.isEmpty())
		{
			int size = q.size(); 
			for(int i = 0; i < size ; i ++)
			{
				Info temp = q.poll();
				for(int j = 0; j <4; j ++ )
				{
					tx = temp.j + dx[j];
					ty = temp.i + dy[j];
					if(tx < 0 || ty < 0 || tx>=M || ty >=N )continue;
					if(arr[ty][tx] ==0) continue;
					if(visited[ty][tx])continue;
					newarr[ty][tx] =cnt;
					visited[ty][tx] = true;
					q.offer(new Info(ty, tx));				
					
				}
			}
			cnt ++;
		}
	}

	public static class Info{
		int i;
		int j;
		Info(int i , int j)
		{
			this.i = i;
			this.j = j;
		}
	}
}
