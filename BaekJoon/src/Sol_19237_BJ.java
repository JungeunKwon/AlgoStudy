import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_19237_BJ {
	public static int N;
	public static int M;
	public static int K;
	public static Arr arr[][];
	public static int dx[] = { 0, 0, 0, -1, 1 };
	public static int dy[] = { 0, -1, 1, 0, 0 }; // 위, 아래, 왼쪽, 오른쪽

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new Arr[N][N];
		Shark[] sharks = new Shark[M + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = new Arr(Integer.parseInt(st.nextToken()), -1);

				if (arr[i][j].sharknum != 0) {
					sharks[arr[i][j].sharknum] = new Shark(i, j);
					arr[i][j].smell = K;
				}
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= M; i++) {
			sharks[i].dir = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= M; i++) {

			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(br.readLine());
				for (int k = 1; k <= 4; k++) {
					sharks[i].posPriority[j][k] = Integer.parseInt(st.nextToken());
				}
			}

		}
		int time = 0;
		while (time < 1001) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j].smell--;
				}
			} // 냄새 하나씩 줄이고
			int sharkcount = 0;
			aa: for (int i = M; i > 0; i--) {
				if (!sharks[i].isAlive)
					continue;
				if (arr[sharks[i].i][sharks[i].j].sharknum != i) {
					sharks[i].isAlive = false;
					continue;
				}

				sharkcount++;
				int ty = 0, tx = 0;
			
			
					int sharkdir = sharks[i].dir;
					for (int j = 1; j <= 4; j++) {
						int prioritypos = sharks[i].posPriority[sharkdir][j];
						ty = sharks[i].i + dy[prioritypos];
						tx = sharks[i].j + dx[prioritypos];
						if (ty < 0 || tx < 0 || ty >= N || tx >= N)
							continue;

						if (arr[ty][tx].smell < 0) {

							sharks[i].i = ty;
							sharks[i].j = tx;
							sharks[i].dir = prioritypos;
							continue aa;
						} 
					}
				
					sharkdir = sharks[i].dir;
					for (int j = 1; j <= 4; j++) {
						int prioritypos = sharks[i].posPriority[sharkdir][j];
						ty = sharks[i].i + dy[prioritypos];
						tx = sharks[i].j + dx[prioritypos];
						if (ty < 0 || tx < 0 || ty >= N || tx >= N)
							continue;

						if (arr[ty][tx].sharknum == i && arr[ty][tx].smell >= 0) {

							sharks[i].i = ty;
							sharks[i].j = tx;
							sharks[i].dir = prioritypos;
							continue aa;

						}
					}
			
			} // 상어들 이동
			for (int i = M; i > 0; i--) {
				if (!sharks[i].isAlive)
					continue;
				arr[sharks[i].i][sharks[i].j].smell = K;
				arr[sharks[i].i][sharks[i].j].sharknum = i;
			}

			if (sharkcount == 1)
				break;
			time++;
		}
		if (time >= 1001) {
			System.out.println(-1);
		} else
			System.out.println(time);

	}

	public static class Arr {
		int sharknum;
		int smell;

		Arr(int sharknum, int smell) {
			this.sharknum = sharknum;
			this.smell = smell;
		}
	}

	public static class Shark {
		int i;
		int j;
		int dir;
		int[][] posPriority;
		boolean isAlive;

		Shark(int i, int j) {
			this.i = i;
			this.j = j;
			this.dir = 0;
			this.posPriority = new int[5][5];
			this.isAlive = true;
		}

	}

}
