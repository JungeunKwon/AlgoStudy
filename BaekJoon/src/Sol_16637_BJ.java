import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sol_16637_BJ {
	public static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		char[] arr = new char[N];
		String line = br.readLine();
		arr = line.toCharArray();
		int opcnt = N / 2;
		char[] op = new char[opcnt];
		int num[] = new int[opcnt + 1];
		max = 0;
		for (int i = 0, j = 0, k = 0; i < N; i++) {
			if (Character.isDigit(arr[i])) {
				num[k] = arr[i] - '0';
				k++;
				continue;
			}
			op[j] = arr[i];
			j++;
		}
		for (int i = 0; i < 1 << opcnt; i++) {
			boolean flag[] = new boolean[opcnt];
			int[] tempnum = new int[opcnt + 1];
			tempnum = Arrays.copyOf(num, opcnt + 1);
			for (int j = 0; j < opcnt; j++) {
				if ((i & 1 << j) != 0) {
					flag[j] = true;
				}
			}
			Calculate(flag, op, tempnum);
		}
		System.out.println(max);
	}

	private static void Calculate(boolean[] flag, char[] op, int[] num) {

		for (int i = 0; i < flag.length; i++) {
			if (flag[i]) {
				if (i < flag.length - 1 && flag[i + 1])
					return;
				else {
					switch (op[i]) {
					case '+':
						num[i] = num[i] + num[i + 1];
						break;
					case '-':
						num[i] = num[i] - num[i + 1];

						break;
					case '*':
						num[i] = num[i] * num[i + 1];

						break;
					}
					num[i + 1] = 0;
				}
			}
		}
		int result = num[0];
		for (int i = 0, j = 1; i < op.length; i++, j++) {
			switch (op[i]) {
			case '+':
				result += num[j];
				break;
			case '-':
				result = result - num[j];

				break;
			case '*':
				result = result * (flag[i] && num[j] == 0 ? 1 : num[j]);
				break;
			}
		}
		if (max < result)
			max = result;
	}

}