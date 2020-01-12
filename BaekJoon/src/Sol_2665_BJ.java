import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Sol_2665_BJ {
	public static int N;
	public static int arr[][];
	public static int visited[][];
	public static int dx[] = {0,0,-1,1};
	public static int dy[] = {-1,1,0,0};
	public static Queue<Info> q;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new int[N][N];
	
		q = new LinkedList<>();
		for(int i= 0; i < N ; i ++)
		{
			String line = br.readLine();
			for(int j = 0; j < N ; j ++)
			{
				int t  = line.charAt(j) - '0';
				visited[i][j] = 100000;
				if(t == 0)
				{
					arr[i][j] = 1;
				}else
				{
					arr[i][j] = 0;
				}
			}
		}
		visited[0][0] = 0;
		q.offer(new Info(0, 0));
		int tx = 0;
		int ty = 0;
		while(!q.isEmpty())
		{
			Info temp = q.poll();
			for(int i = 0; i <4 ; i++)
			{
				tx = temp.j + dy[i];
				ty = temp.i + dx[i];
				if(tx< 0 || ty <0 ||tx>=N || ty>=N) continue;
				
				int count = visited[temp.i][temp.j] + arr[ty][tx];
				if(visited[ty][tx] == 100000)
				{
					visited[ty][tx] = count;
					q.offer(new Info(ty,tx));
				}
				else if(visited[ty][tx] > count)
				{
					visited[ty][tx] = count;
					q.offer(new Info(ty,tx));
				}
						
			}
			
			
		}
		
			System.out.println(visited[N-1][N-1]);
		
	
	}
	public static class Info
	{
		int i;
		int j;
	
		Info(int i, int j)
		{
			this.i = i;
			this.j = j;
			
		}
	}
	
}
