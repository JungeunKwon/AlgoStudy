import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_15684_BJ_2 {
	public static int N;
	public static int M;
	public static boolean arr[][];
	public static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		int input = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		int answer = -1;
		arr = new boolean[N + 1][M + 1];
		for (int i = 0; i < input; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a][b] = true;
		}
		flag = false;
		for (int i = 0; i <= 3; i++) {
			dfs(1, 1, i, 0);
			if (flag) {
				answer = i;
				break;
			}
		}

		System.out.println(answer);

	}

	private static void dfs(int ti, int tj, int cnt, int idx) {
		if (idx >= cnt) {
			if (check(arr)) {
				flag = true;
			}
			return;
		}
		for (int i = ti; i <= N; i++) {
			for (int j = tj; j < M; j++) {
				if (arr[i][j] || arr[i][j - 1] || arr[i][j + 1])
					continue;
				arr[i][j] = true;
				dfs(i, j + 1, cnt, idx + 1);
				arr[i][j] = false;
			}
			tj = 1;
		}
	}

	private static boolean check(boolean[][] arr2) {
		for (int i = 1; i <= M; i++) {
			int start = i;
			int pos = 1;
			while (pos <= N) {
				if (arr2[pos][start]) {
					start = start + 1;
				} else if (arr2[pos][start - 1]) {
					start = start - 1;
				}
				pos++;
			}
			if (start != i)
				return false;
		}
		return true;
	}

}
