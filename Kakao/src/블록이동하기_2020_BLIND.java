
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 블록이동하기_2020_BLIND {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int board[][] =  {{0, 0, 0, 0, 0, 0, 1}, {1, 1, 1, 1, 0, 0, 1}, {0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 1, 1, 0, 0}, {0, 1, 1, 1, 1, 1, 0}, {0, 0, 0, 0, 0, 1, 0}, {0, 0, 1, 0, 0, 0, 0}}
;
		int ans = solution(board);
		System.out.println(ans);
	}

	public static int rotatedx[][] = { { 0, 1 }, { 0, -1 }, { 0, 1 }, { 0, -1 }, { 1, 1 }, { -1, -1 }, { 1, 1 },
			{ -1, -1 } };
	public static int rotatedy[][] = { { -1, -1 }, { -1, -1 }, { 1, 1 }, { 1, 1 }, { 0, 1 }, { 0, 1 }, { 0, -1 },
			{ 0, -1 } };

	// |ㄱ, ㄱ , ㄴ, |ㄴ
	public static int N;

	public static int solution(int[][] board) throws InterruptedException {
		int answer = 0;
		Queue<Info> q = new LinkedList<>();
		q.offer(new Info(0, 1, 0, 0, 0, 1));
		N = board.length;
		int visited[][][] = new int[N][N][2];
		visited[0][1][0] = 1;
		int dx[] = { 0, 0, -1, 1 };
		int dy[] = { -1, 1, 0, 0 };

		int tx = 0, ty = 0, tx2 = 0, ty2 = 0;

		aa :while (!q.isEmpty()) {
			int size = q.size();
			for (int a = 0; a < size; a++) {
				Info temp = q.poll();
			
				for (int i = 0; i < 4; i++) {
					tx = temp.j + dx[i];
					ty = temp.i + dy[i];
					tx2 = temp.j2 + dx[i];
					ty2 = temp.i2 + dy[i];
					if (tx < 0 || tx2 < 0 || ty < 0 || ty2 < 0 || tx >= N || tx2 >= N || ty >= N || ty2 >= N)
						continue;
					if (board[ty][tx] == 1 || board[ty2][tx2] == 1)
						continue;
					if (tx  < tx2 || ty < ty2) {
						int t = tx;
						tx = tx2;
						tx2 =t;
						t = ty;
						ty = ty2;
						ty2= t;
					} 
					if (visited[ty][tx][temp.rotate] != 0 && visited[ty][tx][temp.rotate] <= temp.cnt + 1)
						continue;
					visited[ty][tx][temp.rotate] = temp.cnt + 1;
					if ((ty == N - 1 && tx == N - 1) || (tx2 == N - 1 && ty2 == N - 1)) {
						answer = temp.cnt + 1;
						break aa;
					}
					q.offer(new Info(ty, tx, ty2, tx2, temp.rotate, temp.cnt + 1));

				}
				if (temp.rotate == 0) {
					if (temp.j > temp.j2) {
						possible(temp.j2, temp.i2, 0, q, 1, board, temp.j, temp.i, temp.cnt + 1, visited);
						possible(temp.j2, temp.i2, 2, q, 1, board, temp.j, temp.i, temp.cnt + 1, visited);

						possible(temp.j, temp.i, 1, q, 1, board, temp.j2, temp.i2, temp.cnt + 1, visited);
						possible(temp.j, temp.i, 3, q, 1, board, temp.j2, temp.i2, temp.cnt + 1, visited);
					} else {
						possible(temp.j, temp.i, 0, q, 1, board, temp.j2, temp.i2, temp.cnt + 1, visited);
						possible(temp.j, temp.i, 2, q, 1, board, temp.j2, temp.i2, temp.cnt + 1, visited);

						possible(temp.j2, temp.i2, 1, q, 1, board, temp.j, temp.i, temp.cnt + 1, visited);
						possible(temp.j2, temp.i2, 3, q, 1, board, temp.j, temp.i, temp.cnt + 1, visited);
					}
				} else {
					if (temp.i > temp.i2) {
						possible(temp.j2, temp.i2, 4, q, 0, board, temp.j, temp.i, temp.cnt + 1, visited);
						possible(temp.j2, temp.i2, 5, q, 0, board, temp.j, temp.i, temp.cnt + 1, visited);

						possible(temp.j, temp.i, 6, q, 0, board, temp.j2, temp.i2, temp.cnt + 1, visited);
						possible(temp.j, temp.i, 7, q, 0, board, temp.j2, temp.i2, temp.cnt + 1, visited);
					} else {
						possible(temp.j, temp.i, 4, q, 0, board, temp.j2, temp.i2, temp.cnt + 1, visited);
						possible(temp.j, temp.i, 5, q, 0, board, temp.j2, temp.i2, temp.cnt + 1, visited);

						possible(temp.j2, temp.i2, 6, q, 0, board, temp.j, temp.i, temp.cnt + 1, visited);
						possible(temp.j2, temp.i2, 7, q, 0, board, temp.j, temp.i, temp.cnt + 1, visited);
					}

				}

			}
		}

		return answer;
	}

	private static void possible(int tx, int ty, int pos, Queue<Info> q, int newrotatem, int board[][], int originx,
			int originy, int cnt, int[][][] visited) {
		int ttx = tx;
		int tty = ty;
		for (int i = 0; i < 2; i++) {
			ttx = tx + rotatedx[pos][i];
			tty = ty + rotatedy[pos][i];
			if (ttx < 0 || tty < 0 || tty >= N || ttx >= N || board[tty][ttx] == 1)
				return;
		}
		if (ttx < originx || tty < originy) {
			int t = ttx;
			ttx = originx;
			originx =t;
			t = tty;
			tty = originy;
			originy= t;
		} 
		if (visited[tty][ttx][newrotatem] != 0 && visited[tty][ttx][newrotatem] <= cnt)
			return;
		q.offer(new Info(originy, originx, tty, ttx, newrotatem, cnt));
		visited[tty][ttx][newrotatem] = cnt;
	}

	public static class Info {
		int i;
		int j;
		int i2;
		int j2;
		int cnt;
		int rotate;

		public Info(int i, int j, int i2, int j2, int rotate, int cnt) {
			super();
			this.i = i;
			this.j = j;
			this.i2 = i2;
			this.j2 = j2;
			this.cnt = cnt;
			this.rotate = rotate;

		}

	}
}