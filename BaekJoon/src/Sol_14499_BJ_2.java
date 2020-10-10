import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_14499_BJ_2 {
	public static int N;
	public static int M;
	public static int arr[][];
	public static int K;
	public static int dx[] = { 0, 1, -1, 0, 0 };
	public static int dy[] = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int ti = Integer.parseInt(st.nextToken());
		int tj = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		int dice[][] = new int[4][3];
		int tx = tj;
		int ty = ti;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < K; i++) {
			int dir = Integer.parseInt(st.nextToken());
			tx = tx + dx[dir];
			ty = ty + dy[dir];
			if (tx < 0 || ty < 0 || ty >= N || tx >= M) {
				tx = tx - dx[dir];
				ty = ty - dy[dir];
				continue;
			}
			switch (dir) {
			case 1:
				moveToRight(dice);
				break;
			case 2:
				moveToLeft(dice);
				break;
			case 3:
				moveToUp(dice);
				break;
			case 4:
				moveToDown(dice);
				break;
			}
			if(arr[ty][tx] == 0) {
				arr[ty][tx] = dice[3][1];
			}else {
				dice[3][1] = arr[ty][tx];
				arr[ty][tx] = 0;
			}
			sb.append(dice[1][1] + "\n");
		}
		System.out.println(sb.toString());
	}	

	public static void moveToRight(int[][] dice) {
		int temp = dice[3][1];
		dice[3][1] = dice[1][2];
		dice[1][2] = dice[1][1];
		dice[1][1] = dice[1][0];
		dice[1][0] = temp;
	}

	public static void moveToLeft(int[][] dice) {
		int temp = dice[3][1];
		dice[3][1] = dice[1][0];
		dice[1][0] = dice[1][1];
		dice[1][1] = dice[1][2];
		dice[1][2] = temp;
	}

	public static void moveToUp(int[][] dice) {
		int temp = dice[0][1];
		dice[0][1] = dice[1][1];
		dice[1][1] = dice[2][1];
		dice[2][1] = dice[3][1];
		dice[3][1] = temp;
	}

	public static void moveToDown(int[][] dice) {
		int temp = dice[0][1];
		dice[0][1] = dice[3][1];
		dice[3][1] = dice[2][1];
		dice[2][1] = dice[1][1];
		dice[1][1] = temp;
	}
}
