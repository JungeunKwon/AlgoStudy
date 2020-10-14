import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_17142_BJ {
	public static int N;
	public static int M;
	public static int arr[][];
	public static int dx[] = { 0, 1, -1, 0 };
	public static int dy[] = { 1, 0, 0, -1 };
	public static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		List<Virus> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					list.add(new Virus(i, j));
					arr[i][j] = -1;
				}
			}
		}
		min = Integer.MAX_VALUE;
		int Size = list.size();
		int virusarr[] = new int[M];
		Combi(list, Size, virusarr, 0, 0);
		if (min == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else
			System.out.println(min);
	}

	private static void Combi(List<Virus> list, int size, int[] virusarr, int idx, int depth) {
		if (depth >= M) {
			int newarr[][] = new int[N][N];
			for (int i = 0; i < N; i++) {
				newarr[i] = Arrays.copyOf(arr[i], N);
			}
			Queue<Virus> q = new LinkedList<>();
			for (int i = 0; i < M; i++) {
				int num = virusarr[i];
				Virus temp = list.get(num);
				q.offer(temp);
				newarr[temp.i][temp.j] = -2;
			}
			Result result = findVirus(newarr, q);

			if (result.isFull && result.time < min) {
				min = result.time;
			}
			return;
		}
		for (int i = idx; i < size; i++) {
			virusarr[depth] = i;
			Combi(list, size, virusarr, i + 1, depth + 1);
		}
	}

	private static Result findVirus(int[][] newarr, Queue<Virus> q) {
		boolean result = check(newarr);
		if (result)
			return new Result(result, 0);
		int time = 1;
		result = false;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Virus temp = q.poll();
				for (int j = 0; j < 4; j++) {
					int tx = temp.j + dx[j];
					int ty = temp.i + dy[j];
					if (tx < 0 || ty < 0 || tx >= N || ty >= N)
						continue;
					if (newarr[ty][tx] == 0) {
						newarr[ty][tx] = time;
						q.offer(new Virus(ty, tx));
					}
					if (newarr[ty][tx] == -1) {
						newarr[ty][tx] = -2;
						q.offer(new Virus(ty, tx));
					}
				}
			}
			result = check(newarr);
			if (result)
				return new Result(result, time);
			time++;
		}
		return new Result(false, time);
	}

	private static boolean check(int[][] newarr) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (newarr[i][j] == 0)
					return false;
			}
		}
		return true;
	}

	public static class Result {
		boolean isFull;
		int time;

		public Result(boolean isFull, int time) {
			this.isFull = isFull;
			this.time = time;
		}
	}

	public static class Virus {
		int i;
		int j;

		Virus(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
