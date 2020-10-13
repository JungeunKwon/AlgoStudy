import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_16236_BJ_2 {
	public static int N;
	public static int arr[][];
	public static int sharksize;
	public static int sharkstack;
	public static int dx[] = { 0, -1, 0, 1 };
	public static int dy[] = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		StringTokenizer st;
		sharksize = 2;
		sharkstack = 0;
		Shark shark = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) {
					shark = new Shark(i, j);
				}
			}
		}
		int time = 0;
		while (true) {
			PriorityQueue<Fish> pq = new PriorityQueue<>();
			findFish(shark, pq);
			if (pq.isEmpty()) {
				break;
			}
			Fish fish = pq.poll();
			time += fish.dir;
			arr[fish.i][fish.j] = 0;
			arr[shark.i][shark.j] = 0;
			shark.i = fish.i;
			shark.j = fish.j;
			
			sharkstack++;
			if (sharkstack >= sharksize) {
				sharksize++;
				sharkstack = 0;
			}
			
		}

		System.out.println(time);
	}

	private static void findFish(Shark shark, PriorityQueue<Fish> pq) {
		Queue<Shark> q = new LinkedList<>();
		boolean visited[][] = new boolean[N][N];
		q.offer(shark);
		visited[shark.i][shark.j] = true;
		int dir = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Shark temp = q.poll();
				int tx = 0, ty = 0;
				for (int j = 0; j < 4; j++) {
					tx = temp.j + dx[j];
					ty = temp.i + dy[j];
					if (tx < 0 || ty < 0 || tx >= N || ty >= N)
						continue;
					if (visited[ty][tx])
						continue;
					visited[ty][tx] = true;
					if (arr[ty][tx] > sharksize)
						continue;
					if (arr[ty][tx] != 0 && arr[ty][tx] < sharksize) {
						pq.add(new Fish(ty, tx, arr[ty][tx], dir));
					}
					q.offer(new Shark(ty, tx));
				}
			}
			dir++;
		}
	}

	public static class Shark {
		int i;
		int j;

		public Shark(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static class Fish implements Comparable<Fish> {
		int i;
		int j;
		int size;
		int dir;

		Fish(int i, int j, int size, int dir) {
			this.i = i;
			this.j = j;
			this.size = size;
			this.dir = dir;
		}

		@Override
		public int compareTo(Fish o) {
			if (o.size < sharksize && this.size < sharksize) {
				if (this.dir == o.dir) {
					if (this.i == o.i) {
						return this.j - o.j;
					} else
						return this.i - o.i;
				} else {
					return this.dir - o.dir;
				}
			} else {
				return this.size - o.size;
			}
		}
	}
}
