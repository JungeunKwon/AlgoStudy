import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_14502_BJ {
	public static int N;
	public static int M;
	public static int arr[][];
	public static List<Virus> virus;
	public static int dx[] = { 0, 0, -1, 1 };
	public static int dy[] = { -1, 1, 0, 0 };
	public static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		List<Wall> list = new ArrayList<>();
		virus = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 0) {
					list.add(new Wall(i, j));
				}
				if (arr[i][j] == 2) {
					virus.add(new Virus(i, j));
				}
			}
		}
		max = 0;
		int size = list.size();
		int num[] = new int[3];
		boolean flag[] = new boolean[size];
		Combi(list, num, flag, size, 0);
		System.out.print(max);
	}

	private static void Combi(List<Wall> list, int[] num, boolean[] flag, int size, int idx) {
		if (idx >= 3) {
			int newarr[][] = new int[N][M];
			for (int i = 0; i < N; i++) {
				newarr[i] = Arrays.copyOf(arr[i],M);
			}
			for (int i = 0; i < 3; i++) {
				Wall temp = list.get(num[i]);
				newarr[temp.i][temp.j] = 1;
			}

			bfs(newarr);

			max = Math.max(countArr(newarr), max);
			return;
		}
		for (int i = 0; i < size; i++) {
			if (flag[i])
				continue;
			flag[i] = true;
			num[idx] = i;
			Combi(list, num, flag, size, idx + 1);
			flag[i] = false;

		}
	}

	private static int countArr(int[][] newarr) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (newarr[i][j] == 0)
					count++;
			}
		}
		return count;
	}

	private static void bfs(int[][] newarr) {
		Queue<Virus> q = new LinkedList<>();
		boolean visited[][] = new boolean[N][M];
		for (int i = 0; i < virus.size(); i++) {
			Virus temp = virus.get(i);
			q.offer(temp);
			visited[temp.i][temp.j] = true;
		}
		while (!q.isEmpty()) {
			Virus temp = q.poll();
			for (int i = 0; i < 4; i++) {
				int tx = temp.j + dx[i];
				int ty = temp.i + dy[i];
				if (tx < 0 || ty < 0 || tx >= M || ty >= N)
					continue;
				if (visited[ty][tx])
					continue;
				visited[ty][tx] = true;

				if (newarr[ty][tx] == 0) {
					newarr[ty][tx] = 2;
					q.offer(new Virus(ty, tx));
				}
			}
		}
	}

	public static class Wall {
		int i;
		int j;

		public Wall(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static class Virus {
		int i;
		int j;

		public Virus(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
