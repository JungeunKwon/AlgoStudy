import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_2234_BJ {
	public static int possibledir[][] = {{0,0,0,0},{-1,1,1,1},{1,-1,1,1},{-1,-1,1,1},{1,1,-1,1},{-1,1,-1,1},
	{1,-1,-1,1},{-1,-1,-1,1},{1,1,1,-1},{-1,1,1,-1},{1,-1,1,-1},{-1,-1,1,-1},
	{1,1,-1,-1},{-1,1,-1,-1},{1,-1,-1,-1},{-1,-1,-1,-1}};
	public static int N;
	public static int M;
	public static int arr[][];
	public static int room[][];
	public static int dx [] = {-1,0,1,0};
	public static int dy [] = {0,-1,0,1};
	public static boolean visited[][];
	public static int cnt;
	public static int max;
	public static Queue<Info>q;
	public static int roomsize[];
	public static int mmmax;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());	
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		room = new int[N][M];
		roomsize = new int[N*M+1];
		visited = new boolean[N][M];
		cnt = 0;
		max = 0;
		mmmax = 0;
		q = new LinkedList<>();
		for(int i= 0; i < N ; i ++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M ; j ++)
			{
				arr[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0 ;i < N ; i ++)
		{
			for(int j = 0; j < M; j ++)
			{
				if(!visited[i][j])
				{
					
					q.offer(new Info(i, j));
					visited[i][j] = true;
					room[i][j] = cnt;
					int size = bfs(cnt);
					roomsize[cnt] = size;
					cnt ++;
				}
			}
		}
		mmmax = max;
		visited = new boolean[N][M];
		for(int i = 0 ;i < N ; i ++)
		{
			for(int j = 0; j < M; j ++)
			{
				if(!visited[i][j])
				{
					
					q.offer(new Info(i, j));
					visited[i][j] = true;
					bfs2();
				}
			}
		}
		
		System.out.println(cnt);
		System.out.println(max);
		System.out.println(mmmax);
	}
	private static void bfs2() {
		int tx = 0,  ty = 0;
		while(!q.isEmpty())
		{
			int size = q.size();
			for(int i = 0; i < size; i ++)
			{
				Info temp = q.poll();
				visited[temp.i][temp.j]= true; 
				int currroomnum = room[temp.i][temp.j];
				for(int j = 0; j < 4; j ++)
				{
					tx = temp.j + dx[j];
					ty = temp.i + dy[j];
					if(tx < 0 || ty < 0|| tx>= M || ty >=N) continue;
					if(visited[ty][tx]) continue;
					
					int nextroomnum = room[ty][tx];
					if(currroomnum != nextroomnum)
					{
						int roomi = roomsize[currroomnum] + roomsize[nextroomnum];
						if(roomi > mmmax) mmmax = roomi;
						continue;
					}
					visited[ty][tx] = true;
					q.offer(new Info(ty,tx));
					
				}
			}
		
			
		}
		
	}
	private static int bfs(int num) {
		int tx = 0,  ty = 0;
		int count = 1;
		while(!q.isEmpty())
		{
			int size = q.size();
			for(int i = 0; i < size; i ++)
			{
				Info temp = q.poll();
				visited[temp.i][temp.j]= true; 
				int arrresult = arr[temp.i][temp.j];
				for(int j = 0; j < 4; j ++)
				{
					if(possibledir[arrresult][j] == -1)continue;
					tx = temp.j + dx[j];
					ty = temp.i + dy[j];
					if(tx < 0 || ty < 0|| tx>= M || ty >=N) continue;
					if(visited[ty][tx]) continue;
					visited[ty][tx] = true;
					room[ty][tx] = num;
					q.offer(new Info(ty,tx));
					count ++;
					
				}
			}
		
			
		}
		if(max < count)max= count;
		return count;
		
	}
	public static class Info{
		int i;
		int j;
		
		Info(int i, int j)
		{
			this.i = i;
			this.j = j;
			
		}
	}
}
