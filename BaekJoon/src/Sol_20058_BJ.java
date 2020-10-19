import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_20058_BJ {
	public static int dx[] = { 0, -1, 1, 0 };
	public static int dy[] = { -1, 0, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		int N = (int) Math.pow(2, K);
		int arr[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result = 0;
		int maxsize = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			int level =Integer.parseInt(st.nextToken());
			for(int j = 1 ; j <= level; j ++) {
				rotateIce(j, arr, N);
			}		
			result = meltingIce(arr, N);
			
		}
		boolean visited[][] = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] != 0 && !visited[i][j]) {
					visited[i][j] = true;
					Queue<IceInfo> q = new LinkedList<>();
					q.offer(new IceInfo(i, j));
					int size = checkSize(arr, q, visited, N);
					maxsize = Math.max(size, maxsize);
				}
			}
		}
		System.out.println(result);
		System.out.println(maxsize);
	}

	private static void rotateIce(int level, int[][] arr, int N) {
		int divied = (int) Math.pow(2, level);
		for (int i = divied; i <= N; i += divied) {
			for (int j = divied; j <= N; j += divied) {
				int starti = i - divied;
				int startj = j - divied;
				int endi = i;
				int endj = j;
				rotateDetail(starti, startj, endi, endj, divied, arr, N);
			}
		}
	}

	private static void rotateDetail(int starti, int startj, int endi, int endj, int divied, int[][] arr, int n) {
		int indivied = divied / 2;
		int temparr[][] = new int[indivied][indivied];
		// 0 3
		// 1 2
		// 0을 temp 로
		for (int i = starti, k = 0; i < starti + indivied; i++, k++) {
			for (int j = startj, l = 0; j < startj + indivied; j++, l++) {
				temparr[k][l] = arr[i][j];
			}
		}
		// 1을 0로
		for (int i = starti + indivied, k = starti; i < endi; i++, k++) {
			for (int j = startj; j < startj + indivied; j++) {
				arr[k][j] = arr[i][j];
			}
		}
		// 2를 1로
		for (int i = starti + indivied; i < endi; i++) {
			for (int j = startj + indivied, k = startj; j < endj; j++, k++) {
				arr[i][k] = arr[i][j];
			}
		}
		// 3을 2로
		for (int k = starti + indivied, i = starti; k < endi; i++, k++) {
			for (int j = startj + indivied; j < endj; j++) {
				arr[k][j] = arr[i][j];
			}
		}
		// temp 를 3로
		for (int i = starti, k = 0; i < starti + indivied; i++, k++) {
			for (int j = startj + indivied, l = 0; j < endj; j++, l++) {
				arr[i][j] = temparr[k][l];
			}
		}
	}

	private static int meltingIce(int[][] arr, int N) {
		int result = 0;
		int newarr[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			newarr[i] = Arrays.copyOf(arr[i], N);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 0)
					continue;
				int tx = 0, ty = 0;
				int cnt = 0;
				for (int k = 0; k < 4; k++) {
					tx = j + dx[k];
					ty = i + dy[k];
					if (tx < 0 || ty < 0 || tx >= N || ty >= N || arr[ty][tx] < 1)
						continue;
					cnt++;
				}
				if (cnt <= 2 && arr[i][j] > 0) {
					newarr[i][j] = arr[i][j] - 1;
				}
				result += newarr[i][j];
			}
		}

		for (int i = 0; i < N; i++) {
			arr[i] = Arrays.copyOf(newarr[i], N);
		}
		return result;
	}

	private static int checkSize(int[][] arr, Queue<IceInfo> q, boolean[][] visited, int N) {
		int tx = 0, ty = 0;
		int size = 1;
		while (!q.isEmpty()) {
			IceInfo temp = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = temp.j + dx[i];
				ty = temp.i + dy[i];
				if (tx < 0 || ty < 0 || tx >= N || ty >= N)
					continue;
				if (arr[ty][tx] == 0 || visited[ty][tx])
					continue;
				visited[ty][tx] = true;
				size++;
				q.offer(new IceInfo(ty, tx));
			}
		}
		return size;
	}

	public static class IceInfo {
		int i;
		int j;

		public IceInfo(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
