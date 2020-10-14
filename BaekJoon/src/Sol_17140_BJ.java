import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Sol_17140_BJ {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()) - 1;
		int c = Integer.parseInt(st.nextToken()) - 1;
		int k = Integer.parseInt(st.nextToken());
		int arr[][] = new int[101][101];
		int nummax = 0;
		for (int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (nummax < arr[i][j])
					nummax = arr[i][j];
			}
		}
		int R = 3;
		int C = 3;
		int cnt = 0;
		while (cnt <= 100) {

			if (arr[r][c] == k)
				break;
			int countarr[] = new int[nummax + 1];
			int nextR = R;
			int nextC = C;
			if (R >= C) {
				for (int i = 0; i < R; i++) {
					countarr = new int[101];
					for (int j = 0; j < C; j++) {
						if (arr[i][j] == 0)
							continue;
						countarr[arr[i][j]]++;

					}
					List<Integer> list = new ArrayList<>();
					for (int j = 0; j < 101; j++) {
						if (countarr[j] == 0)
							continue;
						list.add(j);
						list.add(countarr[j]);

					}

					List<Integer> templist = new ArrayList<Integer>();
					for (int j = 1; j <= 100; j++) {
						for (int t = 0; t < list.size() - 1; t = t + 2) {
							if (list.get(t + 1) == j) {
								templist.add(list.get(t));
								templist.add(j);
							}
						}
					}

					int temp = templist.size() > 100 ? 100 : list.size();
					if (nextC < temp)
						nextC = temp;
					for (int j = 0; j < temp; j++) {
						arr[i][j] = templist.get(j);
					}
					for (int j = temp; j < 101; j++) {
						arr[i][j] = 0;
					}

				}
			} else {
				for (int j = 0; j < C; j++) {
					int temparr[] = new int[R];
					for (int i = 0; i < R; i++) {
						temparr[i] = arr[i][j];
					}
					countarr = new int[101];
					for (int i = 0; i < R; i++) {
						if (temparr[i] == 0)
							continue;
						countarr[temparr[i]]++;

					}
					List<Integer> list = new ArrayList<>();
					for (int i = 0; i < 101; i++) {
						if (countarr[i] == 0)
							continue;
						list.add(i);
						list.add(countarr[i]);
					}
					List<Integer> templist = new ArrayList<Integer>();
					for (int i = 1; i <= 100; i++) {
						for (int t = 0; t < list.size() - 1; t = t + 2) {
							if (list.get(t + 1) == i) {
								templist.add(list.get(t));
								templist.add(i);
							}
						}
					}

					int temp = templist.size() > 100 ? 100 : list.size();
					if (nextR < temp)
						nextR = temp;

					for (int m = 0; m < temp; m++) {
						arr[m][j] = templist.get(m);
					}
					for (int m = temp; m < 101; m++) {
						arr[m][j] = 0;
					}

				}
			}

			R = nextR;
			C = nextC;

			cnt++;
		}
		if (cnt > 100) {
			System.out.println(-1);
		} else
			System.out.println(cnt);

	}

}
