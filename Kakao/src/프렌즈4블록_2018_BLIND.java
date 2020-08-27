import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 프렌즈4블록_2018_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m = 4;
		int n = 5;
		String[] board = { "CCBDE", "AAADE", "AAABF", "CCBBF" };

		int result = solution(m, n, board);
		System.out.println(result);
	}

	public static int dx[] = { 1, 1, 0 };
	public static int dy[] = { 0, 1, 1 };

	public static int solution(int m, int n, String[] board) {
		int answer = 0;
		char arr[][] = new char[m][n];
		for (int i = 0; i < m; i++) {
			String line = board[i];
			for (int j = 0; j < n; j++) {
				arr[i][j] = line.charAt(j);
			}
		}
		while (true) {
			List<Info> list = new ArrayList<>();
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[i][j] != ' ') {
						int count = 0;
						int tx = 0, ty = 0;
						for (int k = 0; k < 3; k++) {

							tx = j + dx[k];
							ty = i + dy[k];
							if (tx < 0 || ty < 0 || tx >= n || ty >= m)
								continue;
							if (arr[ty][tx] == arr[i][j])
								count++;

						}
						if(count == 3) {
							list.add(new Info(i, j));
							list.add(new Info(i+1, j));
							list.add(new Info(i+1, j+1));
							list.add(new Info(i, j+1));
						}
					}
				}
			}
			if(list.size() == 0)break;
			for(int i = 0; i < list.size(); i++) {
				Info temp = list.get(i);
				if(arr[temp.i][temp.j] != ' ') {
					arr[temp.i][temp.j]= ' ';
					answer++;
				}
			}
			for (int i = 0; i < n; i++) {
				for (int j = m-1; j >0; j--) {
					if(arr[j][i] == ' ') {
						int ty = j;
						while(true) {
						   ty=ty-1;
						   if(ty<0) break;
						   if(arr[ty][i] != ' ') {
							   arr[j][i] = arr[ty][i];
							   arr[ty][i] = ' ';
							   break;
						   }
						}
					}
				}
			}
		}
		return answer;
	}

	public static class Info {
		int i;
		int j;

		Info(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}