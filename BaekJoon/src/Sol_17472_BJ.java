import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_17472_BJ {
	public static int N;
	public static int M;
	public static int arr[][];
	public static int dx[] = { 0, 0, -1, 1 };
	public static int dy[] = { -1, 1, 0, 0 };
	public static int parent[];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		boolean visited[][] = new boolean[N][M];
		int islandNum = 1;
		Queue<Info> q = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 1 && !visited[i][j]) {
					q.clear();
					q.offer(new Info(i, j));
					visited[i][j] = true;
					arr[i][j] = islandNum;
					getNum(islandNum, q, visited);
					islandNum++;
				}
			}
		}
		visited = new boolean[N][M];
		List<Bridge> list = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] != 0 && !visited[i][j]) {
					q.clear();
					q.offer(new Info(i, j));
					visited[i][j] = true;

					getBridge(arr[i][j], q, visited, list);

				}
			}
		}
		Collections.sort(list);
		parent = new int[islandNum];
		for (int i = 0; i < islandNum; i++) {
			parent[i] = i;
		}
		int cost = 0;
		int bridgecnt = 0;

		for (int i = 0; i < list.size(); i++) {
			Bridge temp = list.get(i);
			int start = temp.start;
			int end = temp.end;
			start = findSet(start);
			end = findSet(end);
			if (start != end) {
				unionSet(start, end);
				cost += temp.cost;
				bridgecnt++;
			}

		}
		int unioncount = 0;
		for (int i = 1; i < islandNum; i++) {
			if (parent[i] == i)
				unioncount++;
		}
		if (unioncount != 1)
			cost = 0;
		if (bridgecnt != islandNum - 2)
			cost = 0;
		if (cost == 0)
			System.out.println(-1);
		else
			System.out.println(cost);

	}

	private static void unionSet(int start, int end) {
		start = findSet(start);
		end = findSet(end);
		if (start != end)
			parent[end] = start;

	}

	private static int findSet(int start) {
		if (parent[start] == start)
			return start;
		else {
			int p = findSet(parent[start]);
			parent[start] = p;
			for(int i = 0; i <parent.length ; i++)
			{
				if(parent[i] == start)
					parent[i] = p;
			}
			return p;
		}
	}

	private static void getBridge(int islandNum, Queue<Info> q, boolean[][] visited, List<Bridge> list) {
		int tx = 0, ty = 0;
		while (!q.isEmpty()) {
			Info temp = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = temp.j + dx[i];
				ty = temp.i + dy[i];
				if (tx < 0 || ty < 0 || tx >= M || ty >= N)
					continue;
				if (visited[ty][tx])
					continue;
				if (arr[ty][tx] == islandNum) {
					visited[ty][tx] = true;
					q.offer(new Info(ty, tx));
					continue;
				}

				if (arr[ty][tx] == 0) {
					int cost = 1;
					while (true) {
						tx = tx + dx[i];
						ty = ty + dy[i];
						if (tx < 0 || ty < 0 || tx >= M || ty >= N)
							break;
						if (arr[ty][tx] == islandNum)
							break;
						if (arr[ty][tx] != 0 && arr[ty][tx] != islandNum) {
							if (cost < 2)
								break;
							list.add(new Bridge(islandNum, arr[ty][tx], cost));
							break;
						}
						cost++;
					}
				}
			}
		}
	}

	private static void getNum(int islandNum, Queue<Info> q, boolean[][] visited) {
		int tx = 0, ty = 0;
		while (!q.isEmpty()) {
			Info temp = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = temp.j + dx[i];
				ty = temp.i + dy[i];
				if (tx < 0 || ty < 0 || tx >= M || ty >= N)
					continue;
				if (arr[ty][tx] == 0)
					continue;
				if (visited[ty][tx])
					continue;
				arr[ty][tx] = islandNum;
				visited[ty][tx] = true;
				q.offer(new Info(ty, tx));
			}
		}
	}

	public static class Info {
		int i;
		int j;

		Info(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}

	public static class Bridge implements Comparable<Bridge> {
		int start;
		int end;
		int cost;

		@Override
		public String toString() {
			return "Bridge [start=" + start + ", end=" + end + ", cost=" + cost + "]";
		}

		Bridge(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Bridge o) {
			return this.cost - o.cost;
		}
	}
}
