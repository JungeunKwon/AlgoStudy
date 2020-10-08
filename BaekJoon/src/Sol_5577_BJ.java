import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sol_5577_BJ {
	public static int colors[] = { 0, 1, 2, 3 };
	public static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[] = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		max = 0;
		for (int i = 0; i < N; i++) {
			int cur = arr[i];

			for (int j = 1; j < 4; j++) {
				if (cur == j)
					continue;
				arr[i] = j;
				max = Math.max(max, Play(arr,i));
			}
			arr[i] = cur;
		}
		System.out.println(N - max);
	}

	private static int Play(int[] arr,int i) {
		int cnt = 0;
		int left = i;
		int right = i;
		int N = arr.length;
		int samecnt =0;
		while(left >= 0 && right < N && arr[left] == arr[right]) {
			int curcolor = arr[left];
			while(left>= 0 && curcolor == arr[left]) {
				samecnt ++;
				left--;
			}
			while(right < N && curcolor == arr[right] ) {
				samecnt ++;
				right++;
			}
			if(samecnt >= 4) {
				cnt = cnt +samecnt;
			}
			samecnt=0;
		}
		return cnt - 1;
	}

}
