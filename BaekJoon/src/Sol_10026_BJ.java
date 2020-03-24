import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Sol_10026_BJ {
	public static int N;
	public static boolean visited[][];
	public static char arr[][];
	public static int yak;
	public static int noyak;
	public static Queue<Info> q;
	public static int dx[] = {1,-1,0,0};
	public static int dy[] = {0,0,-1,1};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visited = new boolean[N][N];
		arr = new char[N][N];
		yak =0;
		noyak = 0;
		String line = "";
		q = new LinkedList<>();
		for(int i =0; i < N; i ++)
		{
			line = br.readLine();			
			for(int j = 0; j < N ; j ++)
			{
				arr[i][j] = line.charAt(j);
			}
		}
		for(int i = 0; i < N ; i ++)
		{
			for(int j = 0; j < N; j ++)
			{
				if(!visited[i][j])
				{
					visited[i][j] = true;
					q.offer(new Info(i, j));
					bfs(false, arr[i][j]);
					noyak ++;
				}
			}
		}
		visited = new boolean[N][N];
		q.clear();
		for(int i = 0; i < N ; i ++)
		{
			for(int j = 0; j < N; j ++)
			{
				if(!visited[i][j])
				{
					visited[i][j] = true;
					q.offer(new Info(i, j));
					bfs(true, arr[i][j]);
					yak ++;
				}
			}
		}
		System.out.println(noyak + " " + yak);
	}
	private static void bfs(boolean isyak, char c) {
		int tx = 0 , ty = 0;
		while(!q.isEmpty())
		{
			Info temp = q.poll();
			for(int i = 0; i < 4 ; i ++)
			{
				tx = temp.j + dx[i];
				ty = temp.i + dy[i];
				if(tx < 0 || ty < 0 || tx>=N || ty>=N) continue;
				if(visited[ty][tx]) continue;
				if(isyak)
				{
					if(c == 'R' ||  c == 'G')
					{
						if(arr[ty][tx] == 'R' || arr[ty][tx] == 'G')
						{
							q.offer(new Info(ty, tx));
							visited[ty][tx] = true;
						}
						
					}else if(arr[ty][tx] == c)
					{
						q.offer(new Info(ty, tx));
						visited[ty][tx] = true;	
					}
				}else
				{
					if(arr[ty][tx] == c)
					{
						q.offer(new Info(ty, tx));
						visited[ty][tx] = true;
					}
				}
				
			}
		}
		
	}
	public static class Info
	{
		int i;
		int j;
		Info(int i, int j)
		{
			this.i = i ;
			this.j = j ;
		}
	}
}
