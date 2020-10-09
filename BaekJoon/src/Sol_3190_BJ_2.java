import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_3190_BJ_2 {
	public static int N;
	public static int arr[][];
	public static int dx[] = { 0, 1, 0, -1 };
	public static int dy[] = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			arr[first][second] = 1;
		}
		Queue<dirInfo> q = new LinkedList<>();
		int L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			q.offer(new dirInfo(time, dir));
		}
		int time = 1;
		arr[1][1] = 2;
		Queue<snakeInfo> snake = new LinkedList<>();
		snake.offer(new snakeInfo(1, 1));
		int dir = 1;
		int tx = 1;
		int ty = 1;

		while (true) {
			tx = tx + dx[dir];
			ty = ty + dy[dir];
			if (tx <= 0 || ty <= 0 || tx > N || ty > N || arr[ty][tx] == 2)
				break;
			if (arr[ty][tx] == 1) {
				arr[ty][tx] = 2;
				snake.offer(new snakeInfo(ty, tx));
			} else {
				arr[ty][tx] = 2;
				snake.offer(new snakeInfo(ty, tx));
				snakeInfo temp = snake.poll();
				arr[temp.i][temp.j] = 0;
			}

			if (!q.isEmpty()) {
				dirInfo temp = q.peek();
				if (temp.time == time) {
					temp = q.poll();
					if (temp.dir.equals("L")) {
						dir = (4 + (dir - 1)) % 4;
					} else {
						dir = (4 + (dir + 1)) % 4;
					}
				}
			}
			time++;

		}

		System.out.println(time);
	}

	public static class dirInfo {
		int time;
		String dir;

		public dirInfo(int time, String dir) {
			this.time = time;
			this.dir = dir;
		}
	}

	public static class snakeInfo {
		int i;
		int j;

		public snakeInfo(int i, int j) {
			this.i = i;
			this.j = j;

		}
	}

}
