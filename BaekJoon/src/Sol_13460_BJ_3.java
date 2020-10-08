import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Sol_13460_BJ_3 {
	public static int N;
	public static int M;
	public static int dx[] = { 0, 0, -1, 1 };
	public static int dy[] = { -1, 1, 0, 0 }; // 상 하 좌 우
	public static int min;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		char[][] arr = new char[N][M];
		Queue<Info> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j);
				if (arr[i][j] == 'R') {
					q.offer(new Info(i, j, 'R'));
				}
				if (arr[i][j] == 'B') {
					q.offer(new Info(i, j, 'B'));

				}
			}
		}
		min = Integer.MAX_VALUE;
		int pos[] = new int[10];
		findPos(pos, q, arr, 0);
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	private static void findPos(int[] pos, Queue<Info> q, char[][] arr, int idx) {
		if (idx >= 10) {
			char[][] newarr = new char[N][M];
			for (int i = 0; i < N; i++) {
				newarr[i] = Arrays.copyOf(arr[i], M);
			}
			Iterator it = q.iterator();
			Info RED = null;
			Info BLUE = null;
			while (it.hasNext()) {
				Info temp = (Info) it.next();
				if (temp.ball == 'R')
					RED = new Info(temp.i, temp.j, temp.ball);
				else
					BLUE = new Info(temp.i, temp.j, temp.ball);
			}
			Play(pos, newarr, RED, BLUE);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (idx > 0) {
				if (opposite(pos[idx - 1]) == i || pos[idx - 1] == i)
					continue;

			}
			pos[idx] = i;
			findPos(pos, q, arr, idx + 1);
		}
	}

	private static void Play(int[] pos, char[][] newarr, Info RED, Info BLUE) {
		Info red = RED;
		Info blue = BLUE;

		for (int i = 0; i < 10; i++) {
			int dir = pos[i];
			int redmove = 0;
			int bluemove = 0;
			// 0은 안움직임 1이면 움직임 2이면 구멍들어감
			while (true) {

				// red move

				redmove = Move(red, dir, newarr);

				// blue move
				bluemove = Move(blue, dir, newarr);
				if (bluemove == 2)
					return;
				if (redmove == 2) {
					bluemove = Move(blue, dir, newarr);

					if (min > (i + 1))
						min = (i + 1);
					return;
				}
				if (redmove == 0 && bluemove == 0)
					break;

			}

		}

	}

	private static int Move(Info BALL, int dir, char[][] newarr) {
		boolean flag = false;
		int origintx = BALL.j;
		int originty = BALL.i;
		int tx = BALL.j;
		int ty = BALL.i;

		while (true) {
			tx = origintx + dx[dir];
			ty = originty + dy[dir];
			if (newarr[ty][tx] == 'O') {
				newarr[originty][origintx] = '.';
				return 2;
			}
			if (newarr[ty][tx] != '.') {
				tx = origintx;
				ty = originty;
				break;
			}
			newarr[originty][origintx] = '.';
			newarr[ty][tx] = BALL.ball;
			origintx = tx;
			originty = ty;
			flag = true;

		}
		BALL.i = ty;
		BALL.j = tx;

		if (flag)
			return 1;
		else
			return 0;
	}

	public static int opposite(int pos) {
		// 상하좌우
		int returnval = -1;
		switch (pos) {
		case 0:
			returnval = 1;
			break;
		case 1:
			returnval = 0;
			break;
		case 2:
			returnval = 3;
			break;
		case 3:
			returnval = 2;
			break;

		}
		return returnval;
	}

	public static class Info {
		int i;
		int j;
		char ball;

		Info(int i, int j, char ball) {
			this.i = i;
			this.j = j;
			this.ball = ball;
		}
	}
}
