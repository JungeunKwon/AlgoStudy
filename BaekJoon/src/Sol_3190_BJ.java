import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_3190_BJ {
	public static int N;
	public static int K;
	public static int L;
	public static int arr[][];
	public static LinkedList<Info> q;
	public static boolean cori[][];
	public static int dx[] = { 1, 0, -1, 0 };
	public static int dy[] = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
		StringTokenizer st;
		q = new LinkedList<>();
		cori = new boolean[N + 1][N + 1];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int posi = Integer.parseInt(st.nextToken());
			int posj = Integer.parseInt(st.nextToken());
			arr[posi][posj] = 1;
		}
		L = Integer.parseInt(br.readLine());
		q.addFirst(new Info(1, 1));
		int time = 0;
		int opcount = 0;
		int nexttime = 1;
		int dir = 0;
		cori[1][1] = true;
		int tx = 0, ty = 0;
		int i = 0, j = 0;
		String op = "S";
		aa: while (true) {
			if (opcount < L) {
				st = new StringTokenizer(br.readLine());
				time = Integer.parseInt(st.nextToken());
				op = st.nextToken();
				opcount++; //명령어가 아직 더 남았을 때
			} else {
				time = time + 10;
				op = "S"; //명령어 안남았으면 계속 한 방향이니까
			}
			for (; nexttime <= time; nexttime++) { //다음 명령어 수행할 시간까지 똑같은 방향
				i = q.getFirst().i;
				j = q.getFirst().j;
				tx = j + dx[dir];
				ty = i + dy[dir];
				if (tx <= 0 || ty <= 0 || tx > N || ty > N)
					break aa;
				if (cori[ty][tx])
					break aa;
				cori[ty][tx] = true;
				if (arr[ty][tx] != 1) { //사과 없을때 꼬리 지우기
					Info temp = q.getLast();
					cori[temp.i][temp.j] = false;
					q.removeLast();
				} else { //사과 있을때
					arr[ty][tx] = 0;
				}
				q.addFirst(new Info(ty, tx)); //머리 집어 넣기
			}

			if (op.equals("D")) {
				if (dir == 3) {
					dir = 0;
				} else
					dir++;

			} else if (op.equals("L")) {
				if (dir == 0) {
					dir = 3;
				} else
					dir--;
			}
		}
		System.out.println(nexttime);

	}

	public static class Info {
		int i;
		int j;

		Info(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
