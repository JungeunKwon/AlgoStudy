import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_1507_BJ {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int arr[][] = new int[N][N];
		int copyarr[][] = new int[N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	
			for (int i = 0; i < N; i++) {
				aa: for (int j = i; j < N; j++) {
					int origin = arr[i][j];
					for (int k = 0; k < N; k++) {
						copyarr[i][j] = origin;
						if(k == i || k == j) continue;
						int temp = arr[i][k] + arr[k][j];
						if (origin > temp) {
							System.out.println(-1);
							System.exit(0);
						}
						if (origin == temp) {
							copyarr[i][j] = 0;
							continue aa;
						}
						
					}
				}
			}
	
		int sum = 0;
		for (int i = 0; i < N; i++) {	
			Arrays.toString(copyarr[i]);
			for (int j = i; j < N; j++) {
				sum += copyarr[i][j];
			
			}
		}
		System.out.println(sum);
	}

}
