import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_5650_SW {
	public static int N;
	public static int max;
	public static int dx[] = { 0, 1, 0, -1 };
	public static int dy[] = { -1, 0, 1, 0 }; // 상 우 하 좌

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int a = 1; a <= T; a++) {
			N = Integer.parseInt(br.readLine());
			Hole[][] holes = new Hole[11][2];
			int arr[][] = new int[N][N];
			max = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] >= 6 && arr[i][j] <= 10) {
						if (holes[arr[i][j]][0] == null) {
							holes[arr[i][j]][0] = new Hole(i, j);
						} else {
							holes[arr[i][j]][1] = new Hole(i, j);
						}
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] == 0) {
						for (int k = 0; k < 4; k++) {
							start(i, j, arr, holes, k);
						}
					}
				}
			}
			System.out.println("#" + a + " " + max);
		}

	}

	private static void start(int i, int j, int[][] arr, Hole[][] holes, int dir) {
		Info temp = new Info(i, j, dir, 0);
		int tx = -1, ty = -1;
		while (true) {
			if ((tx == j && ty == i)) {
				if (max < temp.point)
					max = temp.point;
				break;
			}
			tx = temp.j + dx[temp.dir];
			ty = temp.i + dy[temp.dir];
			if (tx < 0 || ty < 0 || tx >= N || ty >= N || arr[ty][tx] == 5) {

				tx = tx - dx[temp.dir];
				ty = ty - dy[temp.dir];
				temp.dir = opposite(temp.dir);
				temp.point++;

				// continue;
			}
			if ((tx == j && ty == i) || arr[ty][tx] == -1) {
				if (max < temp.point)
					max = temp.point;
				break;
			}
			int num = arr[ty][tx];
			if (num == 0) {
				temp.j = tx;
				temp.i = ty;
				continue;
			}
			if (num >= 6 && num <= 10) {
				if (holes[num][0].i == ty && holes[num][0].j == tx) {
					temp.i = holes[num][1].i;
					temp.j = holes[num][1].j;
					continue;
				} else {
					temp.i = holes[num][0].i;
					temp.j = holes[num][0].j;
					continue;
				}
			}
			// 상 우 하 좌
			if (num == 1 && (temp.dir == 2 || temp.dir == 3)) {
				temp.point++;
				temp.dir = rotate(num, temp.dir);
				temp.i = ty;
				temp.j = tx;
				continue;
			}
			if (num == 2 && (temp.dir == 0 || temp.dir == 3)) {
				temp.point++;
				temp.dir = rotate(num, temp.dir);
				temp.i = ty;
				temp.j = tx;
				continue;
			}
			if (num == 3 && (temp.dir == 0 || temp.dir == 1)) {
				temp.point++;
				temp.dir = rotate(num, temp.dir);
				temp.i = ty;
				temp.j = tx;
				continue;
			}
			if (num == 4 && (temp.dir == 1 || temp.dir == 2)) {
				temp.point++;
				temp.dir = rotate(num, temp.dir);
				temp.i = ty;
				temp.j = tx;
				continue;
			}

			temp.i = ty;
			temp.j = tx;
			tx = tx - dx[temp.dir];
			ty = ty - dy[temp.dir];

			temp.dir = opposite(temp.dir);
			temp.point++;
		}
	}
	// 상 우 하 좌

	public static int rotate(int num, int dir) {
		int temp = 0;
		switch (num) {
		case 1:
			if (dir == 2)
				temp = 1;
			else if (dir == 3)
				temp = 0;
			break;
		case 2:
			if (dir == 0)
				temp = 1;
			else if (dir == 3)
				temp = 2;
			break;
		case 3:
			if (dir == 0)
				temp = 3;
			else if (dir == 1)
				temp = 2;
			break;
		case 4:
			if (dir == 2)
				temp = 3;
			else if (dir == 1)
				temp = 0;
			break;
		}
		return temp;
	}

	public static int opposite(int dir) {
		if (dir == 0)
			return 2;
		if (dir == 1)
			return 3;
		if (dir == 2)
			return 0;
		if (dir == 3)
			return 1;
		return -1;
	}

	public static class Hole {
		int i;
		int j;

		Hole(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static class Info {
		int i;
		int j;
		int dir;
		int point;

		Info(int i, int j, int dir, int point) {
			this.i = i;
			this.j = j;
			this.dir = dir;
			this.point = point;
		}
	}
}
