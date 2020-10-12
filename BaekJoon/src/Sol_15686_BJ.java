import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_15686_BJ {
	public static int N;
	public static int min;
	public static int dx[] = { 0, -1, 0, 1 };
	public static int dy[] = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		List<Chicken> chicken = new ArrayList<>();
		int arr[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					chicken.add(new Chicken(i, j));
					arr[i][j] = 0;
				}
			}
		}
		min = Integer.MAX_VALUE;
		boolean[] flag = new boolean[chicken.size()];
		int[] numarr = new int[chicken.size()];
		Combi(arr, M, chicken, chicken.size(), flag, numarr, 0, 0);
		System.out.println(min);
	}

	private static void Combi(int[][] arr, int m, List<Chicken> chicken, int size, boolean[] flag, int[] numarr,
			int idx, int depth) {
		if (depth >= m) {

			List<Chicken> newlist = new ArrayList<>();
			for (int i = 0; i < m; i++) {
				int num = numarr[i];
				Chicken temp = chicken.get(num);
				newlist.add(new Chicken(temp.i, temp.j));
			}
			min = Math.min(checkdist(arr, newlist), min);
			return;
		}
		for (int i = idx; i < size; i++) {
			numarr[depth] = i;
			Combi(arr, m, chicken, size, flag, numarr, i + 1, depth + 1);
		}
	}

	private static int checkdist(int[][] arr, List<Chicken> newlist) {
		int result = 0;
		for (int i = 0; i < N; i++) {

			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 1) {
					int innermin = Integer.MAX_VALUE;
					for (int k = 0; k < newlist.size(); k++) {
						Chicken chicken = newlist.get(k);
						int x = Math.abs(i - chicken.i);
						int y = Math.abs(j - chicken.j);
						innermin = Math.min(innermin, x + y);
					}
					result += innermin;

				}
			}
		}
		return result;
	}

	public static class Chicken {
		int i;
		int j;

		public Chicken(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
