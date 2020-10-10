import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_14501_BJ {
	public static int N;
	public static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int arr[][] = new int[N + 1][2];
		StringTokenizer st;
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		max = 0;
		// 배열, 시간, 금액
		dfs(arr, 1, 0);
		System.out.println(max);
	}

	private static void dfs(int[][] arr, int day, int price) {
		if (day <= N + 1) {
			if (max < price)
				max = price;
		}

		if (day > N) {

			return;
		}

		dfs(arr, day + arr[day][0], price + arr[day][1]);

		dfs(arr, day + 1, price);
	}

}
