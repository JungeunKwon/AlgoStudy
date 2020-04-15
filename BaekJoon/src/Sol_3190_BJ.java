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
				opcount++; //��ɾ ���� �� ������ ��
			} else {
				time = time + 10;
				op = "S"; //��ɾ� �ȳ������� ��� �� �����̴ϱ�
			}
			for (; nexttime <= time; nexttime++) { //���� ��ɾ� ������ �ð����� �Ȱ��� ����
				i = q.getFirst().i;
				j = q.getFirst().j;
				tx = j + dx[dir];
				ty = i + dy[dir];
				if (tx <= 0 || ty <= 0 || tx > N || ty > N)
					break aa;
				if (cori[ty][tx])
					break aa;
				cori[ty][tx] = true;
				if (arr[ty][tx] != 1) { //��� ������ ���� �����
					Info temp = q.getLast();
					cori[temp.i][temp.j] = false;
					q.removeLast();
				} else { //��� ������
					arr[ty][tx] = 0;
				}
				q.addFirst(new Info(ty, tx)); //�Ӹ� ���� �ֱ�
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
