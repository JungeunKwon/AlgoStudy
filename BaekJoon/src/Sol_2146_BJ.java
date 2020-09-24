import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_2146_BJ {

	public static int dx[] = { 0, 0, -1, 1 };
	public static int dy[] = { -1, 1, 0, 0 };
	public static int N;
	public static int arr[][];
	public static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		boolean visited[][] = new boolean[N][N];
		min = Integer.MAX_VALUE;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int sumnum = 1;
		Queue<Info> q = new LinkedList();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && arr[i][j] != 0) {
					visited[i][j] = true;
					q.clear();
					q.offer(new Info(i, j, 0));
					bfs(q, visited, arr[i][j], sumnum);
					arr[i][j] = sumnum;
					sumnum++;
				}
			}
		}

		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] != 0) {
					visited = new boolean[N][N];
					q.clear();
					q.offer(new Info(i, j, 0));
					bfs2(q, visited, arr[i][j]);
				}
			}
		}
		System.out.println(min);
	}

	private static void bfs2(Queue<Info> q, boolean[][] visited, int now) {
		int tx = 0, ty = 0;
		while (!q.isEmpty()) {
			Info temp = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = temp.j + dx[i];
				ty = temp.i + dy[i];
				if (tx < 0 || ty < 0 || tx >= N || ty >= N || visited[ty][tx])
					continue;
				visited[ty][tx] = true;
				if (arr[ty][tx] == now) {
					continue;
				} else if (arr[ty][tx] == 0) {
					q.offer(new Info(ty, tx, temp.cnt + 1));
				} else {
					if (min > temp.cnt)
						min = temp.cnt;
				}

			}
		}
	}

	private static void bfs(Queue<Info> q, boolean[][] visited, int now, int sumnum) {
		int tx = 0, ty = 0;
		while (!q.isEmpty()) {
			Info temp = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = temp.j + dx[i];
				ty = temp.i + dy[i];
				if (tx < 0 || ty < 0 || tx >= N || ty >= N || visited[ty][tx])
					continue;
				if (arr[ty][tx] != now)
					continue;
				visited[ty][tx] = true;
				arr[ty][tx] = sumnum;
				q.offer(new Info(ty, tx, 0));
			}
		}
	}

	public static class Info {
		int i;
		int j;
		int cnt;

		Info(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}
}