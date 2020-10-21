import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_20061_BJ {
	public static int score;
	public static int dx[] = { 0, 0, 0, 1 };
	public static int dy[] = { 0, 0, 1, 0 };
	public static int greendx[] = { 0, 0, 0, 1 };
	public static int greendy[] = { 0, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int bluearr[][] = new int[4][6];
		int greenarr[][] = new int[6][4];
		score = 0;
		StringTokenizer st;
		for (int a = 0; a < N; a++) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			blueMove(bluearr, t, x);
			greenMove(greenarr, t, y);
		}
		int cnt = 0;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				if (bluearr[j][i] > 0)
					cnt++;
				if (greenarr[i][j] > 0)
					cnt++;
			}
		}

		System.out.println(score);
		System.out.println(cnt);
	}

	private static void greenMove(int[][] greenarr, int t, int y) {
		int tx = 0, ty = y;
		boolean flag = false;
		for (int i = 0; i < 6; i++) {
			tx = i + greendx[t];
			ty = y + greendy[t];
			if (tx < 6 && (greenarr[i][y] != 0 || greenarr[tx][ty] != 0)) {
				flag = true;
				i = i - 1;
				tx = i + greendx[t];
				ty = y + greendy[t];
				greenarr[i][y] = t;
				greenarr[tx][ty] = t;
				break;

			}
		}
		if (!flag) {
			tx = 5 + greendx[t];
			ty = y + greendy[t];

			if (tx > 5) {
				tx = 5;
				greenarr[4][y] = t;
				greenarr[tx][ty] = t;
			} else {
				greenarr[5][y] = t;
				greenarr[tx][ty] = t;
			}
		}

		checkRemoveGreen(greenarr);

		checkTintGreen(greenarr);

	}

	private static void checkTintGreen(int[][] greenarr) {
		int cnt = 0;
		boolean flag = false;
		aa: for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
				if (greenarr[i][j] > 0) {
					cnt++;
					flag = true;
					continue aa;
				}

			}
		}
		if (flag) {
			for (int i = 5; i > 5 - cnt; i--) {
				for (int j = 0; j < 4; j++) {
					greenarr[i][j] = 0;
				}
			}
			for (int i = 0; i < cnt; i++) {
				for (int j = 0; j < 4; j++) {
					Queue<Integer> q = new LinkedList<Integer>();

					for (int k = 4; k >= 0; k--) {
						q.offer(greenarr[k][j]);
						greenarr[k][j] = 0;
					}
					int start = 5;
					while (!q.isEmpty()) {
						int t = q.poll();
						greenarr[start][j] = t;
						start--;
					}
				}
			}

		}

	}

	private static void checkRemoveGreen(int[][] greenarr) {

		while (true) {
			boolean flag = false;
			for (int i = 5; i >= 0; i--) {
				int cnt = 0;
				for (int j = 0; j < 4; j++) {
					if (greenarr[i][j] > 0)
						cnt++;
				}
				if (cnt == 4) {
					flag = true;
					score++;
					for (int j = 0; j < 4; j++) {
						greenarr[i][j] = 0;
					}
					for (int j = 0; j < 4; j++) {
						Queue<Integer> q = new LinkedList<Integer>();

						for (int k = i - 1; k >= 0; k--) {
							q.offer(greenarr[k][j]);
							greenarr[k][j] = 0;
						}
						int start = i;
						while (!q.isEmpty()) {
							int t = q.poll();
							greenarr[start][j] = t;
							start--;
						}
					}

					i++;
				}

			}

			if (!flag)
				break;
		}

	}

	private static void blueMove(int[][] bluearr, int t, int x) {
		int tx = x, ty = 0;
		boolean flag = false;
		for (int j = 0; j < 6; j++) {
			tx = x + dx[t];
			ty = j + dy[t];
			if (ty < 6 && (bluearr[x][j] != 0 || bluearr[tx][ty] != 0)) {

				flag = true;
				j = j - 1;
				tx = x + dx[t];
				ty = j + dy[t];
				bluearr[x][j] = t;
				bluearr[tx][ty] = t;
				break;

			}
		}
		if (!flag) {
			tx = x + dx[t];
			ty = 5 + dy[t];

			if (ty > 5) {
				ty = 5;
				bluearr[x][4] = t;
				bluearr[tx][ty] = t;

			} else {
				bluearr[x][5] = t;
				bluearr[tx][ty] = t;

			}

		}

		checkRemoveBlue(bluearr);

		checkTintBlue(bluearr);

	}

	private static void checkTintBlue(int[][] bluearr) {
		int cnt = 0;
		boolean flag = false;
		aa: for (int j = 0; j < 2; j++) {
			for (int i = 0; i < 4; i++) {
				if (bluearr[i][j] > 0) {
					cnt++;
					flag = true;
					continue aa;
				}

			}
		}
		if (flag) {
			for (int j = 5; j > 5 - cnt; j--) {
				for (int i = 0; i < 4; i++) {
					bluearr[i][j] = 0;
				}
			}
			for (int j = 0; j < cnt; j++) {
				for (int i = 0; i < 4; i++) {
					Queue<Integer> q = new LinkedList<Integer>();

					for (int k = 4; k >= 0; k--) {
						q.offer(bluearr[i][k]);
						bluearr[i][k] = 0;
					}

					int start = 5;
					while (!q.isEmpty()) {
						int t = q.poll();
						bluearr[i][start] = t;
						start--;
					}
				}
			}
		}

	}

	private static void checkRemoveBlue(int[][] bluearr) {
		while (true) {
			boolean flag = false;

			for (int j = 5; j >= 0; j--) {
				int cnt = 0;
				for (int i = 0; i < 4; i++) {
					if (bluearr[i][j] > 0) {
						cnt++;
					}
				}
				if (cnt == 4) {
					score++;
					flag = true;
					for (int i = 0; i < 4; i++) {
						bluearr[i][j] = 0;
					}
					for (int i = 0; i < 4; i++) {
						Queue<Integer> q = new LinkedList<Integer>();

						for (int k = j - 1; k >= 0; k--) {
							q.offer(bluearr[i][k]);
							bluearr[i][k] = 0;
						}

						int start = j;
						while (!q.isEmpty()) {
							int t = q.poll();
							bluearr[i][start] = t;
							start--;
						}
					}
					j++;
				}
			}

			if (!flag)
				break;
		}
	}

}
