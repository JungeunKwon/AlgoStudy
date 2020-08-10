import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_18809_BJ {
	public static int N;
	public static int M;
	public static int G;
	public static int R;
	public static int arr[][];
	public static int total;
	public static List<Info> list;
	public static boolean flag[];
	public static int dx[] = { 0, -1, 0, 1 };
	public static int dy[] = { -1, 0, 1, 0 };
	public static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		total = G + R;
		arr = new int[N][M];
		list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					list.add(new Info(i, j));
				}
			}
		}
		max = 0;
		int trlist[] = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			trlist[i] = i;
		}
		int newtr[] = new int[total];
		Combi(trlist, newtr, list.size(), total);
		System.out.println(max);
	}

	private static void Combi(int[] trlist, int[] newtr, int n, int r) {
		if (n < r)
			return;
		if (r <= 0) {
			flag = new boolean[newtr.length];
			SeedPermu(newtr,  0,0);
			return;

		}
		newtr[r - 1] = trlist[n - 1];
		Combi(trlist, newtr, n - 1, r - 1);
		Combi(trlist, newtr, n - 1, r);

	}


	private static void SeedPermu(int[] newtr,  int depth, int idx) {
		if (depth >= R) {
			Queue<newInfo> q = new LinkedList<>();
			int redarr[][] = new int[N][M];
			int greenarr[][] = new int[N][M];
			int temparr[][] = new int[N][M];
			boolean visited[][][] = new boolean[N][M][2];
			for (int i = 0; i < newtr.length; i++) {
				Info temp = list.get(newtr[i]);
				boolean num = flag[i];
				if (num) {
					redarr[temp.i][temp.j] = 1;
					visited[temp.i][temp.j][0] = true;
					q.offer(new newInfo(temp.i, temp.j, 0));
				} else {
					greenarr[temp.i][temp.j] = 1;
					visited[temp.i][temp.j][1] = true;
					q.offer(new newInfo(temp.i, temp.j, 1));

				}

			}

			int count = go(q, redarr, greenarr, visited, temparr);
			if (count > max)
				max = count;

			return;
		}
		for (int i = idx; i < newtr.length; i++) {
			if (flag[i])
				continue;
			flag[i] = true;
			SeedPermu(newtr, depth + 1, i);
			flag[i] = false;
		}
	}

	private static int go(Queue<newInfo> q, int[][] redarr, int[][] greenarr, boolean[][][] visited, int[][] temparr) {
		int tx = 0, ty = 0;
		int count = 2;
		int total = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int a = 0; a < size; a++) {
				newInfo temp = q.poll();
				if (temparr[temp.i][temp.j] != 0)
					continue;
				int color = temp.color;
				for (int i = 0; i < 4; i++) {
					tx = temp.j + dx[i];
					ty = temp.i + dy[i];
					if (tx < 0 || ty < 0 || tx >= M || ty >= N)
						continue;
					if (arr[ty][tx] == 0)
						continue;
					if (visited[ty][tx][color])
						continue;
					visited[ty][tx][color] = true;
					if (color == 0) {
						if (greenarr[ty][tx] == count) {
							total++;
							visited[ty][tx][1] = true;
							temparr[ty][tx] = count;
							continue;
						} else {
							if (visited[ty][tx][1])
								continue;
						}
						redarr[ty][tx] = count;
					} else {
						if (redarr[ty][tx] == count) {
							total++;
							visited[ty][tx][0] = true;
							temparr[ty][tx] = count;

							continue;
						} else {
							if (visited[ty][tx][0])
								continue;
						}
						greenarr[ty][tx] = count;
					}
					q.offer(new newInfo(ty, tx, color));
				}
			}
			count++;
		}
		return total;
	}

	public static class Info {
		int i;
		int j;

		Info(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static class newInfo {
		int i;
		int j;
		int color;

		newInfo(int i, int j, int color) {
			this.i = i;
			this.j = j;
			this.color = color;
		}
	}

}
