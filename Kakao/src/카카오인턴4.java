
import java.util.LinkedList;
import java.util.Queue;

public class 카카오인턴4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] board = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };
		int ans = solution(board);
		System.out.println(ans);
	}

	public static int solution(int[][] board) {
		int answer = 0;
		int N = board.length;
		Queue<Info> q = new LinkedList<>();
		q.offer(new Info(0, 0, -1, 0));
		int dx[] = { -1, 0, 1, 0 };
		int dy[] = { 0, -1, 0, 1 };
		int cost[][] = new int[N][N];
		cost[0][0] = 0;
		int tx = 0, ty = 0;
		while (!q.isEmpty()) {
			Info temp = q.poll();
			inner: for (int i = 0; i < 4; i++) {
				tx = temp.j + dx[i];
				ty = temp.i + dy[i];
				if (tx < 0 || ty < 0 || tx >= N || ty >= N)
					continue inner;
				if(board[ty][tx] == 1) continue inner;
				int calcost = 0;
				if(temp.dir != -1)
				{
					if (i == temp.dir) {
						calcost = temp.cost + 100;
					} else
						calcost = temp.cost + 600;
				}else
					calcost = temp.cost + 100;
				if(cost[ty][tx] == 0)
				{
					cost[ty][tx] = calcost;
					q.offer(new Info(ty, tx, i, calcost));
				}else if(calcost <= cost[ty][tx])
				{
					cost[ty][tx] = calcost;
					q.offer(new Info(ty, tx, i , calcost));
				}
			}
		}
		return cost[N-1][N-1];
	}

	public static class Info {
		int i;
		int j;
		int cost;
		int dir;

		Info(int i, int j, int dir, int cost) {
			this.i = i;
			this.j = j;
			this.dir = dir;
			this.cost = cost;

		}
	}

}
