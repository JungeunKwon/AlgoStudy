import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Sol_15683_BJ {
	public static int N;
	public static int M;
	public static int arr[][];
	public static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		min = Integer.MAX_VALUE;
		List<CCTV> cctvs = new ArrayList<>();
		cctvs.add(new CCTV(null, null, 0, 0));
		int[][] dx = { { 1 }, { 0 }, { -1 }, { 0 } };
		int[][] dy = { { 0 }, { 1 }, { 0 }, { -1 } };
		cctvs.add(new CCTV(dx, dy, 4, 1));
		int[][] dx2 = { { -1, 1 }, { 0, 0 } };
		int[][] dy2 = { { 0, 0 }, { -1, 1 } };
		cctvs.add(new CCTV(dx2, dy2, 2, 2));
		int[][] dx3 = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
		int[][] dy3 = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
		cctvs.add(new CCTV(dx3, dy3, 4, 2));
		int[][] dx4 = { { -1, 0, 1 }, { 0, 1, 0 }, { 1, 0, -1 }, { 0, -1, 0 } };
		int[][] dy4 = { { 0, -1, 0 }, { -1, 0, 1 }, { 0, 1, 0 }, { -1, 0, 1 } };
		cctvs.add(new CCTV(dx4, dy4, 4, 3));
		int[][] dx5 = { { 0, 1, 0, -1 } };
		int[][] dy5 = { { -1, 0, 1, 0 } };
		cctvs.add(new CCTV(dx5, dy5, 1, 4));
		findArea(arr, cctvs, 0, 0);

		System.out.println(min);
	}

	private static void findArea(int[][] arr2, List<CCTV> cctvs, int ti, int tj) {
		if (tj == M) {
			tj = 0;
			ti++;
		}
		if (ti == N) {
			int result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr2[i][j] == 0)
						result++;
					if (min < result)
						return;
				}
			}
			
			if (min > result)
				min = result;
			return;
		}

		if (arr2[ti][tj] > 0 && arr2[ti][tj] < 6) {
			int num = arr2[ti][tj];
			int newarr[][] = new int[N][M];
			CCTV temp = cctvs.get(num);
			for (int i = 0; i < temp.rotatecnt; i++) {
				for (int j = 0; j < N; j++) {
					newarr[j] = Arrays.copyOf(arr2[j], M);
				}

				for (int j = 0; j < temp.arrowcnt; j++) {
					int ty = ti;
					int tx = tj;
					while (true) {
						ty = ty + temp.dy[i][j];
						tx = tx + temp.dx[i][j];
						if (ty < 0 || tx < 0 || ty >= N || tx >= M) {
							break;
						}
						if (newarr[ty][tx] == 6)
							break;
						if (newarr[ty][tx] != 0)
							continue;
						newarr[ty][tx] = -1;
					}
				}
				
				findArea(newarr, cctvs, ti, tj+1);

			}

		} else {
			findArea(arr2, cctvs, ti, tj + 1);
		}

	}

	public static class CCTV {
		int[][] dx;
		int[][] dy;
		int rotatecnt;
		int arrowcnt;

		CCTV(int[][] dx, int[][] dy, int rotatecnt, int arrowcnt) {
			this.dx = dx;
			this.dy = dy;
			this.rotatecnt = rotatecnt;
			this.arrowcnt = arrowcnt;
		}
	}
}
