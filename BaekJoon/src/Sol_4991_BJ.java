import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_4991_BJ {
	public static char[][] arr;
	public static boolean [] flag;
	public static int [] result;
	public static List<Info> list;
	public static int min;
	public static int N;
	public static int dp[][];
	public static int M;
	public static int dx[] = {0,0,-1,1};
	public static int dy[] = {-1,1,0,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			if(M == 0 && N == 0)break;
			min = Integer.MAX_VALUE;
			arr = new char[N][M];
	
			list = new ArrayList<>();
			String line = "";
			Info robot = null;
			for(int i = 0; i < N; i ++)
			{
				line = br.readLine();
				for(int j = 0; j < M; j ++)
				{
					arr[i][j] = line.charAt(j);
					if(arr[i][j] == '*')
					{
						list.add(new Info(i, j));
					}
					if(arr[i][j] == 'o')
					{
						robot = new Info(i,j);
					}
							
				}
			}
			
			
			flag = new boolean[list.size()+1];
			dp = new int[list.size()+1][list.size()+1];
			result = new int[list.size()];
			perm(robot, 0, list.size());
			System.out.println(min);
		}
	}
	private static void perm(Info robot, int idx, int length) {
		if(idx >= length)
		{
			int dist = 0;
			int pos = list.size();
			int next = 0;
			Queue<Info> q= new LinkedList<>();
			q.offer(robot);
			for(int i = 0; i < length; i ++)
			{
				next = result[i];
				int t = 0;
				if(dp[pos][next] != 0)
				{
					t = dp[pos][next];
				}else
				{
					t = bfs(q, list.get(result[i]));
					dp[pos][next] = t;
					dp[next][pos] = t;
				}
				
				q.clear();
				if(t == -1) {
					min = -1;
					return;
				}else
					dist+= t;
				if(dist > min) return;
				pos = next;
				q.offer(list.get(result[i]));
			}
			q.clear();
			if(dist< min) min = dist;
			return;
		}
		if(min == -1) return;
		for(int i=0; i<length; i++) {
			if(flag[i]) continue;
			flag[i] = true;
			result[idx] = i;
			perm(robot, idx+1, length);
			flag[i] = false;
		}
	}
	private static int bfs(Queue<Info> q, Info info) {
		int tx = 0, ty = 0;
		int count = 0;
		boolean [][] visited = new boolean[N][M];
		
		while(!q.isEmpty())
		{
			int size = q.size();
			for(int i = 0; i < size; i ++)
			{
				Info temp = q.poll();
				if(temp.i == info.i && temp.j == info.j)
					return count;
				visited[temp.i][temp.j] = true;
				
				for(int j = 0; j < 4; j ++)
				{
					tx = temp.j + dx[j];
					ty = temp.i + dy[j];
					if(tx < 0 || ty < 0 || tx >= M || ty >= N) continue;
					if(visited[ty][tx]) continue;
					if(arr[ty][tx] != 'x')
					{
						visited[ty][tx] = true;
						q.offer(new Info(ty,tx));
					}
				}
			}
			count++;
		}
		return -1;
	}
	public static class Info
	{
		int i;
		int j;
		Info(int i , int j)
		{
			this.i = i;
			this.j = j;
		}
		@Override
		public String toString() {
			return "Info [i=" + i + ", j=" + j + "]";
		}
		
	}
}
