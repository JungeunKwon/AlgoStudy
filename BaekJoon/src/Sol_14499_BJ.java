import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_14499_BJ {
	public static int N;
	public static int M;
	public static int dx[] = { 1, -1, 0, 0 };
	public static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int dice[][] = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int firsti = Integer.parseInt(st.nextToken());
		int firstj = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int arr[][] = new int[N][M];
		int op[] = new int[k];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int dir = 3;
		int tx = firstj;
		int ty = firsti;
		for (int i = 0; i < k; i++) {
			op[i] = Integer.parseInt(st.nextToken()) - 1;
			tx = tx + dx[op[i]];
			ty = ty + dy[op[i]];
			if (tx < 0 || ty < 0 || tx >= M || ty >= N) {
				tx = tx - dx[op[i]];
				ty = ty - dy[op[i]];
				continue;
			}
			switch (op[i]) {
			case 0:
				turnRight(dice);
				break;
			case 1:
				turnLeft(dice);
				break;
			case 2:
				turnUp(dice);
				break;
			case 3:
				turnDown(dice);
				break;
			}
			if (arr[ty][tx] == 0) {
				arr[ty][tx] = dice[3][1];
			} else {
				dice[3][1] = arr[ty][tx];
				arr[ty][tx] = 0;
			}
			System.out.println(dice[1][1]);
		}

	}

	public static void turnUp(int dice[][]) {
		int temp = dice[0][1];
		dice[0][1] = dice[1][1];
		dice[1][1] = dice[2][1];
		dice[2][1] = dice[3][1];
		dice[3][1] = temp;
	}

	public static void turnDown(int dice[][]) {
		int temp = dice[3][1];
		dice[3][1] = dice[2][1];
		dice[2][1] = dice[1][1];
		dice[1][1] = dice[0][1];
		dice[0][1] = temp;
	}

	public static void turnRight(int dice[][]) {
		int temp = dice[3][1];
		dice[3][1] = dice[1][2];
		dice[1][2] = dice[1][1];
		dice[1][1] = dice[1][0];
		dice[1][0] = temp;
	}

	public static void turnLeft(int dice[][]) {
		int temp = dice[1][1];
		dice[1][1] = dice[1][2];
		dice[1][2] = dice[3][1];
		dice[3][1] = dice[1][0];
		dice[1][0] = temp;
	}
}
