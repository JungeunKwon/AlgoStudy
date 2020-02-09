import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sol_15898_BJ {
	public static int N;
	public static int gama[][];
	public static char gamac[][];
	public static boolean flag[];
	public static Info[] material;
	public static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		gama = new int[5][5];
		gamac = new char[5][5];
		flag = new boolean[N];
		material = new Info[N];
		max = 0;
		for (int a = 0; a < N; a++) {
			int[][] temp = new int[4][4];
			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					temp[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			char[][] tempc = new char[4][4];
			for (int i = 0; i < 4; i++) {
				String line = br.readLine().trim().replace(" ", "");
				for (int j = 0; j < 4; j++) {
					tempc[i][j] = line.charAt(j);
				}
			}
			material[a] = new Info(temp, tempc);
		}
		int temp[] = new int[N];
		Perm(temp, 0);
		System.out.println(max);
	}

	private static void Perm(int[] temp, int idx) {
		if (idx == 3) {
			int postemp[] = new int[4];
			findPos(temp, postemp, 0); // 10개중 3개 뽀ㅃ는거
			return;
		}
		for (int i = 0; i < N; i++) {
			if (flag[i] == true)
				continue;
			flag[i] = true;
			temp[idx] = i;
			Perm(temp, idx + 1);
			flag[i] = false;
		}

	}

	private static void findPos(int[] temp, int[] postemp, int idx) {
		if (idx == 3) { // 자리 4개중 3개 뽀ㅃ는거
			int rotatetemp[] = new int[3];
			findRotate(temp, postemp, rotatetemp, 0);
			return;

		}
		for (int i = 0; i < 4; i++) {
			postemp[idx] = i;
			findPos(temp, postemp, idx + 1);

		}
	}

	private static void findRotate(int[] temp, int[] postemp, int[] rotatetemp, int idx) {
		if (idx == 3) { // 회전 3개 뽀ㅃ는거
			int newgama[][] = new int[5][5];
			char newgamac[][] = new char[5][5];

			for (int i = 0; i < 5; i++) {
				newgama[i] = Arrays.copyOf(gama[i], 5);
				newgamac[i] = Arrays.copyOf(gamac[i], 5);
			}
			putmaterial(newgama, newgamac, temp, postemp, rotatetemp);

			return;
		}
		for (int i = 0; i < 3; i++) {
			rotatetemp[idx] = i;
			findRotate(temp, postemp, rotatetemp, idx + 1);

		}
	}

	private static void putmaterial(int[][] newgama, char[][] newgamac, int[] temp, int[] postemp, int[] rotatetemp) {
		for (int i = 0; i < 3; i++) {
			int [][]nextmat = material[temp[i]].efficacy;
			char [][]nextmatc = material[temp[i]].element;
			int pos = postemp[i];
			int starti = 0, startj =0;
			switch (pos) {
			case 0:
				starti = 0; startj =0;
				break;
			case 1:
				starti = 0;startj =1;
				break;
			case 2:
				starti = 1; startj =0;
				break;
			case 3:
				starti = 1; startj =1;
				break;
			}
			for(int j = 0; j < rotatetemp[i]; j ++)
			{
				nextmat = rotate(nextmat);
				nextmatc = rotate(nextmatc);
			}
			for(int k = starti, m = 0; k < starti+4; k++, m++)
			{
				for(int l = startj, n= 0; l <startj+4; l++,n++)
				{
					int c  = newgama[k][l] + nextmat[m][n];
					if(c<0) c= 0;
					if(c>9) c= 9;
					newgama[k][l] = c;
					if(nextmatc[m][n] !='W') newgamac[k][l] = nextmatc[m][n];
				}
			}
		}
		int count[] = new int[4];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				switch (newgamac[i][j]) {
				case 'R':
					count[0] += newgama[i][j];
					break;
				case 'B':
					count[1] += newgama[i][j];
					break;
				case 'G':
					count[2] += newgama[i][j];
					break;
				case 'Y':
					count[3] += newgama[i][j];
					break;
				}
			}
		}
		System.out.println(Arrays.toString(count));
		int result = 7 * count[0] + 5 * count[1] + 3 * count[2] + 2 * count[3];
		if (max < result)
			max = result;

	}

	public static int[][] rotate(int rotate[][]) {
		int newrotate[][] = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				newrotate[i][j] = rotate[3 - j][i];
			}
		}

		return newrotate;
	}

	public static char[][] rotate(char rotate[][]) {
		char newrotate[][] = new char[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				newrotate[i][j] = rotate[3 - j][i];
			}
		}

		return newrotate;
	}

	public static class Info {
		int efficacy[][];
		char element[][];

		Info(int[][] efficacy, char[][] element) {
			this.efficacy = efficacy;
			this.element = element;
		}
	}
}
