import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_19238_BJ {
	public static int N;
	public static int M;
	public static int arr[][];
	public static int dx[] = { 0, 0, -1, 1 };
	public static int dy[] = { -1, 1, 0, 0 };
	public static int left;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int F = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][N + 1];
		left = 0;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		BaekJoon start = new BaekJoon(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), F);
		List<People> peoplelist = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			peoplelist.add(new People(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
					Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0));
		}
		findpeople(start, peoplelist);
		//System.out.println(left);
	}
	private static void exit(int num)
	{
		System.out.println(num);
		System.exit(1);
	}
	private static void findpeople(BaekJoon start, List<People> peoplelist) {
		if(left == -1)exit(-1);
		if(start.f <= 0)
		{
			exit(-1);
		}		
		
		People min = new People(0, 0, 0, 0, Integer.MAX_VALUE);
		for (int i = 0; i < peoplelist.size(); i++) {
			People p = peoplelist.get(i);
			finddistance(start, p, p.starti, p.startj);
			if(p.dist == -1)
			{
				exit(-1);
			}
			if(p.dist <= min.dist)
			{
				if(p.dist == min.dist)
				{
					if(p.starti == min.starti)
					{
						if(p.startj < min.startj)
						{
							min = p;
						}
					}else if(p.starti < min.starti)
					{
						min = p;
					}	
				}else
				{
					min = p;
				}				
			}
				
		}
		
		start.i = min.starti;
		start.j = min.startj;
		start.f = start.f - min.dist;
	
		if(start.f < 0)
		{
			exit(-1);
		}
		finddistance(start, min,min.desti, min.destj);

		if(min.dist == -1)
		{
			exit(-1);
		}
		start.i = min.desti;
		start.j = min.destj;
		start.f = start.f - min.dist;
		if(start.f < 0)
		{
			exit(-1);
		}
		start.f = start.f +  min.dist*2;
		peoplelist.remove(min);
		if(peoplelist.isEmpty())
		{
			exit(start.f);
		}
		findpeople(start, peoplelist);
	}
	public static void finddistance(BaekJoon start, People p, int desti, int destj)
	{
		boolean visited[][] = new boolean[N + 1][N + 1];
		Queue<BaekJoon> q = new LinkedList<>();
		q.clear();
		q.offer(start);
		visited = new boolean[N + 1][N + 1];
		visited[start.i][start.j] = true;
		p.dist = 0;
		int dist = 1;
		if(start.i == desti && start.j == destj)
		{
			p.dist = 0;
			return;
		}
		while (!q.isEmpty()) {
			int size = q.size();

			for (int j = 0; j < size; j++) {
				BaekJoon b = q.poll();
				for (int k = 0; k < 4; k++) {
					int tx = b.j + dx[k];
					int ty = b.i + dy[k];
					if (tx <= 0 || ty <= 0 || tx > N || ty > N || arr[ty][tx] == 1 || visited[ty][tx])
						continue;
					if (ty == desti && tx == destj) {
						p.dist = dist;
						return;
					}
					visited[ty][tx] = true;
					q.offer(new BaekJoon(ty, tx, 0));
				}
			}
			dist++;
		}
		exit(-1);
	}
	public static class BaekJoon {
		int i;
		int j;
		int f;

		public BaekJoon(int i, int j, int f) {
			this.i = i;
			this.j = j;
			this.f = f;
		}
	}

	public static class People implements Comparable<People> {
		int starti;
		int startj;
		int desti;
		int destj;
		int dist;

		@Override
		public String toString() {
			return "People [starti=" + starti + ", startj=" + startj + ", desti=" + desti + ", destj=" + destj
					+ ", dist=" + dist + "]";
		}

		public People(int starti, int startj, int desti, int destj, int dist) {
			this.starti = starti;
			this.startj = startj;
			this.desti = desti;
			this.destj = destj;
			this.dist = dist;
		}

		@Override
		public int compareTo(People o) {
			if (this.dist == o.dist) {
				if (this.starti == o.starti)
					return this.startj - o.startj;
				else
					return this.starti - o.starti;
			}
			return this.dist - o.dist;
		}

	}
}
