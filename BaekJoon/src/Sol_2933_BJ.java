import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Sol_2933_BJ {
	public static int R;
	public static int C;
	public static int N;
	public static char arr[][];
	public static int pos[];
	public static int dx[] = { -1, 1, 0, 0 };
	public static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		boolean visited[][] = new boolean[R][C];
		for (int i = 0; i < R; i++) {
			String line = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = line.charAt(j);
			}
		}
	
		N = Integer.parseInt(br.readLine());
		pos = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			pos[i] = R - (Integer.parseInt(st.nextToken()));
		}
		aa : for (int a = 0; a < N; a++) {
			if (a % 2 == 0) // left
			{
				for (int i = 0; i < C; i++) {
					if (arr[pos[a]][i] == 'x') {
						arr[pos[a]][i] = '.';
						break;
					}
				}
			} else { // right
				for (int i = C - 1; i >= 0; i--) {
					if (arr[pos[a]][i] == 'x') {
						arr[pos[a]][i] = '.';
						break;
					}
				}
			}
			visited = new boolean[R][C];
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (arr[i][j] == '.')
						continue;
					if (visited[i][j])
						continue;
					int numarr[][] = new int[R][C];
					numarr[i][j] = 1;
					visited[i][j] = true;
					Queue<Info> q = new LinkedList<>();
					q.offer(new Info(i, j));
					int min = check(arr, q, visited, numarr);

					if (min != 1) {
						
						move(arr, numarr);
						continue aa;
					}
				}
			}

		}
		StringBuilder sb= new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void move(char[][] arr2, int[][] temp) {
		int min = R;
		 for (int i = 0; i < C; i++) {
			 aa:for (int j = R - 2; j >= 0; j--) {
				if (temp[j][i] == 1 && temp[j + 1][i] == 0 && arr2[j + 1][i] == '.') {
					int ty = j;
					while (true) {
						ty = ty + 1;
						if (ty >= R || ty < 0) {
							ty = ty - 1;
							break;
						}
						if(temp[ty][i] == 1)
						{
							continue aa;
						}
						if (arr2[ty][i] == 'x') {
							ty = ty -1;
							break;
						}
					}
					if (Math.abs(ty - j)  < min)
						min = Math.abs(ty - j) ;
					
				}
			}
		}
		for (int i = 0; i < C; i++) {
			for (int j = R - 1; j >= 0; j--) {
				if (temp[j][i] == 1) {
					char tempc = arr[j + min][i];
					arr[j + min][i] = arr[j][i];
					arr[j][i] = tempc;

				}
			}
		}
		

	}

	private static int check(char[][] arr2, Queue<Info> q, boolean[][] visited, int[][] numarr) {
		int tx = 0, ty = 0;
		int min = R;
		while (!q.isEmpty()) {
			Info temp = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = temp.j + dx[i];
				ty = temp.i + dy[i];
				if (tx < 0 || ty < 0 || tx >= C || ty >= R)
					continue;
				if (visited[ty][tx])
					continue;
				if (arr[ty][tx] == '.')
					continue;
				if (min > R - ty)
					min = R - ty;
				visited[ty][tx] = true;
				numarr[ty][tx] = 1;
				q.offer(new Info(ty, tx));
			}
		}
		return min;
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
