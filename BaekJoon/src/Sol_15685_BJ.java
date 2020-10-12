import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Sol_15685_BJ {
	// 상 우 하 좌
	public static int dx[] = { 1, 0, -1, 0 };
	public static int dy[] = { 0, -1, 0, 1 };
	public static int N;
	public static int arr[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[101][101];
		StringTokenizer st;
		PriorityQueue<Info> q;
		Stack<Info> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			stack = new Stack<>();
			int num = 0;
			stack.push(new Info(d, num));
			num++;
			arr[y][x] = 1;
			x = x + dx[d];
			y = y + dy[d];
			arr[y][x] = 1;
			for (int j = 0; j < g; j++) {
				q = new PriorityQueue<>();
				while (!stack.isEmpty()) {
					Info temp = stack.pop();
					q.offer(temp);
					int newdir = (4 + (temp.dir + 1)) % 4;
					x = x + dx[newdir];
					y = y + dy[newdir];
					arr[y][x] = 1;
					q.offer(new Info(newdir, num));
					num++;
				}
				while (!q.isEmpty()) {
					stack.push(q.poll());
				}
			
			}

		}
		int answer = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(arr[i][j] == 1 && arr[i+1][j] == 1 && arr[i][j+1] == 1 && arr[i+1][j+1] == 1) {
					answer ++;
				}
			}
		}
		System.out.println(answer);

	}

	public static class Info implements Comparable<Info> {
		int dir;
		int num;

		public Info(int dir, int num) {
			super();
			this.dir = dir;
			this.num = num;
		}

		@Override
		public int compareTo(Info o) {

			return this.num - o.num;
		}
	}

}
