import java.util.Arrays;
import java.util.LinkedList;

public class 외벽점검_2020_BLIND {

	public static void main(String[] args) {
		int n = 200;
		int weak[] = { 0, 10, 50, 80, 120, 160 };
		int dist[] = { 1, 10, 5, 40, 30 };
		int result = solution(n, weak, dist);
		System.out.println(result);
	}

	public static int count;
	public static LinkedList<Integer> list;

	public static int solution(int n, int[] weak, int[] dist) {
		int answer = 0;

		int N = dist.length;
		count = Integer.MAX_VALUE;
		int newdist[] = new int[dist.length];
		boolean flag[] = new boolean[dist.length];
		Perm(n, weak, dist, newdist, flag, 0);
		if (count == Integer.MAX_VALUE) {
			return -1;
		}
		return count;
	}

	private static void Perm(int n, int[] weak, int[] dist, int[] newdist, boolean[] flag, int idx) {
		if (idx >= dist.length) {
			boolean visited[] = new boolean[weak.length];
			int cnt = 0;
			int shift[] = Arrays.copyOf(weak, weak.length);
			for (int i = 0; i < weak.length - 1; i++) {
				cnt = 0;
				visited = new boolean[weak.length];
				bb: for (int j = 0; j < dist.length; j++) {
					boolean isFull = true;
					aa: for (int k = 0; k < weak.length; k++) {
						if (visited[k])
							continue;
						isFull = false;
						visited[k] = true;
						int start = shift[k];
						for (int l = k + 1; l != k; l++) {
							if (l >= weak.length) {
								l = -1;
								continue;
							}
							if (visited[l])
								break aa;
							int num = shift[l];
							if (num < start)
								num += n;
							int diff = num - start;
							if (diff <= newdist[j]) {
								visited[l] = true;
							} else
								break aa;
						}
					}
					if (!isFull)
						cnt++;
					else
						break bb;

					if (cnt > count)
						continue;

				}

				if (check(visited) && cnt != 0 && count > cnt) {
					count = cnt;
				}
				int t = shift[weak.length - 1];
				for (int j = weak.length - 1; j > 0; j--) {
					shift[j] = shift[j - 1];
				}
				shift[0] = t;

			}

			return;
		}
		for (int i = 0; i < dist.length; i++) {
			if (flag[i])
				continue;
			flag[i] = true;
			newdist[idx] = dist[i];
			Perm(n, weak, dist, newdist, flag, idx + 1);
			flag[i] = false;
		}
	}

	private static boolean check(boolean[] visited) {
		for (int i = 0; i < visited.length; i++) {
			if (!visited[i])
				return false;
		}
		return true;
	}

}