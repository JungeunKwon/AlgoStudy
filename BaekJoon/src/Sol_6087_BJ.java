import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_6087_BJ {
	public static int H;
	public static int W;
	public static char[][] arr;
	public static int visited[][];
	public static int min;
	public static Queue<Info>q;
	public static int dx[] = {0,-1,0,1};
	public static int dy[] = {-1,0,1,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		q = new LinkedList<>();
		arr = new char[H][W];
		for(int i = 0; i < H ; i ++)
		{
			String line = br.readLine();
			for(int j = 0; j < W ; j ++)
			{
				arr[i][j] = line.charAt(j);
				if(arr[i][j] == 'C')
					q.offer(new Info(i, j, 0, -1));
			}
		}
		min = Integer.MAX_VALUE;
		visited = new int[H][W];
		visited[0][0] = 1;
		Info temp = q.poll();
		bfs(temp.i, temp.j);
		System.out.println(min-1);
	
	}
	private static void bfs(int N, int M) {
		int tx = 0, ty = 0;
		while(!q.isEmpty())
		{
			Info temp = q.poll();
			int dir = temp.dir;
			
			for(int i = 0; i < 4; i ++)
			{
				tx = temp.j + dx[i];
				ty = temp.i + dy[i];
				int count = temp.count;
				if(tx < 0 || ty <0 || tx>= W || ty >=H)continue;
				if(dir != i) count ++;
				if(tx == M && ty== N)
				{
					if(min > count)min = count;
					continue;
				}
				if(arr[ty][tx] != '.')continue;
				if(visited[ty][tx] != 0 && visited[ty][tx] < count) continue;
				visited[ty][tx] = count;
				q.offer(new Info(ty,tx,count, i));
			}
		}
	}
	public static class Info{
		int i;
		int j;
		int count;
		int dir;
		Info(int i , int j , int count, int dir)
		{
			this.i = i;
			this.j = j;
			this.count = count;
			this.dir = dir;
		}
	}
}
