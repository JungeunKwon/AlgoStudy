import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_14442_BJ {
	public static int min;
	public static int arr[][];
	public static boolean visited[][][];
	public static Queue<Info>q;
	public static int dx[] = {-1,0,1,0};
	public static int dy[] = {0,-1,0,1};
	public static int N;
	public static int M;
	public static int K;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		min = -1;
		StringTokenizer st= new StringTokenizer(br.readLine());
		 N = Integer.parseInt(st.nextToken());
		 M = Integer.parseInt(st.nextToken());
		 K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		visited = new boolean[N][M][K+1];
		q = new LinkedList<>();
		for(int i = 0; i < N ; i ++)
		{
			String line = br.readLine();
			for(int j = 0; j< M; j ++)
			{
				arr[i][j] = line.charAt(j) - '0';
			}
		}
		q.offer(new Info(0, 0, 1,0));
		visited[0][0][0] = true;
		min = -1;
		bfs();
		System.out.println(min);
	
	}
	private static void bfs() {
		int tx = 0, ty = 0;
		while(!q.isEmpty()) {
			Info temp = q.poll();
			if(tx == M-1 && ty == N-1)
			{
				min = temp.count;
				return;
			}
			for(int i = 0; i < 4; i ++)
			{
				tx = temp.j + dx[i];
				ty  = temp.i + dy[i];
				int count =temp.count + 1;
				if(tx < 0 || ty < 0 || tx>=M || ty >=N)continue;
				if(tx == M-1 && ty == N-1)
				{
					min = count;
					return;
				}
				int bcount = temp.brokencount;
				
				if(arr[ty][tx] == 1) { 
					if(bcount >= K) continue;
					bcount ++;
				}
				if(visited[ty][tx][bcount]) continue;
			
				visited[ty][tx][bcount] = true;
				q.offer(new Info(ty,tx,count, bcount));
				
				
			}
		}
		min = -1;
	}
	public static class Info
	{
		int i;
		int j;
		int count;
		int brokencount;
		Info(int i , int j, int count, int brokencount)
		{
			this.i = i;
			this.j = j;
			this.count = count;
			this.brokencount = brokencount;
		}
	}
}
