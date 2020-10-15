import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Sol_17837_BJ_2 {
	public static int N;
	public static int K;
	public static int dx[] = { 0, 1, -1, 0, 0 };
	public static int dy[] = { 0, 0, 0, -1, 1 };
	public static int arr[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		LinkedList<Mal> arrlist[][] = new LinkedList[N + 1][N + 1];
		List<Mal> list = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int ti = Integer.parseInt(st.nextToken());
			int tj = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			Mal mal = new Mal(ti, tj, dir, i);
			list.add(mal);
			arrlist[ti][tj] = new LinkedList<>();
			arrlist[ti][tj].addLast(mal);
		}
		int turn = 1;
		aa: while (turn <= 1000) {
			for (int i = 0; i < K; i++) {
				Mal now = list.get(i);
				LinkedList<Mal> templist = new LinkedList<>();
				for (int j = 0; j < arrlist[now.i][now.j].size(); j++) {
					Mal temp = arrlist[now.i][now.j].get(j);
					if (temp.num == now.num) {
						for (int k = j; k < arrlist[now.i][now.j].size(); k++) {
							Mal temp2 = arrlist[now.i][now.j].get(k);
							templist.add(temp2);
							arrlist[now.i][now.j].remove(k);
							k--;
						}
						break;
					}
				}
				int dir = now.dir;
				int tx = now.j + dx[dir];
				int ty = now.i + dy[dir];
				boolean flag = false;
				if (tx < 1 || ty < 1 || tx > N || ty > N || arr[ty][tx] == 2) {
					dir = getOpposite(dir);
					tx = now.j + dx[dir];
					ty = now.i + dy[dir];
					if (tx < 1 || ty < 1 || tx > N || ty > N || arr[ty][tx] == 2) {
						tx = now.j;
						ty = now.i;
						flag = true;
					}

				}
				if (!flag && arr[ty][tx] == 1) {
					int size = templist.size();
					LinkedList<Mal> temptemplist = new LinkedList<>();
					for (int j = 0; j < size; j++) {
						Mal temp = templist.getLast();
						templist.removeLast();
						temptemplist.addLast(temp);
					}
					templist = temptemplist;
				}
				now.i = ty;
				now.j = tx;
				now.dir = dir;
				LinkedList<Mal> newposlist = arrlist[ty][tx];
				if (newposlist == null)
					newposlist = new LinkedList<>();
				for (int j = 0; j < templist.size(); j++) {
					Mal move = templist.get(j);
					move.i = ty;
					move.j = tx;
					if (move.num == now.num) {
						move.dir = dir;
					}
					newposlist.addLast(move);
				}
				arrlist[ty][tx] = newposlist;
				if (newposlist.size() >= 4) {
					break aa;
				}
			}
			turn++;
		}
		if (turn > 1000) {
			System.out.println(-1);
		} else
			System.out.println(turn);
	}

	public static int getOpposite(int dir) {
		int result = 0;
		switch (dir) {
		case 1:
			result = 2;
			break;
		case 2:
			result = 1;
			break;
		case 3:
			result = 4;
			break;
		case 4:
			result = 3;
			break;

		}
		return result;
	}

	public static class Mal {
		int i;
		int j;
		int dir;
		int num;

		Mal(int i, int j, int dir, int num) {
			this.i = i;
			this.j = j;
			this.dir = dir;
			this.num = num;
		}
	}
}
