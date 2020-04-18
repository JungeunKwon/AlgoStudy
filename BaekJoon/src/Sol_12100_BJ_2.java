import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_12100_BJ_2 {
	public static int N;
	public static int arr[][];
	public static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		int move[] = new int[5];
		StringTokenizer st;
		max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] > max)
					max = arr[i][j];
			}
		}
		findmove(0, move);
		System.out.println(max);
	}

	private static void findmove(int idx, int[] move) {
		if (idx >= 5) {
			return;
		}
		for (int i = 0; i < 4; i++) {
			int[][] temparr = new int[N][N];
			for (int j = 0; j < N; j++) {
				temparr[j] = Arrays.copyOf(arr[j], N);
			}
			move[idx] = i;
			play(i, arr);
			findmove(idx + 1, move);
			arr = temparr;
		}
		// 0 위 1 오른 2 아래 3 왼
	}

	private static void play(int dir, int[][] arr) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.clear();
		switch (dir) {
		case 0:
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					q.offer(arr[k][j]);
					arr[k][j] = 0;
				}
				q = move(q);
				for (int k = 0; k < N; k++) {
					if (q.isEmpty()) {
						arr[k][j] = 0;
					} else {
						arr[k][j] = q.poll();
					}
				}
				q.clear();

			}

			break;
		case 1:
			for (int j = 0; j < N; j++) {
				for (int k = N - 1; k >= 0; k--) {
					q.offer(arr[j][k]);
					arr[j][k] = 0;

				}
				q = move(q);
				for (int k = N - 1; k >= 0; k--) {
					if (q.isEmpty()) {
						arr[j][k] = 0;
					} else {
						arr[j][k] = q.poll();
					}

				}
				q.clear();
			}
			break;
		case 2:
			for (int j = 0; j < N; j++) {
				for (int k = N - 1; k >= 0; k--) {
					q.offer(arr[k][j]);
					arr[k][j] = 0;
				}
				q = move(q);
				for (int k = N - 1; k >= 0; k--) {
					if (q.isEmpty()) {
						arr[k][j] = 0;
					} else {
						arr[k][j] = q.poll();
					}
				}
				q.clear();

			}
			break;
		case 3:
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					q.offer(arr[j][k]);
					arr[j][k] = 0;
				}
				q = move(q);
				for (int k = 0; k < N; k++) {
					if (q.isEmpty()) {
						arr[j][k] = 0;
					} else {
						arr[j][k] = q.poll();
					}

				}
				q.clear();

			}
			break;
		}
	}

	private static Queue<Integer> move(Queue<Integer> q) {
		int size = q.size();
		Queue<Integer> newq = new LinkedList<>();
		aa: for (int i = 0; i < size; i++) {
			if (q.isEmpty())
				break;
			if (q.peek() == 0) {
				q.poll();
			} else {
				int t = q.poll();
				if (!q.isEmpty()) {
					if (q.peek() == 0) {
						while (!q.isEmpty()) {
							if (q.peek() == 0) {
								q.poll();
								continue;
							} else {
								if (q.peek() == t) {
									q.poll();
									newq.offer(t * 2);
									if (max < t * 2)
										max = t * 2;
								} else {
									newq.offer(t);
								}
								continue aa;
							}
						}
						newq.offer(t);

					} else if (q.peek() == t) {
						q.poll();
						newq.offer(t * 2);
						if (max < t * 2)
							max = t * 2;
					} else {
						newq.offer(t);
					}
				} else {
					newq.offer(t);
				}

			}
		}
		return newq;
	}

}
