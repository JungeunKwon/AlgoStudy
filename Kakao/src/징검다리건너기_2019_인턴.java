import java.util.Arrays;

public class 징검다리건너기_2019_인턴 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int k = 3;
		int stones[] = { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 };
		int ans = solution(stones, k);
		System.out.println(ans);
	}

	public static int solution(int[] stones, int k) {
		int answer = 0;
		int min = 1;
		int max = 200000000;
		while (min < max - 1) {
			int mid = (min + max) / 2;
			int pass = 0;
			boolean flag = false;
			for (int i = 0; i < stones.length; i++) {
				if (stones[i] < mid) {
					pass++;
				} else
					pass = 0;
				if (pass >= k) {
					flag = true;
					break;
				}
			}
			if (flag) {
				max = mid;
			} else {
				min = mid;
			}
		}
		answer = min;
		return answer;
	}
}
