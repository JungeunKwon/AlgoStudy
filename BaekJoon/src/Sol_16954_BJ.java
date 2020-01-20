import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Sol_16954_BJ {
	public static char arr[][];
	public static int N;
	public static int ans;
	public static int dx[] = { 1, 0, -1, 0, -1, 1, 1, -1,0 };
	public static int dy[] = { 0, -1, 0, 1, 1, -1, 1, -1 ,0};
	public static Queue<Info> q;
	public static boolean visited[][][];
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = 8;
		visited = new boolean[N][N][N*N+1];		
		arr = new char[N][N];
		
		ans = 0;
		q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		q.offer(new Info(7, 0));
		bfs();
		System.out.println(ans);
	}

	private static void bfs() {
		int tx = 0, ty = 0, count = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int j = 0; j < size; j++) {
				Info temp = q.poll();
				if (arr[temp.i][temp.j] == '#')
					continue;
				if (temp.i == 0 && temp.j == 7) {
					ans = 1;
					return;
				}
				for (int i = 0; i <  9; i++) {
					tx = temp.j + dx[i];
					ty = temp.i + dy[i];
					if (tx < 0 || ty < 0 || tx >= N || ty >= N)
						continue;
					if (arr[ty][tx] == '#')
						continue;
					if(visited[ty][tx][count])continue;
					visited[ty][tx][count]= true;
					q.offer(new Info(ty, tx));
				}
			}
			count++;
			movemap();
		}

	}

	private static void movemap() {
		//char temp = '.';
		for(int j = 0; j < N ; j ++)
		{
			for(int i = N - 1; i > 0 ; i --)
			{
				//char temp = arr[i][j];
				arr[i][j] = arr[i-1][j];
			}arr[0][j] = '.';
		}
		
	}

	public static class Info {
		int i;
		int j;

		Info(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
