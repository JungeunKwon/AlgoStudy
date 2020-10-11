import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Sol_19236_BJ_2 {
	public static int N = 4;
	public static int arr[][];
	public static int dx[] = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	public static int dy[] = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };
	public static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[N][N];
		List<Fish> fish = new ArrayList<>();
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				fish.add(new Fish(i, j, num, dir, true));
				arr[i][j] = num;
			}
		}
		fish.add(new Fish(0, 0, 0, 0, false));
		max = 0;
		Shark shark = new Shark(0, 0, 0);
		shark(arr, fish, shark);
		System.out.println(max);
	}

	private static void shark(int[][] arr2, List<Fish> fish, Shark shark) {
		int newarr[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			newarr[i] = Arrays.copyOf(arr2[i], N);
		}
		List<Fish> newfish = new ArrayList<>();
		int num = newarr[shark.i][shark.j];
		int dir = 0;
		for (int i = 0; i < fish.size(); i++) {
			Fish temp = fish.get(i);
			Fish newtemp = new Fish(temp.i, temp.j, temp.num, temp.dir, temp.isAlive);
			if (temp.num == num) {
				newtemp.isAlive = false;
				dir = temp.dir;
			}
			newfish.add(newtemp);
		}
		shark.cnt = shark.cnt + num;
		newarr[shark.i][shark.j] = 0;
		fishMove(newarr, newfish, shark);
		int tx = shark.j;
		int ty = shark.i;
		while (true) {
			tx = tx + dx[dir];
			ty = ty + dy[dir];
			if (tx < 0 || ty < 0 || tx >= N || ty >= N)
				break;
			if (newarr[ty][tx] == 0)
				continue;
			else {
				shark(newarr, newfish, new Shark(ty, tx, shark.cnt));
			}
		}
		if (max < shark.cnt) {
			max = shark.cnt;
		}
	}

	private static void fishMove(int[][] newarr, List<Fish> fish, Shark shark) {
		Collections.sort(fish);
		for (int i = 0; i < fish.size(); i++) {
			Fish temp = fish.get(i);

			if (!temp.isAlive)
				continue;
			int tx = temp.j + dx[temp.dir];
			int ty = temp.i + dy[temp.dir];
			int dir = temp.dir;
			boolean flag = false;
			if (tx < 0 || ty < 0 || tx >= N || ty >= N || (ty == shark.i && tx == shark.j)) {
				for (int j = 1; j < 8; j++) {
					dir = temp.dir + j;
					if (dir >= 9)
						dir = dir % 8;
					tx = temp.j + dx[dir];
					ty = temp.i + dy[dir];
					if (tx < 0 || ty < 0 || tx >= N || ty >= N)
						continue;
					if (ty == shark.i && tx == shark.j)
						continue;
					flag = true;
					break;
				}
				if (!flag)
					continue;
			}
			if (newarr[ty][tx] == 0) {
				newarr[temp.i][temp.j] = 0;
				temp.i = ty;
				temp.j = tx;
				temp.dir = dir;
				newarr[ty][tx] = temp.num;
			} else {
				int num = newarr[ty][tx];
				Fish change = fish.get(num);
				newarr[temp.i][temp.j] = num;
				change.i = temp.i;
				change.j = temp.j;
				temp.i = ty;
				temp.j = tx;
				temp.dir = dir;
				newarr[ty][tx] = temp.num;
			}

		}

	}

	public static class Shark {
		int i;
		int j;
		int cnt;

		public Shark(int i, int j, int cnt) {
			this.i = i;
			this.j = j;
			this.cnt = cnt;
		}
	}

	public static class Fish implements Comparable<Fish> {
		int i;
		int j;
		int num;
		int dir;
		boolean isAlive;

		public Fish(int i, int j, int num, int dir, boolean isAlive) {
			super();
			this.i = i;
			this.j = j;
			this.num = num;
			this.dir = dir;
			this.isAlive = isAlive;
		}

		Fish() {

		}

		@Override
		public int compareTo(Fish o) {
			return this.num - o.num;
		}

	}
}
