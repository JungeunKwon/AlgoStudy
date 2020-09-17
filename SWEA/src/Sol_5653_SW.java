import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Sol_5653_SW {
	public static int N;
	public static int M;
	public static int K;
	public static int dx[] = { 0, 0, -1, 1 };
	public static int dy[] = { -1, 1, 0, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int a = 1; a <= T; a++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			boolean visited[][] = new boolean[N + 2 * K][M + 2 * K];
			int now[][] = new int[N + 2 * K][M + 2 * K];
			int timenow[][] = new int[N + 2 * K][M + 2 * K];
			int starti = (N + 2 * K) / 2 - 1;
			int startj = (M + 2 * K) / 2 - 1;
			PriorityQueue<Info> q = new PriorityQueue();
			PriorityQueue<Info> tempq = new PriorityQueue();
			for (int i = starti; i < starti + N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = startj; j < startj + M; j++) {
					now[i][j] = Integer.parseInt(st.nextToken());
					if (now[i][j] != 0) {
						q.offer(new Info(false, now[i][j], 0, now[i][j], i, j, 0));
						visited[i][j] = true;
						timenow[i][j] = 1;
					}
				}
			}
			int time = 1;
			while (time <= K) {
				if (q.isEmpty()) {
					time++;
					Iterator it = tempq.iterator();
					while (it.hasNext()) {
						q.offer((Info) it.next());

					}
					tempq.clear();
					continue;
				}
				Info temp = q.poll();

				if (!temp.isActive && temp.inActivetime > 0) {
					temp.inActivetime--;
					tempq.offer(temp);
					continue;
				}
				if (temp.isActive) {
					if (now[temp.i][temp.j] != temp.time) {
						visited[temp.i][temp.j] = true;
						continue;
					}
				}
				if (!temp.isActive && temp.inActivetime == 0) {
					temp.isActive = true;
					temp.Activetime = temp.time;
				}
				int tx = 0, ty = 0;
				for (int i = 0; i < 4; i++) {
					tx = temp.j + dx[i];
					ty = temp.i + dy[i];
					if (visited[ty][tx] || (timenow[ty][tx] != 0 && timenow[ty][tx] < time))
						continue;
					if (now[ty][tx] >= temp.time)
						continue;
					now[ty][tx] = temp.time;
					timenow[ty][tx] = time;
					tempq.offer(new Info(false, temp.time, 0, temp.time, ty, tx, time));
				}
				temp.Activetime--;
				if (temp.Activetime == 0)
					visited[temp.i][temp.j] = true;
				else
					tempq.offer(temp);

				// time++;
			}
			Iterator it = tempq.iterator();
			while (it.hasNext()) {
				q.offer((Info) it.next());

			}
			tempq.clear();
			while (!q.isEmpty()) {
				Info temp = q.poll();
				if (now[temp.i][temp.j] != temp.time) {
					continue;
				}
				tempq.offer(temp);
			}
			System.out.println("#" + a + " " + tempq.size());
		}
	}

	public static class Info implements Comparable<Info> {
		boolean isActive;
		int time;
		int Activetime;
		int inActivetime;
		int starttime;
		int i;
		int j;

		public Info(boolean isActive, int time, int activetime, int inActivetime, int i, int j, int starttime) {
			super();
			this.isActive = isActive;
			this.time = time;
			Activetime = activetime;
			this.inActivetime = inActivetime;
			this.i = i;
			this.j = j;
			this.starttime = starttime;
		}

		public int compareTo(Info o) {
			if (this.starttime == o.starttime)
				return this.time - o.time;
			return this.starttime - o.starttime;

		}

	}
}
