import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_11053_BJ {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int max = 1;
		int dp[] = new int[N];

		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = i; j >= 0; j--) {
				if (arr[i] > arr[j]) {
					int res = Math.max(dp[i], dp[j] + 1);
					dp[i] = res;
					if (max < res)
						max = res;
				}
			}
		}
		System.out.println(max);
	}

}
