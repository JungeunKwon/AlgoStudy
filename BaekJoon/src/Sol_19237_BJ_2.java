import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Sol_19237_BJ_2 {
	public static int N;
	public static int M;
	public static int K;
	public static int dx[] = { 0, 0, 0, -1, 1 };
	public static int dy[] = { 0, -1, 1, 0, 0 };
	public static int arr[][];
	public static int timearr[][];
	public static int smellarr[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		List<Shark> list = new ArrayList<>();
		arr = new int[N][N];
		timearr = new int[N][N];
		smellarr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] != 0) {
					timearr[i][j] = K;
					smellarr[i][j] = arr[i][j];
					list.add(new Shark(i, j, arr[i][j]));
				}
			}
		}
		Collections.sort(list);

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			list.get(i).dir = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < M; i++) {
			int dirarr[][] = new int[5][4];
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 0; k < 4; k++) {
					dirarr[j][k] = Integer.parseInt(st.nextToken());
				}
			}
			list.get(i).order = dirarr;

		}
		int time = 0;
		while (time < 1001) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					timearr[i][j]--;
					if (timearr[i][j] < 0)
						smellarr[i][j] = 0;
				}
			}
			aa: for (int i = list.size() - 1; i >= 0; i--) {
				Shark shark = list.get(i);
				if (arr[shark.i][shark.j] != shark.num) {
					list.remove(i);
					// i++;
					continue;
				}
				arr[shark.i][shark.j] = 0;
				int dir = shark.dir;
				int tx = 0;
				int ty = 0;
				for (int j = 0; j < 4; j++) {
					int tempdir = shark.order[dir][j];
					tx = shark.j + dx[tempdir];
					ty = shark.i + dy[tempdir];
					if (tx < 0 || ty < 0 || tx >= N || ty >= N)
						continue;
					if (smellarr[ty][tx] == 0) {
						shark.j = tx;
						shark.i = ty;
						shark.dir = tempdir;
						continue aa;
					}
				}
				for (int j = 0; j < 4; j++) {
					int tempdir = shark.order[dir][j];
					tx = shark.j + dx[tempdir];
					ty = shark.i + dy[tempdir];
					if (tx < 0 || ty < 0 || tx >= N || ty >= N)
						continue;
					if (smellarr[ty][tx] == shark.num) {
						shark.j = tx;
						shark.i = ty;
						shark.dir = tempdir;
						continue aa;
					}
				}

			}
			for (int i = list.size() - 1; i >= 0; i--) {
				Shark temp = list.get(i);
				arr[temp.i][temp.j] = temp.num;
				smellarr[temp.i][temp.j] = temp.num;
				timearr[temp.i][temp.j] = K;

			}
//			System.out.println();
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.print(arr[i][j] + " ");
//				}
//				System.out.print(" | ");
//				for (int j = 0; j < N; j++) {
//					System.out.print(smellarr[i][j] + " ");
//				}
//				System.out.print(" | ");
//				for (int j = 0; j < N; j++) {
//					System.out.print(timearr[i][j] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			if (list.size() == 1)
				break;
			time++;
		}
		if (time >= 1001)
			System.out.println(-1);
		else
			System.out.println(time);
	}

	public static class Shark implements Comparable<Shark> {
		int i;
		int j;
		int num;
		int dir;
		int order[][];

		public Shark(int i, int j, int num) {
			this.i = i;
			this.j = j;
			this.num = num;
		}

		public Shark(int i, int j, int num, int dir, int order[][]) {
			this.i = i;
			this.j = j;
			this.num = num;
			this.dir = dir;
			this.order = order;

		}

		@Override
		public int compareTo(Shark o) {
			return this.num - o.num;
		}

	}
}
