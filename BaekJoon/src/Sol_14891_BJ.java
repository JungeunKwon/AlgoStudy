import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Sol_14891_BJ {
	public static int N = 4;
	public static int M = 8;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] arr = new int[N + 1][M];
		for (int i = 1; i <= N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}
		int K = Integer.parseInt(br.readLine());
		List<Info> list = new ArrayList<>();
		StringTokenizer st;
		for (int i = 0; i < K; i++) {
			boolean visited[] = new boolean[N + 1];
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			list.clear();
			getList(arr, list, idx, dir, visited);
			for (int j = 0; j < list.size(); j++) {
				Info temp = list.get(j);
				if (temp.dir == 1) {
					rotateRight(arr[temp.idx]);
				} else {
					rotateLeft(arr[temp.idx]);
				}
			}
		}
		int result = arr[1][0] * 1 + arr[2][0] * 2 + arr[3][0] * 4 + arr[4][0] * 8;
		System.out.println(result);
	}

	private static void getList(int[][] arr, List<Info> list, int idx, int dir, boolean visited[]) {
		if (idx <= 0)
			return;
		if (idx > N)
			return;
		if (visited[idx])
			return;
		visited[idx] = true;
		list.add(new Info(idx, dir));
		if (idx - 1 > 0) {
			if (arr[idx][6] != arr[idx - 1][2]) {
				getList(arr, list, idx - 1, dir == -1 ? 1 : -1, visited);
			}
		}
		if (idx + 1 <= N) {
			if (arr[idx][2] != arr[idx + 1][6]) {
				getList(arr, list, idx + 1, dir == -1 ? 1 : -1, visited);
			}
		}
	}

	public static void rotateRight(int[] rotatearr) {
		int temp = rotatearr[M - 1];
		for (int i = M - 2; i >= 0; i--) {
			rotatearr[i + 1] = rotatearr[i];
		}
		rotatearr[0] = temp;

	}

	public static void rotateLeft(int[] rotatearr) {
		int temp = rotatearr[0];
		for (int i = 0; i < M - 1; i++) {
			rotatearr[i] = rotatearr[i + 1];
		}
		rotatearr[M - 1] = temp;

	}

	public static class Info {
		int idx;
		int dir;

		@Override
		public String toString() {
			return "Info [idx=" + idx + ", dir=" + dir + "]";
		}

		Info(int idx, int dir) {
			this.idx = idx;
			this.dir = dir;
		}
	}

}
