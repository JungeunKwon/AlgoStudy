import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_14719_BJ {
	public static int N;
	public static int M;
	public static int arr[][];

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		st = new StringTokenizer(br.readLine());
		int[] height = new int[M];
		int answer = 0;

		for (int i = 0; i < M; i++) {
			int H = Integer.parseInt(st.nextToken());
			height[i] = H;
			for (int j = 0; j < H; j++) {
				arr[j][i] = 1;
			}
		}
		int leftidx = 0;
		int rightidx = 0;
		int right = 0;

		int left = height[leftidx];
		for (int i = 1; i < M - 1; i++) {
			int now = height[i];

			if (i >= rightidx) {
				right = 0;
				for (int j = i + 1; j < M; j++) {
					if (height[j] > right) {
						right = height[j];
						rightidx = j;
					}
				}
			}

			if (now < left && now < right) {
				answer = answer + (Math.min(left, right) - now);
			}
			if (height[leftidx] <= now) {
				leftidx = i;
				left = now;
			}

		}
		System.out.println(answer);
	}

}
