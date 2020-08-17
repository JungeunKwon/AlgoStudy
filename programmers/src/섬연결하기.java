import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class º∂ø¨∞·«œ±‚ {

	public static void main(String[] args) {
		int n = 4;
		int costs[][] = { { 0, 1, 1 }, { 0, 2, 2 }, { 1, 2, 5 }, { 1, 3, 1 }, { 2, 3, 8 } };
		int result = solution(n, costs);
		System.out.println(result);
	}

	public static int parent[];

	public static int solution(int n, int[][] costs) {
		int answer = 0;
		parent = new int[n];
		List<Info> list = new ArrayList<>();
		for (int i = 0; i < costs.length; i++) {
			int start = costs[i][0];
			int end = costs[i][1];
			int cost = costs[i][2];
			list.add(new Info(start, end, cost));
		}
		Collections.sort(list);
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
		for(int i = 0; i < list.size(); i ++)
		{
			int start = list.get(i).start;
			int end = list.get(i).end;
			start = findSet(start);
			end = findSet(end);
			if(start!=end)
			{
				answer += list.get(i).cost;
				unionSet(start, end);
			}
		}
		return answer;
	}

	public static int findSet(int x) {
		if (parent[x] == x)
			return x;
		else {
			int p = findSet(parent[x]);
			parent[x] = p;
			return p;
		}
	}

	public static void unionSet(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if (x != y) {
			parent[y] = x;
		}
	}

	public static class Info implements Comparable<Info> {
		int start;
		int end;
		int cost;

		Info(int start, int end, int cost) {
			this.start = start;
			this.end = end;
			this.cost = cost;
		}

		@Override
		public int compareTo(Info o) {
			return this.cost - o.cost;
		}
	}
}
