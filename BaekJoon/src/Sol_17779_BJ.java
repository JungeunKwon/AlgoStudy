import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_17779_BJ {
	public static int N;
	public static int arr[][];
	public static int result;
	public static int dx[] = { 1, 1, 1, 1 };
	public static int dy[] = { -1, 1, 1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
		result = Integer.MAX_VALUE;
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int x = 1; x <= N; x++) { // x
			for (int y = 1; y <= N; y++) { // y
				for (int d1 = 1; d1 <= N; d1++) { // d1

					for (int d2 = 1; d2 <= N; d2++) { // d2
						if (y - d1 < 1 || y - d1 >= y)
							continue;
						if (x + d1 + d2 > N || x >= x + d1 + d2)
							continue;
						if (y + d2 > N)
							continue;
						int max = 0, min = Integer.MAX_VALUE;
						Find(d1, d2, x, y, max, min);
					}
				}
			}

		}
		System.out.println(result);
		// Find(1, 1, 1, 1);
	}

	private static void Find(int d1, int d2, int x, int y, int max, int min) {

		int newarr[][] = new int[N + 1][N + 1];
		int tx = x, ty = y;
		newarr[tx][ty] = 1;

		for (int i = x; i < x + d1; i++) {
			tx = tx + dx[0];
			ty = ty + dy[0];
			if (tx < 1 || ty < 1 || tx > N || ty > N)
				break;
			newarr[tx][ty] = 1;
		}
		tx = x;
		ty = y;
		for (int i = x; i < x + d2; i++) {
			tx = tx + dx[1];
			ty = ty + dy[1];
			if (tx < 1 || ty < 1 || tx > N || ty > N)
				break;
			newarr[tx][ty] = 1;
		}
		tx = x + d1;
		ty = y - d1;
		newarr[tx][ty] = 1;

		for (int i = x + d1; i < x + d1 + d2; i++) {
			tx = tx + dx[2];
			ty = ty + dy[2];
			if (tx < 1 || ty < 1 || tx > N || ty > N)
				break;
			newarr[tx][ty] = 1;
		}
		tx = x + d2;
		ty = y + d2;
		newarr[tx][ty] = 1;

		for (int i = x + d2; i < x + d1 + d2; i++) {
			tx = tx + dx[3];
			ty = ty + dy[3];
			if (tx < 1 || ty < 1 || tx > N || ty > N)
				break;
			newarr[tx][ty] = 1;
		}

		int t = CalArr2(newarr, x, tx);
		max = Math.max(t, max);
		min = Math.min(t, min);
		t = CalArr(newarr, 1, x + d1, 1, y + 1);
		max = Math.max(t, max);
		min = Math.min(t, min);
		t = CalArr(newarr, 1, x + d2 + 1, y + 1, N + 1);
		max = Math.max(t, max);
		min = Math.min(t, min);
		t = CalArr(newarr, x + d1, N + 1, 1, y - d1 + d2);
		max = Math.max(t, max);
		min = Math.min(t, min);
		t = CalArr(newarr, x + d2 + 1, N + 1, y - d1 + d2, N + 1);
		max = Math.max(t, max);
		min = Math.min(t, min);

		result = Math.min(result, max - min);

	}

	private static int CalArr2(int[][] newarr, int firsty, int lasty) {
		int result = 0;

		qq: for (int i = 1; i <= N; i++) {
			int cnt = 0;
			for (int j = 1; j <= N; j++) {
				if (newarr[i][j] == 1) {
					cnt++;
				}
				if (cnt == 2) {
					result += arr[i][j];
					continue qq;

				}
				if (cnt == 1) {
					result += arr[i][j];
					newarr[i][j] = 1;
					if (i == firsty)
						continue qq;
					if (i == lasty)
						break qq;

				}
			}
		}

		return result;
	}

	private static int CalArr(int[][] newarr, int r, int maxr, int c, int maxc) {
		int result = 0;

		for (int i = r; i < maxr; i++) {
			for (int j = c; j < maxc; j++) {
				if (newarr[i][j] == 1)
					continue;

				result += arr[i][j];
			}
		}

		return result;
	}

}
