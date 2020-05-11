import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_2382_SW {

	public static int N;
	public static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int a = 1; a <= T; a++) {
			N = Integer.parseInt(br.readLine());
			int arr[][] = new int[N][N];
			StringTokenizer st;
			ArrayList<Info> people = new ArrayList<>();
			ArrayList<Info> stairs = new ArrayList<>();
			min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					if (arr[i][j] == 1)
						people.add(new Info(i, j));
					else if (arr[i][j] > 1)
						stairs.add(new Info(i, j, arr[i][j]));
				}
			}
			boolean flag[] = new boolean[people.size()];
			dfs(flag, arr, people, stairs, 0);

			System.out.println("#" + a + " " + min);
		}

	}

	private static void dfs(boolean[] flag, int[][] arr, ArrayList<Info> people, ArrayList<Info> stairs, int idx) {
		if (idx >= people.size()) {

			PriorityQueue<Info> stairone = new PriorityQueue<>();
			PriorityQueue<Info> stairtwo = new PriorityQueue<>();
			Info tempone = stairs.get(0);
			Info temptwo = stairs.get(1);
			for (int i = 0; i < people.size(); i++) {
				Info temp = people.get(i);
				if (flag[i]) {
					int time = Math.abs(temp.i - tempone.i) + Math.abs(temp.j - tempone.j);
					stairone.add(new Info(temp.i, temp.j, time + 1));

				} else {
					int time = Math.abs(temp.i - temptwo.i) + Math.abs(temp.j - temptwo.j);
					stairtwo.add(new Info(temp.i, temp.j, time + 1));

				}
			}
			int time = 0;
			int arrstairs[] = new int[3];
			if (!stairone.isEmpty()) {
				time = 0;

				aa:while (!stairone.isEmpty()) {
					Info temp = stairone.peek();
					if (time >= temp.dir)
					{
						 for (int i = 0; i < 3; i++) {
							if (arrstairs[i] == 0) {
								arrstairs[i] = stairs.get(0).dir;
								stairone.poll();
								continue aa;
							}
						}
					}
					time++;
					for (int i = 0; i < 3; i++) {
						if (arrstairs[i] != 0) {
							arrstairs[i]--;
						}
					}
				}
			}
			int inmax = 0;
			for (int i = 0; i < 3; i++) {
				if (inmax < arrstairs[i] ) {
					inmax = arrstairs[i];
				}
			}
			time += inmax;
			int time2 = 0;
			if (!stairtwo.isEmpty()) {
				time2 = 0;
				arrstairs = new int[3];
				bb:while (!stairtwo.isEmpty()) {
					Info temp = stairtwo.peek();
					if (time2 >= temp.dir) {
						for (int i = 0; i < 3; i++) {
							if (arrstairs[i] == 0) {
								arrstairs[i] = stairs.get(1).dir;
								stairtwo.poll();
								continue bb;
							}
						}
					}
					time2++;
					for (int i = 0; i < 3; i++) {
						if (arrstairs[i] != 0) {
							arrstairs[i]--;
						}
					}
				}

			}
			inmax = 0;
			for (int i = 0; i < 3; i++) {
				if (inmax < arrstairs[i] ) {
					inmax = arrstairs[i];
				}
			}
			time2 += inmax;
			int max = Math.max(time, time2);
			if (min > max)
				min = max;
			return;
		}
		flag[idx] = true;
		dfs(flag, arr, people, stairs, idx + 1);
		flag[idx] = false;
		dfs(flag, arr, people, stairs, idx + 1);

	}

	public static class Info implements Comparable<Info> {
		int i;
		int j;
		int dir;

		Info(int i, int j) {
			this.i = i;
			this.j = j;
			this.dir = 0;
		}

		Info(int i, int j, int dir) {
			this.i = i;
			this.j = j;
			this.dir = dir;
		}

		@Override
		public int compareTo(Info o) {
			return this.dir - o.dir;
		}
	}
}
