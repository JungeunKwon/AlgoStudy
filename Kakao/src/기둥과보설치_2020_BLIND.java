import java.util.Arrays;

public class 기둥과보설치_2020_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 5;
		// int[][] build_frame = { { 1, 0, 0, 1 }, { 1, 1, 1, 1 }, { 2, 1, 0, 1 }, { 2,
		// 2, 1, 1 }, { 5, 0, 0, 1 },
		// { 5, 1, 0, 1 }, { 4, 2, 1, 1 }, { 3, 2, 1, 1 } };
		int[][] build_frame = { { 0, 0, 0, 1 }, { 2, 0, 0, 1 }, { 4, 0, 0, 1 }, { 0, 1, 1, 1 }, { 1, 1, 1, 1 },
				{ 2, 1, 1, 1 }, { 3, 1, 1, 1 }, { 2, 0, 0, 0 }, { 1, 1, 1, 0 }, { 2, 2, 0, 1 } };
		int[][] result = solution(n, build_frame);
		for (int i = 0; i < result.length; i++) {
			System.out.println(Arrays.toString(result[i]));
		}
		// System.out.println(result);
	}

	public static int[][] solution(int n, int[][] build_frame) {
		int[][] answer = {};
		int[][][] visited = new int[n + 1][n + 1][2];
		int totalcnt = 0;
		// 0은 기둥 1은 보
		for (int t = 0; t < build_frame.length; t++) {
			int x = build_frame[t][0];
			int y = build_frame[t][1];
			int a = build_frame[t][2];
			int b = build_frame[t][3];
			if (b == 1) {
				if (a == 0) {
					if (y == 0)
						visited[y][x][a] = 1;
					else if (visited[y - 1][x][0] == 1 || visited[y][x][1] == 1 || (x > 0 && visited[y][x - 1][1] == 1))
						visited[y][x][a] = 1;
					else
						continue;
				} else {
					if (visited[y - 1][x][0] == 1 || visited[y - 1][x + 1][0] == 1
							|| (x > 0 && visited[y][x - 1][1] == 1 && visited[y][x + 1][1] == 1))
						visited[y][x][a] = 1;
					else
						continue;
				}
				totalcnt++;
			} else {
				visited[y][x][a] = 0;
				if (!check(n, visited)) {
					visited[y][x][a] = 1;
					continue;
				}
				totalcnt--;
			}

		}
		answer = new int[totalcnt][3];
		int k = 0;
		for (int j = 0; j <= n; j++) {
			for (int i = 0; i <= n; i++) {
				if (visited[i][j][0] == 1) {
					answer[k][0] = j;
					answer[k][1] = i;
					answer[k][2] = 0;
					k++;
				}
				if (visited[i][j][1] == 1) {
					answer[k][0] = j;
					answer[k][1] = i;
					answer[k][2] = 1;
					k++;
				}
			}
		}
		return answer;
	}

	public static boolean check1(int n, int i, int j, int[][][] visited) {
		if (i == 0)
			return true;
		if (visited[i - 1][j][0] == 1)
			return true;
		if (visited[i][j][1] == 1)
			return true;
		if (j > 0 && visited[i][j - 1][1] == 1)
			return true;
		return false;
	}

	public static boolean check2(int n, int i, int j, int[][][] visited) {
		if (visited[i - 1][j][0] == 1)
			return true;
		if (visited[i - 1][j + 1][0] == 1)
			return true;
		if (j > 0 && j < n && visited[i][j - 1][1] == 1 && visited[i][j + 1][1] == 1)
			return true;
		return false;
	}

	public static boolean check(int n, int[][][] visited) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j][0] == 1 && !check1(n, i, j, visited)) {
					return false;
				}
				if (visited[i][j][1] == 1 && !check2(n, i, j, visited)) {
					return false;
				}
			}
		}
		return true;
	}
}