import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Sol_2239_BJ {
	public static int N;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 9;
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = line.charAt(j) - '0';
			}
		}

		find(arr, 0, 0);

	}

	private static void find(int[][] arr, int nowi, int nowj) {
		if (nowi == 9) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(arr[i][j]);
				}
				System.out.println();
			}
			System.exit(0);
		}
		if (nowj == 9) {
			find(arr, nowi + 1, 0);
			return;
		}
		if (arr[nowi][nowj] > 0) {
			find(arr, nowi, nowj + 1);
			return;
		}

		for (int k = 1; k <= N; k++) {

			if (isOkay(arr, nowi, nowj, k)) {
				arr[nowi][nowj] = k;
				find(arr, nowi, nowj + 1);
				arr[nowi][nowj] = 0;
			}

		}

	}

	private static boolean isOkay(int[][] arr, int nowi, int nowj, int k) {
		for (int j = 0; j < N; j++) {
			if (arr[nowi][j] == k)
				return false;
		}
		for (int i = 0; i < N; i++) {
			if (arr[i][nowj] == k)
				return false;
		}
		int findi = 0, findj = 0;
		if (nowi >= 0 && nowi < 3) {
			if (nowj >= 0 && nowj < 3) {
				findi = 0;
				findj = 0;
			} else if (nowj >= 3 && nowj < 6) {
				findi = 0;
				findj = 3;

			} else if (nowj >= 6 && nowj < 9) {
				findi = 0;
				findj = 6;
			}
		} else if (nowi >= 3 && nowi < 6) {
			if (nowj >= 0 && nowj < 3) {
				findi = 3;
				findj = 0;
			} else if (nowj >= 3 && nowj < 6) {
				findi = 3;
				findj = 3;

			} else if (nowj >= 6 && nowj < 9) {
				findi = 3;
				findj = 6;
			}

		} else if (nowi >= 6 && nowi < 9) {
			if (nowj >= 0 && nowj < 3) {
				findi = 6;
				findj = 0;
			} else if (nowj >= 3 && nowj < 6) {
				findi = 6;
				findj = 3;

			} else if (nowj >= 6 && nowj < 9) {
				findi = 6;
				findj = 6;
			}
		}
		for (int i = findi; i < findi + 3; i++) {
			for (int j = findj; j < findj + 3; j++) {
				if (arr[i][j] == k)
					return false;
			}
		}
		return true;
	}

}
