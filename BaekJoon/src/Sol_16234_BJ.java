import java.util.List;
import java.util.Queue;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Sol_16234_BJ {
	public static int N;
	public static int L;
	public static int R;
	public static int arr[][];
	public static int dx[] = { 0, -1, 0, 1 };
	public static int dy[] = { -1, 0, 1, 0 };
	public static int tempcnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cnt = 0;
		while (true) {
			boolean flag = false;
			boolean visited[][] = new boolean[N][N];

			int[][] newarr = new int[N][N];
			for (int i = 0; i < N; i++) {
				newarr[i] = Arrays.copyOf(arr[i], N);
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j])
						continue;
					List<Info> list = new ArrayList<>();
					list.add(new Info(i, j));
					visited[i][j] = true;
					tempcnt = arr[i][j];
					Queue<Info> q = new LinkedList<>();
					q.offer(new Info(i, j));
					findArea(q, list, visited);
					if (list.size() > 1) {
						int result = tempcnt / list.size();
						for (int k = 0; k < list.size(); k++) {
							Info temp = list.get(k);
							arr[temp.i][temp.j] = result;
						}
						flag = true;
					}

				}
			}

			if (!flag)
				break;
			cnt++;
		}

		System.out.println(cnt);
	}

	private static void findArea(Queue<Info> q, List<Info> list, boolean[][] visited) {
		int tx = 0, ty = 0;
		while (!q.isEmpty()) {
			Info temp = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = temp.j + dx[i];
				ty = temp.i + dy[i];
				if (tx < 0 || ty < 0 || tx >= N || ty >= N)
					continue;
				if (visited[ty][tx])
					continue;

				int peoples = arr[ty][tx];
				int diff = Math.abs(arr[temp.i][temp.j] - peoples);
				if (diff >= L && diff <= R) {
					visited[ty][tx] = true;
					tempcnt += arr[ty][tx];
					list.add(new Info(ty, tx));
					q.offer(new Info(ty, tx));
				}
			}
		}
	}

	public static class Info {
		int i;
		int j;

		@Override
		public String toString() {
			return "Info [i=" + i + ", j=" + j + "]";
		}

		Info(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
