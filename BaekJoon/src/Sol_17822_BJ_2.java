import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Sol_17822_BJ_2 {
	public static int N;
	public static int M;
	public static int T;
	public static int arr[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][M + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int a = 0; a < T; a++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int count = Integer.parseInt(st.nextToken());
			for (int i = 1; i <= N; i++) {
				if (i % num == 0) {
					if (d == 0) {
						moveToRight(arr[i], count);
					} else {
						moveToLeft(arr[i], count);
					}
				}
			}

			boolean flag = false;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= M; j++) {
					List<Info> list = new ArrayList<>();

					list.add(new Info(i, j));

					if (arr[i][j] != 0) {
						boolean visited[][] = new boolean[N + 1][M + 1];
						find(list, i, j, arr[i][j], visited);
						if (list.size() > 1) {
							for (int k = 0; k < list.size(); k++) {
								Info temp = list.get(k);
								arr[temp.i][temp.j] = 0;
								flag = true;
							}
						}
					}
				}

			}
			if (!flag) {
				int cnt1 = 0;
				int result = 0;
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= M; j++) {
						if (arr[i][j] != 0) {
							cnt1++;
							result += arr[i][j];
						}
					}
				}
				if (cnt1 == 0)
					break;
				double avg = (double) (result) / cnt1;
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= M; j++) {
						if (arr[i][j] != 0) {
							if (arr[i][j] < avg) {
								arr[i][j]++;
							} else if (arr[i][j] > avg) {
								arr[i][j]--;
							}
						}
					}
				}

			}

		}

		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				if (arr[i][j] != 0)
					cnt += arr[i][j];
			}
		}
		System.out.println(cnt);
	}

	private static void find(List<Info> list, int i, int j, int num, boolean[][] visited) {
		if (visited[i][j])
			return;
		if (i > N || i < 1)
			return;
		visited[i][j] = true;
		if (j == 1) {
			if (arr[i][M] == num && !visited[i][M]) {
				list.add(new Info(i, M));

				find(list, i, M, num, visited);
			}
		}
		if (j > 1) {
			if (arr[i][j - 1] == num && !visited[i][j - 1]) {
				list.add(new Info(i, j - 1));
				find(list, i, j - 1, num, visited);
			}
		}
		if (j == M) {
			if (arr[i][1] == num && !visited[i][1]) {
				list.add(new Info(i, 1));
				find(list, i, 1, num, visited);
			}
		}
		if (j < M) {
			if (arr[i][j + 1] == num && !visited[i][j + 1]) {
				list.add(new Info(i, j + 1));
				find(list, i, j + 1, num, visited);
			}
		}
		if (i < N && arr[i + 1][j] == num && !visited[i + 1][j]) {
			list.add(new Info(i + 1, j));
			find(list, i + 1, j, num, visited);
		}
		if (i > 1 && arr[i - 1][j] == num && !visited[i - 1][j]) {
			list.add(new Info(i - 1, j));
			find(list, i - 1, j, num, visited);
		}
	}

	public static void moveToRight(int arr[], int count) {
		for (int a = 0; a < count; a++) {
			int t = arr[arr.length - 1];
			for (int i = arr.length - 1; i > 1; i--) {
				arr[i] = arr[i - 1];
			}
			arr[1] = t;
		}
	}

	public static void moveToLeft(int arr[], int count) {
		for (int a = 0; a < count; a++) {
			int t = arr[1];
			for (int i = 1; i < arr.length - 1; i++) {
				arr[i] = arr[i + 1];
			}
			arr[arr.length - 1] = t;
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
