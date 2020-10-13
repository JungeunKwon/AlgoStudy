import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_17143_BJ_2 {
	public static int R;
	public static int C;
	public static int M;
	public static int garo;
	public static int sero;
	public static int dx[] = { 0, 0, 0, 1, -1 };
	public static int dy[] = { 0, -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int arr[][] = new int[R + 1][C + 1];
		garo = (C - 1) * 2;
		sero = (R - 1) * 2;
		LinkedList<Shark> list = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int speed = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			arr[x][y] = size;
			list.add(new Shark(x, y, speed, dir, size));
		}
		Collections.sort(list);
		int answer = 0;
		for (int j = 1; j <= C; j++) {
			for (int i = 1; i <= R; i++) {
				if (arr[i][j] != 0) {
					answer += arr[i][j];
					arr[i][j] = 0;
					break;
				}
			}
			int newarr[][] = new int[R + 1][C + 1];
			int size = list.size();
			for (int i = 0; i < size; i++) {
				Shark temp = list.getFirst();
				list.removeFirst();
				if (arr[temp.i][temp.j] != temp.size)
					continue;
				int dir = temp.dir;
				int tx = temp.j;
				int ty = temp.i;
				int speed = 0;
				switch (dir) {
				case 1:
				case 2:
					speed = temp.speed % sero;
					for (int k = 0; k < speed; k++) {
						tx = tx + dx[dir];
						ty = ty + dy[dir];
						if (tx <= 0 || ty <= 0 || tx > C || ty > R) {
							tx = tx - dx[dir];
							ty = ty - dy[dir];
							if (dir == 1)
								dir = 2;
							else
								dir = 1;
							k--;
							continue;
						}

					}
					if (newarr[ty][tx] < temp.size) {
						newarr[ty][tx] = temp.size;
						temp.dir = dir;
						temp.i = ty;
						temp.j = tx;
						list.addLast(temp);
					}
					break;
				case 3:
				case 4:
					speed = temp.speed % garo;
					for (int k = 0; k < speed; k++) {
						tx = tx + dx[dir];
						ty = ty + dy[dir];
						if (tx <= 0 || ty <= 0 || tx > C || ty > R) {
							tx = tx - dx[dir];
							ty = ty - dy[dir];
							if (dir == 4)
								dir = 3;
							else
								dir = 4;
							k--;
							continue;
						}
					}
					if (newarr[ty][tx] < temp.size) {
						newarr[ty][tx] = temp.size;
						temp.dir = dir;
						temp.i = ty;
						temp.j = tx;
						list.addLast(temp);

					}

					break;

				}

			}

			arr = newarr;
		}
		System.out.println(answer);
	}

	public static class Shark implements Comparable<Shark> {
		int i;
		int j;
		int speed;
		int dir;
		int size;

		public Shark(int i, int j, int speed, int dir, int size) {
			super();
			this.i = i;
			this.j = j;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}

		@Override
		public String toString() {
			return "Shark [i=" + i + ", j=" + j + ", speed=" + speed + ", dir=" + dir + ", size=" + size + "]";
		}

		@Override
		public int compareTo(Shark o) {
			return o.size - this.size;
		}

	}
}
