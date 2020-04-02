import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sol_14888_BJ {
	public static int N;
	public static int arr[];
	public static boolean flag[];
	public static int calcu[];
	public static long max;
	public static long min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		calcu = new int[N - 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int t = 0;
		
		min = Long.MAX_VALUE;
		max = Long.MIN_VALUE;
		for (int i = 0; i < 4; i++) {
			int num = Integer.parseInt(st.nextToken());
			for (int j = 0; j < num; j++) {
				calcu[t] = i;
				t++;
			}
		}
		flag = new boolean[N - 1];
		int newcalarr[] = new int[N - 1];
		Perm(newcalarr, 0);
		System.out.println(max);
		System.out.println(min);
	}

	private static void Perm(int[] newcalarr, int idx) {
		if (idx >= N - 1) {
			Calculator(newcalarr);
			return;
		}
		for (int i = 0; i < N - 1; i++) {
			if (flag[i])
				continue;
			flag[i] = true;
			newcalarr[idx] = calcu[i];
			Perm(newcalarr, idx + 1);
			flag[i] = false;
		}
	}

	private static void Calculator(int[] newcalarr) {
		long result = arr[0];
		for (int i = 0; i < N - 1; i++) {
			long first = result;
			long second = arr[i + 1];
			switch (newcalarr[i]) {
			case 0:
				result = first + second;
				break;
			case 1:
				result = first - second;
				break;
			case 2:
				result = first * second;
				break;
			case 3:
				if(first < 0)
				{
					result = Math.abs(first) / second;
					result = -result;
				}else
				{
					result = first / second;
				}
				break;
			}
		}
		if(result > max) max = result;
		if(result < min) min = result;
	}

}
