import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Sol_13458_BJ_2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int rooms[] = new int[N];
		long dp[] = new long[1000001];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			rooms[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		long result = 0;
		for (int i = 0; i < N; i++) {
			int peoples = rooms[i];
			if (dp[peoples] != 0) {
				result = result + dp[peoples];
			} else {
				long innerresult = 1;
				if (peoples <= B) {
					innerresult = 1;

				} else {
					int temp = peoples;
					temp = peoples - B;
					if (temp % C == 0) {
						innerresult = (temp / C )+ 1;
					} else {
						innerresult = (temp / C) + 2;
					}
				}
				result = result + innerresult;
				dp[peoples] = innerresult;
			}
		}
		System.out.println(result);
	}

}
