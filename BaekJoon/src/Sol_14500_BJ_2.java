import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_14500_BJ_2 {
	public static int N;
	public static int M;
	public static int arr[][];
	public static int max;
	public static int dx[][] = { { 0, 1, 2, 1 }, { 0, 1, 2, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, -1 } };
	public static int dy[][] = { { 0, 0, 0, 1 }, { 0, 0, 0, -1 }, { 0, 1, 2, 1 }, { 0, 1, 2, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean visited[][] = new boolean[N][M];
		// x,y,cnt,score
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dfs(i, j, 0, 0, visited);
				check(i, j);
			}
		}
		System.out.println(max);
	}

	public static void check(int ti, int tj) {
		int dN = dx.length;
		int dM = dx[0].length;
		for (int i = 0; i < dN; i++) {
			int result = 0;
			int tx = tj;
			int ty = ti;
			aa: for (int j = 0; j < dM; j++) {
				tx = tj + dx[i][j];
				ty = ti + dy[i][j];
				if (tx < 0 || ty < 0 || tx >= M || ty >= N)
					break aa;
				result = result + arr[ty][tx];
			}
			if (result > max)
				max = result;
		}
	}

	private static void dfs(int i, int j, int cnt, int score, boolean[][] visited) {
		if (i < 0 || j < 0 || i >= N || j >= M)
			return;
		if (cnt >= 4) {
			if (score > max) {
				max = score;
			}
			return;
		}
		if(visited[i][j])return;
		visited[i][j] = true;
		int tempscore = score + arr[i][j];

		int tx = i;
		int ty = j;
		ty = ty + 1;
		dfs(tx, ty, cnt + 1, tempscore, visited);
		tx = i + 1;
		ty = j;
		dfs(tx, ty, cnt + 1, tempscore, visited);
		tx = i - 1;
		ty = j;
		dfs(tx, ty, cnt + 1, tempscore, visited);
		tx = i;
		ty = j - 1;
		dfs(tx, ty, cnt + 1, tempscore, visited);
		visited[i][j] = false;
	}

}
