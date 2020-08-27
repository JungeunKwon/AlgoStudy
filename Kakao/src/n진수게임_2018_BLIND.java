import java.util.*;

public class n진수게임_2018_BLIND {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 16;
		int t = 16;
		int m = 2;
		int p = 1;
		String result = solution(n, t, m, p);
		System.out.println(result);
	}

	public static String solution(int n, int t, int m, int p) {
		String answer = "";
		String player[] = new String[m];
		for(int i = 0; i <m;i++) {
			player[i] = "";
		}
		int num = 0;
		int order = 0;
		aa : while (true) {
			String tempnum = Integer.toString(num, n);
			for (int j = 0; j < tempnum.length(); j++) {
				player[order] = player[order] + tempnum.charAt(j);
				order++;
				if(player[p-1].length() == t)break aa;
				if (order >= m)
					order = 0;
			
			}num++;
		}
		return player[p-1].toUpperCase();
	}
}