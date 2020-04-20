import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_14890_BJ {
	public static int N;
	public static int L;
	public static int arr[][];
	public static int count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		count = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int i = 0; i < N; i++) {
			checkgaro(arr[i]);
		}
		for(int i = 0; i < N ; i++)
		{
			int temp[] = new int[N];
			for(int j = 0; j < N; j ++)
			{
				temp[j] = arr[j][i];
			}
			checkgaro(temp);
		}
		System.out.println(count);
	}

	private static void checkgaro(int[] garo) {
		boolean[] visited = new boolean[N];
		for (int i = 0; i < N - 1; i++) {
			if (Math.abs(garo[i] - garo[i + 1]) > 1)
				return;
	
			else if (garo[i] > garo[i + 1]) {
				if(i + L >= N) return;
				for (int j = i + 1; j <= i + L; j++) {
					if (visited[j])
						return;
					if (garo[i + 1] != garo[j])
						return;
				}
				for (int j = i + 1; j <= i + L; j++) {
					visited[j] = true;
				}
			} else if (garo[i] < garo[i + 1]) {
				if((i+1)-L < 0 ) return;
				for (int j = i; j >= (i+1) - L; j--) {
					if (visited[j])
						return;
					if (garo[i] != garo[j])
						return;
				}
				for (int j = i; j >= (i+1) - L; j--) {
					visited[j] = true;
				}

			} else if (garo[i] == garo[i + 1])
				continue;

		}
		count++;
	}

}
