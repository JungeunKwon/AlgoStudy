import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_13460_BJ_2 {
	public static int N;
	public static int M;
	public static int di[] = { -1, 0, 1, 0 };
	public static int dj[] = { 0, -1, 0, 1 }; // 0 위 1 왼 2 아래 3 오른
	public static char arr[][];
	public static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		String line = "";
		Info R = null;
		Info B = null;
		min = -1;
		for (int i = 0; i < N; i++) {
			line = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j);
			}
		}
		int[] move = new int[10];
		findmove(move, 0);
		System.out.println(min);
	}

	private static void findmove(int[] move, int idx) {
		if (idx >= 10) {
			go(move);
			return;
		}
		for (int i = 0; i < 4; i++) {
			if (idx > 0) {
				if (move[idx - 1] == i || move[idx - 1] == opposite(i))
					continue;
			}
			move[idx] = i;
			findmove(move, idx + 1);
		}

	}

	private static void go(int[] move) {

		Info R = null;
		Info B = null;
		char temparr[][] = new char[N][M];
		for (int i = 0; i < N; i++) {
			temparr[i] = Arrays.copyOf(arr[i], M);
			for (int j = 0; j < M; j++) {
				if (temparr[i][j] == 'R') {
					R = new Info(i, j, 'R');

				} else if (temparr[i][j] == 'B') {
					B = new Info(i, j, 'B');
				}
			}
		}
		for (int i = 0; i < move.length; i++) {
			int dir = move[i];
			int Rednum = 0;
			int Bluenum = 0;
			// 0 위 1 왼 2 아래 3 오른
			while (true) {
			
				Rednum = moving(dir, temparr, R);
				Bluenum = moving(dir, temparr, B);

				if (Bluenum == 3) {
					return;
				}
				if (Rednum == 3) {
					if (min == -1 || min > i + 1) {
						min = i + 1;
					}

					return;
				}
				if (Bluenum == 1 && Rednum == 1)
					break;
			}

		}

	}

	private static int moving(int dir, char[][] temparr, Info pos) {
		boolean flag = false;
		int ti = pos.i;
		int tj = pos.j;
		if (temparr[ti][tj] == '.')
			return 1;
		while (true) {
			ti = pos.i + di[dir];
			tj = pos.j + dj[dir];
			if (temparr[ti][tj] == 'O') {
				temparr[pos.i][pos.j] = '.';
				return 3;
			}
			if (temparr[ti][tj] == '.') {
				char temp = temparr[ti][tj];
				temparr[ti][tj] = temparr[pos.i][pos.j];
				temparr[pos.i][pos.j] = temp;
				flag = true;
			} else {
				break;
			}
			pos.i = ti;
			pos.j = tj;

		}

		if (flag) {
			return 2;
		} else {
			return 1;
		}
	}

	public static int opposite(int num) {
		if (num == 0) {
			return 2;
		} else if (num == 1) {
			return 3;
		} else if (num == 2) {
			return 0;
		} else {
			return 1;
		}
	}

	public static class Info {
		int i;
		int j;
		char color;

		Info(int i, int j, char color) {
			this.i = i;
			this.j = j;
			this.color = color;
		}
	}
}
