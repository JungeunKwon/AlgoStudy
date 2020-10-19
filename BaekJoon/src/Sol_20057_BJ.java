import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_20057_BJ {
	public static int dx[] = { -1, 0, 1, 0 };
	public static int dy[] = { 0, 1, 0, -1 };
	public static int percentage[] = { 1, 1, 2, 7, 7, 2, 10, 10, 5 };
	public static int movedx[][] = { { 1, 1, 0, 0, 0, 0, -1, -1, -2, -1 }, { -1, 1, -2, -1, 1, 2, -1, 1, 0, 0 },
			{ -1, -1, 0, 0, 0, 0, 1, 1, 2, 1 }, { -1, 1, -2, -1, 1, 2, -1, 1, 0, 0 } };
	public static int movedy[][] = { { -1, 1, -2, -1, 1, 2, -1, 1, 0, 0 }, { -1, -1, 0, 0, 0, 0, 1, 1, 2, 1 },
			{ 1, -1, 2, 1, -1, -2, 1, -1, 0, 0 }, { 1, 1, 0, 0, 0, 0, -1, -1, -2, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result = 0;
		int dir = 0, jump = 0, distance = 1;
		int starti = N / 2, startj = N / 2;
		while (true) {
			if (starti == 0 && startj == 0)
				break;
			if (dir >= 4) {
				dir = 0;
			}
			if (jump >= 2) {
				jump = 0;
				distance++;
			}
			int tx = startj;
			int ty = starti;
			for (int i = 0; i < distance; i++) {
				tx = tx + dx[dir];
				ty = ty + dy[dir];
				int firstsand = arr[ty][tx];
				int diff = 0;
				for (int j = 0; j < 10; j++) {
					int ttx = tx + movedx[dir][j];
					int tty = ty + movedy[dir][j];
					int temp = 0;
					if (ttx < 0 || tty < 0 || ttx >= N || tty >= N) {

						if (j == 9) {
							temp = firstsand - diff;

						} else {
							temp = (percentage[j] * firstsand) / 100;
							diff += temp;
						}
						result += temp;
						continue;
					}
					if (j == 9) {
						temp = firstsand - diff;
						arr[tty][ttx] += temp;
					} else {
						temp = (percentage[j] * firstsand) / 100;
						arr[tty][ttx] += temp;
						diff += temp;
					}

				}
				arr[ty][tx] = 0;
				starti = ty;
				startj = tx;

				if (tx == 0 && ty == 0)
					break;
			}
			jump++;
			dir++;
		}

		System.out.println(result);
	}

}
