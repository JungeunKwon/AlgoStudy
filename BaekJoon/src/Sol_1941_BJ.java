import java.io.BufferedReader;
import java.io.InputStreamReader;


public class Sol_1941_BJ {
	public static int N;
	public static int size;
	public static char arr[][];
	public static int dx[] = { 1, -1, 0, 0 };
	public static int dy[] = { 0, 0, 1, -1 };
	public static boolean check[];
	public static int cnt;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = 5;
		arr = new char[N][N];
		size = N*N;
		check = new boolean[1<<(N*N)];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				arr[i][j] = line.charAt(j);
			}
		}
		cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				check[N*i+j] = true;
				dfs(0,arr[i][j] == 'S' ? 1 : 0, (1<<(N*i+j)));
			}
		}
		System.out.println(cnt);
	}
	private static void dfs(int idx, int scnt, int bit) {
		if(idx >= 6) {
			if(scnt >= 4) cnt++;
			return;
		}
		for(int i = 0; i < size;  i ++) {
			if((bit & (1 << i)) == 0) continue;
			int ti = i / N;
			int tj = i % N;
			for(int j = 0 ; j < 4 ; j++) {
				int tx = tj + dx[j];
				int ty = ti + dy[j];
				int num = ty*N + tx;
				if(tx <0 || ty<0||tx>=N || ty>=N) continue;
				if(check[bit | (1 << num)])continue;
				check[bit | (1<<num)] = true;
				int temp = arr[ty][tx] == 'S' ? 1 : 0;
				dfs(idx+1, scnt + temp, bit | (1<<num));
			}
		}
		
	}

	
}
