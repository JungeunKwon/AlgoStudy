import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_14890_BJ_2 {
	public static int N;
	public static int L;
	public static int arr[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			if (checkVerti(i)) {
				cnt++;
			}
			if (checkHori(i)) {
				cnt++;
			}
		}
		System.out.println(cnt);

	}

	public static boolean checkHori(int startj) {
		boolean visited[] = new boolean[N];
		for (int i = 0; i < N - 1; i++) {

			if (Math.abs(arr[i][startj] - arr[i + 1][startj]) > 1)
				return false;
			if (arr[i][startj] < arr[i + 1][startj]) {
				for (int k = i; k > i - L; k--) {
					if (k < 0)
						return false;
					if (arr[i][startj] != arr[k][startj])
						return false;
					if (visited[k])
						return false;
					visited[k] = true;
				}
			} else if (arr[i][startj] > arr[i + 1][startj]) {
				for (int k = i + 1; k < (i + 1) + L; k++) {
					if (k >= N)
						return false;
					if (arr[i + 1][startj] != arr[k][startj])
						return false;
					if (visited[k])
						return false;
					visited[k] = true;
				}
			}

		}

		return true;
	}

	public static boolean checkVerti(int starti) {
		boolean visited[] = new boolean[N];

		for (int j = 0; j < N - 1; j++) {
			if (Math.abs(arr[starti][j] - arr[starti][j + 1]) > 1)
				return false;
			if (arr[starti][j] < arr[starti][j + 1]) {
				for (int k = j; k > j - L; k--) {
					if (k < 0)
						return false;
					if (arr[starti][j] != arr[starti][k])
						return false;
					if (visited[k])
						return false;
					visited[k] = true;
				}
			} else if (arr[starti][j] > arr[starti][j + 1]) {
				for (int k = j + 1; k < (j + 1) + L; k++) {
					if (k >= N)
						return false;
					if (arr[starti][j + 1] != arr[starti][k])
						return false;
					if (visited[k])
						return false;
					visited[k] = true;
				}
			}
		}
		return true;
	}
}
