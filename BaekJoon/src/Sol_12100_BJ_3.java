import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_12100_BJ_3 {
	public static int N;
	// 상하좌우
	public static int dx[] = { 0, 0, -1, 1 };
	public static int dy[] = { -1, 1, 0, 0 };
	public static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int arr[][] = new int[N][N];
		StringTokenizer st;
		max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (max < arr[i][j])
					max = arr[i][j];
			}
		}
		int move[] = new int[5];
		findMove(move, arr, 5, 0);
		System.out.println(max);
	}

	private static void findMove(int[] move, int[][] arr, int five, int idx) {
		if (idx >= five) {
			int newarr[][] = new int[N][N];
			for (int i = 0; i < N; i++) {
				newarr[i] = Arrays.copyOf(arr[i], N);
			}
			Move(move, newarr);
			return;
		}
		for (int i = 0; i < 4; i++) {

			move[idx] = i;
			findMove(move, arr, five, idx + 1);
		}
	}

	private static void Move(int[] move, int[][] newarr) {
		for (int i = 0; i < 5; i++) {

			switch (move[i]) {
			case 0:
				moveToUp(newarr);
				break;
			case 1:
				moveToDown(newarr);
				break;
			case 2:
				moveToLeft(newarr);
				break;
			case 3:
				moveToRight(newarr);
				break;
			}

		}
	}

	private static void moveToUp(int[][] newarr) {
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			list.clear();
			for (int j = 0; j < N; j++) {
				if (newarr[j][i] == 0)
					continue;
				if (!list.isEmpty()) {
					if (list.getLast() == newarr[j][i]) {
						list.removeLast();
						list.addLast(newarr[j][i] * 2);
						list.addLast(0);
					} else
						list.addLast(newarr[j][i]);
				} else {
					list.addLast(newarr[j][i]);
				}
				newarr[j][i] = 0;
			}
			int size = list.size();
			for (int j = 0, k = 0; j < size; j++) {
				int t = list.getFirst();
				if (t > max)
					max = t;
				if (t != 0) {
					newarr[k][i] = t;
					k++;
				}
				list.removeFirst();
			}
		}
	}

	private static void moveToDown(int[][] newarr) {
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			list.clear();
			for (int j = N - 1; j >= 0; j--) {
				if (newarr[j][i] == 0)
					continue;

				if (!list.isEmpty()) {
					if (list.getLast() == newarr[j][i]) {
						list.removeLast();
						list.addLast(newarr[j][i] * 2);
						list.addLast(0);
					} else
						list.addLast(newarr[j][i]);
				} else {
					list.addLast(newarr[j][i]);
				}
				newarr[j][i] = 0;
			}

			int size = list.size();

			for (int j = 0, k = N - 1; j < size; j++) {
				int t = list.getFirst();
				if (t > max)
					max = t;

				if (t != 0) {
					newarr[k][i] = t;
					k--;
				}
				list.removeFirst();

			}
		}

	}

	private static void moveToLeft(int[][] newarr) {
		LinkedList<Integer> list = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			list.clear();
			for (int j = 0; j < N; j++) {
				if (newarr[i][j] == 0)
					continue;

				if (!list.isEmpty()) {
					if (list.getLast() == newarr[i][j]) {
						list.removeLast();
						list.addLast(newarr[i][j] * 2);
						list.addLast(0);
					} else
						list.addLast(newarr[i][j]);
				} else {
					list.addLast(newarr[i][j]);
				}
				newarr[i][j] = 0;
			}

			int size = list.size();

			for (int j = 0, k = 0; j < size; j++) {
				int t = list.getFirst();
				if (t > max)
					max = t;

				if (t != 0) {
					newarr[i][k] = t;
					k++;
				}
				list.removeFirst();

			}
		}
	}

	private static void moveToRight(int[][] newarr) {
		LinkedList<Integer> list = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			list.clear();
			for (int j = N - 1; j >= 0; j--) {
				if (newarr[i][j] == 0)
					continue;

				if (!list.isEmpty()) {
					if (list.getLast() == newarr[i][j]) {
						list.removeLast();
						list.addLast(newarr[i][j] * 2);
						list.addLast(0);
					} else
						list.addLast(newarr[i][j]);
				} else {
					list.addLast(newarr[i][j]);
				}
				newarr[i][j] = 0;
			}
	
			int size = list.size();

			for (int j = 0, k = N - 1; j < size; j++) {
				int t = list.getFirst();
				if (t > max)
					max = t;

				if (t != 0) {
					newarr[i][k] = t;
					k--;
				}
				list.removeFirst();

			}
		}

	}

}
