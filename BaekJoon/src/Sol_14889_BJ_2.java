import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_14889_BJ_2 {
	public static int N;
	public static int arr[][];
	public static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		boolean flag[] = new boolean[N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		min = Integer.MAX_VALUE;
		findTeam(flag, 0, 0);
		System.out.println(min);
	}

	private static void findTeam(boolean[] flag, int idx, int cnt) {
		if (idx >= N) {
			if (cnt != N / 2)
				return;
			int startteam = 0;
			int linkteam = 0;
			int temp = 0;
			for (int i = 0; i < N; i++) {
				temp = 0;
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;
					if (flag[i] == flag[j]) {
						temp += arr[i][j];
					}
				}
				if (flag[i]) {
					startteam += temp;
				} else
					linkteam += temp;
			}
			min = Math.min(Math.abs(startteam - linkteam), min);
			return;
		}
		flag[idx] = true;
		findTeam(flag, idx + 1, cnt + 1);
		flag[idx] = false;
		findTeam(flag, idx + 1, cnt);
	}

}
