import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_14888_BJ_2 {
	public static int N;
	public static int max;
	public static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		int numarr[] = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			numarr[i] = Integer.parseInt(st.nextToken());
		}
		// 0은 덧셈 1 뺄셈 2 곱셈 3 나눗셈
		int oparr[] = new int[N - 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 0, k = 0; i < 4; i++) {
			int t = Integer.parseInt(st.nextToken());
			for (int j = 0; j < t; j++) {
				oparr[k] = i;
				k++;
			}
		}
		boolean flag[] = new boolean[N - 1];
		int[] permu = new int[N - 1];
		Permu(flag, numarr, oparr, permu, 0);
		System.out.println(max);
		System.out.println(min);
	}

	private static void Permu(boolean[] flag, int[] numarr, int[] oparr, int[] permu, int idx) {
		if (idx >= N - 1) {
			int result = numarr[0];
			for (int i = 0; i < N - 1; i++) {
				int op = oparr[permu[i]];
				switch (op) {
				case 0:
					result = result + numarr[i + 1];
					break;
				case 1:
					result = result - numarr[i + 1];

					break;
				case 2:
					result = result * numarr[i + 1];

					break;
				case 3:
					result = result / numarr[i + 1];

					break;
				}
			}
			if (result > max)
				max = result;
			if (result < min)
				min = result;
			return;
		}
		for (int i = 0; i < N - 1; i++) {
			if (flag[i])
				continue;
			flag[i] = true;
			permu[idx] = i;
			Permu(flag, numarr, oparr, permu, idx + 1);
			flag[i] = false;
		}
	}

}
