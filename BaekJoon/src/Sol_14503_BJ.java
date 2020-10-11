import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_14503_BJ {
	public static int N;
	public static int M;
	public static int arr[][];
	public static int dx[] = { 0, 1, 0, -1 };
	public static int dy[] = { -1, 0, 1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		st = new StringTokenizer(br.readLine());
		Robot robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));
		int count = 1;
		boolean visited[][] = new boolean[N][M];
		int tx = robot.j, ty = robot.i;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		Queue<Robot> q = new LinkedList<>();
		visited[ty][tx] = true;
		q.offer(robot);
		while (!q.isEmpty()) {
			boolean flag = false;
			Robot temp = q.poll();
			
			int dir = temp.dir;
			for (int i = 1; i < 5; i++) {
				dir = (4 + (temp.dir - i)) % 4;
				tx = temp.j + dx[dir];
				ty = temp.i + dy[dir];
				if (tx < 0 || ty < 0 || tx >= M || ty >= N)
					continue;
				if (arr[ty][tx] == 1)
					continue;
				if (visited[ty][tx])
					continue;
				flag = true;
				visited[ty][tx] = true;
				count++;
				q.offer(new Robot(ty, tx, dir));
				break;
			}
			if (!flag) {
				tx = temp.j - dx[temp.dir];
				ty = temp.i - dy[temp.dir];
				if (tx < 0 || ty < 0 || tx >= M || ty >= N || arr[ty][tx] == 1)
					break;
				q.offer(new Robot(ty, tx, temp.dir));

			}
		}

		System.out.println(count);
	}

	public static class Robot {
		int i;
		int j;
		int dir;

		public Robot(int i, int j, int dir) {
			this.i = i;
			this.j = j;
			this.dir = dir;
		}

	}
}
