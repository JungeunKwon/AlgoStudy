import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class 네트워크 {
	public static void main(String[] args) {
		int n = 3;
		int computers[][] = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };
		int result = solution(n, computers);
		System.out.println(result);
	}

	public static int parent[];

	public static int solution(int n, int[][] computers) {
		int answer = 0;
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (i == j)
					continue;
				if (computers[i][j] == 1) {
					unionSet(i, j);
				}
			}
		}
		for (int i = 0; i < n; i++) {
			if (parent[i] == i)
				answer++;
		}
		return answer;
	}

	private static void unionSet(int i, int j) {
		i = findSet(i);
		j = findSet(j);
		if (i != j) {
			parent[j] = i;
		}
	}

	private static int findSet(int i) {
		if (parent[i] == i)
			return i;
		else {
			int p = findSet(parent[i]);
			parent[i] = p;
			return p;
		}
	}
}