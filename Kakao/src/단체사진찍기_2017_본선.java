import java.util.Arrays;

public class 단체사진찍기_2017_본선 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 2;
		String data[] = { "N~F=0", "R~T>2" };
		int result = solution(n, data);
		System.out.println(result);
	}

	public static int cnt;

	public static int solution(int n, String[] data) {
		int answer = 0;
		char friends[] = { 'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T' };
		boolean flag[] = new boolean[8];
		char dfsresult[] = new char[8];
		cnt = 0;
		dfs(n, data, flag, friends, dfsresult, 0);
		return cnt;
	}

	private static void dfs(int n, String[] data, boolean[] flag, char[] friends, char[] dfsresult, int idx) {
		if (idx >= 8) {
			for (int i = 0; i < n; i++) {
				char first = data[i].charAt(0);
				char second = data[i].charAt(2);
				char op = data[i].charAt(3);
				int num = Integer.parseInt(data[i].substring(4)) + 1;
				int fipos = 0;
				int sepos = 0;
				for (int j = 0; j < 8; j++) {
					if (dfsresult[j] == first)
						fipos = j;
					if (dfsresult[j] == second)
						sepos = j;
				}
				int diff = Math.abs(fipos-sepos);
				switch (op) {
				case '=':
					if(diff != num)return;
					break;
				case '<':
					if(diff >= num) return;
					break;
				case '>':
					if(diff <= num) return;
					break;
				}
			}
			cnt++;
			return;
		}
		for (int i = 0; i < 8; i++) {
			if (flag[i])
				continue;
			flag[i] = true;
			dfsresult[idx] = friends[i];
			dfs(n, data, flag, friends, dfsresult, idx + 1);
			flag[i] = false;
		}

	}
}