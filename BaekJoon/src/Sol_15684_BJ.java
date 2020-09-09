import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_15684_BJ {
	public static int N;
	public static int M;
	public static int H;
	public static boolean arr[][];
	public static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new boolean[H + 2][N + 2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int h = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			arr[h][n] = true;
		}
		int answer = -1;
		flag = false;
		for (int i = 0; i <= 3; i++) {
			dfs(i, 0);
			if (flag) {
				answer = i;
				break;
			}
		}
		System.out.println(answer);
	}

	private static void dfs(int max, int idx) {
		if (flag)
			return;
		if (idx >= max) {

			if (check(arr))
				flag = true;
			return;
		}

		for (int i = 1; i <= H; i++) {
			for (int j = 1; j <= N; j++) {
				if (arr[i][j - 1] || arr[i][j] || arr[i][j + 1])
					continue;
				arr[i][j] = true;
				dfs(max, idx + 1);
				arr[i][j] = false;
			}
		}
	}

	public static boolean check(boolean[][] arr) {
		for (int a = 1; a <= N; a++) {
			int i = 1;
			int j = a;
			while (i <= H) {
				if (arr[i][j] == true) {
					i = i + 1;
					j = j + 1;
				} else if (arr[i][j - 1] == true) {
					i = i + 1;
					j = j - 1;
				} else {
					i = i + 1;
				}
			}
			if (j != a)
				return false;
		}

		return true;
	}
}
