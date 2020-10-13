import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Sol_16235_BJ {
	public static int N;
	public static int M;
	public static int K;
	public static int arr[][];
	public static int addarr[][];
	public static int diedarr[][];
	public static int dx[] = { 0, 1, 1, 1, 0, -1, -1, -1 };
	public static int dy[] = { -1, -1, 0, 1, 1, 1, 0, -1 };
	public static int treecnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		LinkedList<Integer> trees[][] = new LinkedList[N + 1][N + 1];
		treecnt = 0;
		addarr = new int[N + 1][N + 1];
		diedarr = new int[N + 1][N + 1];
		arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				addarr[i][j] = Integer.parseInt(st.nextToken());
				arr[i][j] = 5;
				trees[i][j] = new LinkedList<>();
				
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			trees[x][y].add(age);
			treecnt++;
		}
		int year = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (trees[i][j].size() == 0)
					continue;
				Collections.sort(trees[i][j]);
			}
		}

		while (year < K) {

			Spring(trees);
			Summer();
			Fall(trees);
			Winter();
			year++;
		}
		System.out.println(treecnt);
	}

	private static void Winter() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				arr[i][j] += addarr[i][j];
			}
		}
	}

	private static void Fall(LinkedList<Integer> trees[][]) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (trees[i][j].size() == 0)
					continue;
				int size = trees[i][j].size();
				for (int k = 0; k < size; k++) {
					int age = trees[i][j].get(k);
					if (age % 5 == 0) {
						int tx = 0, ty = 0;
						for (int l = 0; l < 8; l++) {
							tx = j + dx[l];
							ty = i + dy[l];
							if (tx <= 0 || ty <= 0 || tx > N || ty > N)
								continue;
							trees[ty][tx].addFirst(1);
							treecnt++;
						}
					}

				}
			}
		}
	}

	private static void Summer() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (diedarr[i][j] != 0) {
					arr[i][j] += diedarr[i][j];
					diedarr[i][j] = 0;
				}
			}
		}

	}

	private static void Spring(LinkedList<Integer> trees[][]) {

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (trees[i][j].size() == 0)
					continue;
				int size = trees[i][j].size();
				for (int k = 0; k < size; k++) {
					int age = trees[i][j].getFirst();
					trees[i][j].removeFirst();
					if (arr[i][j] < age) {
						diedarr[i][j] += (age / 2);
						treecnt--;
					} else {
						arr[i][j] -= age;
						trees[i][j].addLast(age + 1);
					}
				}
			}
		}
	}

}
