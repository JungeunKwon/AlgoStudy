import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

public class 리틀프렌즈사천성_2017_본선 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m = 2;
		int n = 4;
		String board[] = { "NRYN", "ARYA" };
		String result = solution(m, n, board);
		System.out.println(result);
	}

	public static int dx[] = { 0, 0, -1, 1 };
	public static int dy[] = { -1, 1, 0, 0 };

	public static String solution(int m, int n, String[] board) {
		String answer = "";

		char[][] arr = new char[m][n];
		TreeMap<Character, Alpha> list = new TreeMap<>();
		for (int i = 0; i < m; i++) {
			arr[i] = board[i].toCharArray();
			for (int j = 0; j < n; j++) {
				if (Character.isUpperCase(arr[i][j]) && !list.containsKey(arr[i][j])) {
					list.put(arr[i][j], new Alpha(i, j, arr[i][j]));
				}
			}
		}
		List<Alpha> origin = new ArrayList<>();
		Iterator it = list.keySet().iterator();
		while (it.hasNext()) {
			Character c = (Character) it.next();
			origin.add(list.get(c));
		}
	//	Collections.sort(origin);

		while (!origin.isEmpty()) {
			boolean removed = false;
			for (int i = 0; i < origin.size(); i++) {
				Alpha temp = origin.get(i);
				Queue<Info> q = new LinkedList<>();
				int visited[][] = new int[m][n];
				visited[temp.i][temp.j] = 1;
				q.offer(new Info(temp.i, temp.j, 1, -1));
				if (bfs(arr, q, temp.c, m, n, visited)) {
					answer += temp.c;
					origin.remove(temp);
					arr[temp.i][temp.j] = '.';
					removed = true;
					break;
				}
			}
			if (!removed)
				break;

		}
		if (answer.length() != list.size() || !origin.isEmpty()) {
			return "IMPOSSIBLE";
		}
		return answer;
	}

	private static boolean bfs(char[][] arr, Queue<Info> q, char c, int m, int n, int[][] innervisited) {
		int tx = 0, ty = 0;
		while (!q.isEmpty()) {
			Info temp = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = temp.j + dx[i];
				ty = temp.i + dy[i];
				int turn = temp.turn;
				if (tx < 0 || ty < 0 || tx >= n || ty >= m)
					continue;
				if (temp.dir != i)
					turn++;
				if (turn > 3)
					continue;
				if (innervisited[ty][tx] != 0 && innervisited[ty][tx] < turn)
					continue;
				innervisited[ty][tx] = turn;
				char a = arr[ty][tx];

				if (a == c) {
					arr[ty][tx] = '.';
					return true;
				}
				if (a == '.')
					q.offer(new Info(ty, tx, turn, i));

			}
		}
		return false;
	}

	public static class Alpha implements Comparable<Alpha> {
		int i;
		int j;
		char c;

		public Alpha(int i, int j, char c) {
			super();
			this.i = i;
			this.j = j;
			this.c = c;
		}

		@Override
		public int compareTo(Alpha o) {
			// TODO Auto-generated method stub
			return this.c - o.c;
		}

	}

	public static class Info {
		int i;
		int j;
		int turn;
		int dir;

		Info(int i, int j, int turn, int dir) {
			this.i = i;
			this.j = j;
			this.turn = turn;
			this.dir = dir;
		}
	}
}