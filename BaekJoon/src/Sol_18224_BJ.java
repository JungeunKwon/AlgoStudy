import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_18224_BJ {
	public static int N;
	public static int M;
	public static int dx[] = { 1, 0, -1, 0 };
	public static int dy[] = { 0, 1, 0, -1 };
	public static int arr[][];
	public static Queue<Info> q;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		q = new LinkedList<>();
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		q.offer(new Info(0, 0));
		bfs();
	}

	private static void bfs() {
		int tx = 0, ty = 0;
		// q.offer(new Info(0,0,1,false,0));
		int count = 1;
		boolean visited[][][][] = new boolean[N][N][2][M + 1];
		visited[0][0][0][1] = true;
		int state = 0;
		int day = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int k = 0; k < size; k++) {

				Info temp = q.poll();
				if (temp.i == N - 1 && temp.j == N - 1) {
					if (state == 0) {
						System.out.println(day + " sun");
					} else {
						System.out.println(day + " moon");
					}
					q.clear();
					return;
				}

				// ³· 0
				out : for (int i = 0; i < 4; i++) {
					tx = temp.j + dx[i];
					ty = temp.i + dy[i];
					
					if (tx < 0 || ty < 0 || tx >= N || ty >= N) continue;

					if (arr[ty][tx] == 0) {
						if (visited[ty][tx][state][count]) continue;
						visited[ty][tx][state][count] = true;
						q.offer(new Info(ty, tx));
					} else {
						if (state == 1) {
							int ttx = tx, tty = ty;
							aa : while (true) {
								ttx = ttx + dx[i];
								tty = tty + dy[i];
								if (ttx < 0 || tty < 0 || ttx >= N || tty >= N)
									continue out;
								if (arr[tty][ttx] == 0) {
									//if (visited[tty][ttx][state][count]) continue;
									visited[tty][ttx][state][count] = true;
									q.offer(new Info(tty, ttx));
									break aa;
								}
							}
						}
					}
				}

			}
			if (count == M) {
				if (state == 0)
					state = 1;
				else {
					state = 0;
					day++;
				}
				count = 1;
			} else {
				count++;
			}
		}
		System.out.println(-1);
	}

	public static class Info {
		int i;
		int j;

		Info(int i, int j) {
			this.i = i;
			this.j = j;

		}
	}
}
