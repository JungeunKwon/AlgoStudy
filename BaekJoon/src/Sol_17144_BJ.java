import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_17144_BJ {
	public static int R;
	public static int C;
	public static int T;
	public static int dx[] = { 0, -1, 0, 1 };
	public static int dy[] = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		int arr[][] = new int[R][C];
		Air up = null;
		Air down = null;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == -1) {
					if (up == null) {
						up = new Air(i, j);
					} else
						down = new Air(i, j);
				}
			}
		}
		for (int a = 0; a < T; a++) {
			int comparr[][] = new int[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (arr[i][j] > 0) {
						int tx = 0, ty = 0, cnt = 0;
						int now = arr[i][j];
						for (int k = 0; k < 4; k++) {
							tx = j + dx[k];
							ty = i + dy[k];
							if (ty < 0 || tx < 0 || tx >= C || ty >= R || arr[ty][tx] == -1)
								continue;
							cnt++;
							comparr[ty][tx] += now / 5;
						}
						comparr[i][j] += now - ((now / 5) * cnt);
					}
				}
			}
			comparr[up.i][up.j] = -1;
			comparr[down.i][down.j] = -1;
			
			windUp(comparr, up.i, up.j);
			windDown(comparr, down.i, down.j);
		

			arr = comparr;

		}
		int answer = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if(arr[i][j] == -1)continue;
				answer += arr[i][j];
			}
		}
		System.out.println(answer);
	}

	public static void windUp(int[][] arr, int starti, int startj) {
		for (int i = starti - 1; i > 0; i--) {
			arr[i][startj] = arr[i - 1][startj];
		}
		for (int j = 0; j < C - 1; j++) {
			arr[0][j] = arr[0][j + 1];
		}
		for (int i = 0; i < starti; i++) {
			arr[i][C - 1] = arr[i + 1][C - 1];
		}
		for (int j = C - 1; j > 1; j--) {
			arr[starti][j] = arr[starti][j - 1];
		}
		arr[starti][startj + 1] = 0;
	}

	public static void windDown(int[][] arr, int starti, int startj) {
		for (int i = starti + 1; i < R - 1; i++) {
			arr[i][startj] = arr[i + 1][startj];
		}
		for (int j = startj; j < C - 1; j++) {
			arr[R - 1][j] = arr[R - 1][j + 1];
		}
		for (int i = R - 1; i > starti; i--) {
			arr[i][C - 1] = arr[i - 1][C - 1];
		}
		for (int j = C - 1; j > startj + 1; j--) {
			arr[starti][j] = arr[starti][j - 1];
		}
		arr[starti][startj + 1] = 0;
	}

	public static class Air {
		int i;
		int j;

		Air(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
