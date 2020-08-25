import java.util.Arrays;

public class 자물쇠와열쇠_2020_BLIND {

	public static void main(String[] args) {
		int key[][] = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int lock[][] = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };

		boolean result = solution(key, lock);
		System.out.println(result);
	}

	public static boolean flag;
	public static int holenum;

	public static boolean solution(int[][] key, int[][] lock) {
		boolean answer = true;
		int N = lock.length;
		int M = key.length;
		flag = false;
		holenum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (lock[i][j] == 0)
					holenum++;
			}
		}
		System.out.println(holenum);
		rotate(key, lock, N, M);
		answer = flag;
		return answer;
	}

	private static void rotate(int[][] key, int[][] lock, int N, int M) {
		int keytemp[][] = new int[M][M];
		for (int j = 0; j < M; j++) {
			keytemp[j] = Arrays.copyOf(key[j], M);
		}
		int temp[][] = new int[M][M];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < M; k++) {
					temp[j][k] = keytemp[M - k - 1][j];
				}
			}
			isCorrect(temp, lock, N, M);
			keytemp = temp;
			temp = new int[M][M];
		}
	}

	private static void isCorrect(int[][] temp, int[][] lock, int N, int M) {
	    boolean innerflag = check(temp, lock, N, M);
        if (innerflag) {
           flag = true;
           return;
        }

	}

	private static boolean check(int[][] temp, int[][] lock, int N, int M) {
		for (int i =-N; i <N; i++) {
			for (int j = -N ; j < N; j++) {
				int count = 0;

				a: for (int k = 0; k < M; k++) {
	                if (k + i < 0 || k + i >= N)
	                        continue;
	               for (int l = 0; l < M; l++) {
	                   if (j + l < 0 || j + l >= N)
	                        continue;
	                  if (temp[k][l] == 1) {
	                     if (lock[k+i][l+j] == 1)
	                        break a;
	                     else
	                        count++;

	                  }

	               }
	            }

				if (count == holenum)
					return true;
			}
		}

		return false;
	}
}
